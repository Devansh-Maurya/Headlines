package maurya.devansh.headlines.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import maurya.devansh.headlines.BuildConfig
import maurya.devansh.headlines.data.local.NewsHeadlinesDb
import maurya.devansh.headlines.data.remote.NewsHeadlinesApiService
import maurya.devansh.headlines.model.NewsHeadline
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Devansh on 6/6/20
 */

class NewsRepository @Inject constructor(private val newsHeadlinesDb: NewsHeadlinesDb,
                                         private val newsHeadlinesApi: NewsHeadlinesApiService,
                                         private val newsBoundaryCallback: NewsBoundaryCallback
) {

    private var newsHeadlinesLiveData: LiveData<PagedList<NewsHeadline>> = MutableLiveData()

    fun refreshData(action : () -> Unit) {
        val disposable = newsHeadlinesApi.getTopHeadlines("in", BuildConfig.API_KEY, 1)
            .subscribeOn(Schedulers.io())
            .subscribe ({ topHeadlines ->
                Completable.fromAction {
                    newsHeadlinesDb.newsHeadlinesDao.deleteAll()
                }.andThen {
                    Completable.fromAction {
                        newsHeadlinesDb.newsHeadlinesDao.insert(topHeadlines.articles.toTypedArray()) }
                        .subscribeOn(Schedulers.io()).subscribe({
                            action()
                            Timber.d("Data added to DB: ${topHeadlines.articles}")
                        }, {
                            action()
                            Timber.e("Error adding data to DB: $it")
                        })
                }.subscribeOn(Schedulers.io()).subscribe()
            }, { throwable ->
                action()
                Timber.e("Failed to load data: $throwable")
            })
        val compositeDisposable = newsBoundaryCallback.compositeDisposable
        compositeDisposable.add(disposable)
    }

    fun getNewsHeadlines() : LiveData<PagedList<NewsHeadline>>  {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(
            newsHeadlinesDb.newsHeadlinesDao.getNewsHeadlines(),
            pagedListConfig
        ).setBoundaryCallback(newsBoundaryCallback).build()
    }

    fun getCompositeDisposable() : CompositeDisposable =
        newsBoundaryCallback.compositeDisposable
}
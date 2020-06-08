package maurya.devansh.headlines.data

import androidx.paging.PagedList
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import maurya.devansh.headlines.BuildConfig
import maurya.devansh.headlines.consts.PAGE_SIZE
import maurya.devansh.headlines.data.local.NewsHeadlinesDb
import maurya.devansh.headlines.data.remote.NewsHeadlinesApiService
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.util.PagingRequestHelper
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.math.ceil

/**
 * Created by Devansh on 7/6/20
 */

class NewsBoundaryCallback @Inject constructor(private val newsHeadlinesDb: NewsHeadlinesDb,
                                               private val newsHeadlinesApi: NewsHeadlinesApiService,
                                               val compositeDisposable: CompositeDisposable) :
        PagedList.BoundaryCallback<NewsHeadline>() {

    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private var pageCount: Int = 1
    private var totalRecords: Int = 0

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            val disposable = newsHeadlinesApi.getTopHeadlines("in", BuildConfig.API_KEY, pageCount)
                .subscribeOn(Schedulers.io())
                .subscribe ({ topHeadlines ->
                    Completable.fromAction {
                        newsHeadlinesDb.newsHeadlinesDao.insert(topHeadlines.articles.toTypedArray())
                    }.subscribeOn(Schedulers.io())
                        .subscribe({
                            Timber.d("Data added to DB: ${topHeadlines.articles}")
                            updatePageCount(topHeadlines.articles.size)
                            helperCallback.recordSuccess()
                    }, {
                        Timber.e("Error adding data to DB: $it")
                        helperCallback.recordFailure(it) })
                }, { throwable ->
                    Timber.e("Failed to load data: $throwable")
                    helperCallback.recordFailure(throwable)
                })
            compositeDisposable.add(disposable)
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: NewsHeadline) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            val disposable = newsHeadlinesApi.getTopHeadlines("in", BuildConfig.API_KEY, pageCount)
                .subscribeOn(Schedulers.io())
                .subscribe ({ topHeadlines ->
                    Completable.fromAction {
                        newsHeadlinesDb.newsHeadlinesDao.insert(topHeadlines.articles.toTypedArray()) }
                        .subscribeOn(Schedulers.io()).subscribe({
                            Timber.d("Data added to DB at end: ${topHeadlines.articles}")
                            updatePageCount(topHeadlines.articles.size)
                            helperCallback.recordSuccess()
                        }, {
                            Timber.e("Error adding data to DB: $it")
                            helperCallback.recordFailure(it)
                        })
                }, { throwable ->
                    Timber.e("Failed to load data: $throwable")
                    helperCallback.recordFailure(throwable)
                })
            compositeDisposable.add(disposable)
        }
    }

    private fun updatePageCount(newRecords: Int) {
        totalRecords += newRecords
        pageCount = ceil(totalRecords / PAGE_SIZE.toFloat()).toInt()
    }
}
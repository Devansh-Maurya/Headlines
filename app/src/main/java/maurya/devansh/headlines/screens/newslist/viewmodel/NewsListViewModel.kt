package maurya.devansh.headlines.screens.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import maurya.devansh.headlines.datasource.NewsDataSourceFactory
import maurya.devansh.headlines.model.NewsHeadline
import timber.log.Timber

/**
 * Created by Devansh on 6/6/20
 */

class NewsListViewModel(newsDataSourceFactory: NewsDataSourceFactory): ViewModel() {

    private val newsHeadlinesListLiveData: LiveData<PagedList<NewsHeadline>>
    private val compositeDisposable: CompositeDisposable

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        newsHeadlinesListLiveData = LivePagedListBuilder(newsDataSourceFactory, pagedListConfig).build()
        compositeDisposable = newsDataSourceFactory.compositeDisposable
    }

    fun getNewsHeadlines() : LiveData<PagedList<NewsHeadline>> {
        return newsHeadlinesListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("Composite disposable size: ${compositeDisposable.size()}")
        compositeDisposable.clear()
    }
}
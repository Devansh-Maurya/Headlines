package maurya.devansh.headlines.screens.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import maurya.devansh.headlines.data.NewsRepository
import maurya.devansh.headlines.model.NewsHeadline
import timber.log.Timber

/**
 * Created by Devansh on 6/6/20
 */

class NewsListViewModel(private val newsRepository: NewsRepository): ViewModel() {

    private val compositeDisposable = newsRepository.getCompositeDisposable()

    init {
        newsRepository.refreshData {}
    }

    fun getNewsHeadlines() : LiveData<PagedList<NewsHeadline>> {
        return newsRepository.getNewsHeadlines()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("Composite disposable size: ${compositeDisposable.size()}")
        compositeDisposable.clear()
    }
}
package maurya.devansh.headlines.screens.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.repository.news.NewsRepository
import timber.log.Timber

/**
 * Created by Devansh on 6/6/20
 */

class NewsListViewModel(newsRepository: NewsRepository): ViewModel() {

    private val newsHeadlinesListLiveData = newsRepository.getTopHeadlines()
    private val compositeDisposable = newsRepository.getCompositeDisposable()

    fun getNewsHeadlines() : LiveData<PagedList<NewsHeadline>> {
        return newsHeadlinesListLiveData
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("Composite disposable size: ${compositeDisposable.size()}")
        compositeDisposable.clear()
    }
}
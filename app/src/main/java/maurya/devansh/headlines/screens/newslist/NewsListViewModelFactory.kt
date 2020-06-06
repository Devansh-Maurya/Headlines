package maurya.devansh.headlines.screens.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import maurya.devansh.headlines.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Devansh on 6/6/20
 */

class NewsListViewModelFactory @Inject constructor(private val newsRepository: NewsRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Type")
    }
}
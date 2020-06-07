package maurya.devansh.headlines.screens.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.model.TopNewsHeadlines
import maurya.devansh.headlines.repository.NewsRepository

/**
 * Created by Devansh on 6/6/20
 */

class NewsListViewModel(private val newsRepository: NewsRepository): ViewModel() {

    fun getTopHeadlines(country: String, apiKey: String) : LiveData<List<NewsHeadline>> {
        val newsHeadlinesSource: LiveData<TopNewsHeadlines> =
            LiveDataReactiveStreams.fromPublisher(
                newsRepository.getTopHeadlines(country, apiKey)
                    .onErrorReturn(Function {
                        return@Function TopNewsHeadlines("Error fetching news", 0, listOf())
                    })
                    .subscribeOn(Schedulers.io())
            )

        return MediatorLiveData<List<NewsHeadline>>().apply {
            addSource(newsHeadlinesSource) {
                value = it.articles
            }
        }
    }
}
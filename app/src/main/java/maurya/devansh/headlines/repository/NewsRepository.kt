package maurya.devansh.headlines.repository

import io.reactivex.Flowable
import maurya.devansh.headlines.model.TopNewsHeadlines
import maurya.devansh.headlines.network.NewsHeadlinesApiService
import javax.inject.Inject

/**
 * Created by Devansh on 6/6/20
 */

class NewsRepository @Inject constructor(private val newsHeadlinesApi: NewsHeadlinesApiService) {

    fun getTopHeadlines(country: String, apiKey: String, page: Int = 1) : Flowable<TopNewsHeadlines> {
        return newsHeadlinesApi.getTopHeadlines(country, apiKey, page)
    }
}
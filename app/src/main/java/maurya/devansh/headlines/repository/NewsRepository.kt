package maurya.devansh.headlines.repository

import maurya.devansh.headlines.network.NewsHeadlinesApiService
import javax.inject.Inject

/**
 * Created by Devansh on 6/6/20
 */

class NewsRepository @Inject constructor(private val apiService: NewsHeadlinesApiService) {

    fun getTopHeadlines() {

    }
}
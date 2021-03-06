package maurya.devansh.headlines.data.remote

import io.reactivex.Flowable
import maurya.devansh.headlines.model.TopNewsHeadlines
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Devansh on 6/6/20
 */
interface NewsHeadlinesApiService {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("apiKey") apiKey: String,
                        @Query("page") page: Int = 1): Flowable<TopNewsHeadlines>


}
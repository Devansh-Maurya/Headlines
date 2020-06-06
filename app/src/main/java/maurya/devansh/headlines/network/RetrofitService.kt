package maurya.devansh.headlines.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Devansh on 6/6/20
 */
private const val BASE_URL = "https://newsapi.org"

object RetrofitService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
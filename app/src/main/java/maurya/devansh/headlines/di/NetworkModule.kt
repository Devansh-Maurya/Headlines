package maurya.devansh.headlines.di

import dagger.Module
import dagger.Provides
import maurya.devansh.headlines.network.NewsHeadlinesApiService
import maurya.devansh.headlines.network.RetrofitService
import javax.inject.Singleton

/**
 * Created by Devansh on 6/6/20
 */

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsHeadlinesApiService(): NewsHeadlinesApiService =
        RetrofitService.createService(NewsHeadlinesApiService::class.java)
}
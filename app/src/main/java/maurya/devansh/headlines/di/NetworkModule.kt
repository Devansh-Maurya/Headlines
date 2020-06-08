package maurya.devansh.headlines.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import maurya.devansh.headlines.data.remote.NewsHeadlinesApiService
import maurya.devansh.headlines.data.remote.RetrofitService
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

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}
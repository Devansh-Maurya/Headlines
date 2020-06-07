package maurya.devansh.headlines.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import maurya.devansh.headlines.db.NewsHeadlinesDb
import maurya.devansh.headlines.network.NewsHeadlinesApiService
import maurya.devansh.headlines.repository.news.NewsBoundaryCallback

/**
 * Created by Devansh on 6/6/20
 */

@Module
class RepositoryModule {

    @Provides
    fun provideNewsBoundaryCallback(newsHeadlinesDb: NewsHeadlinesDb,
                                    newsHeadlinesApi: NewsHeadlinesApiService,
                                    compositeDisposable: CompositeDisposable
    ): NewsBoundaryCallback =
        NewsBoundaryCallback(
            newsHeadlinesDb,
            newsHeadlinesApi,
            compositeDisposable
        )


}
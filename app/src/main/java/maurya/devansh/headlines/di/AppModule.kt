package maurya.devansh.headlines.di

import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import dagger.Binds
import dagger.Module
import maurya.devansh.headlines.datasource.NewsDataSourceFactory
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.screens.newslist.viewmodel.NewsListViewModelFactory

/**
 * Created by Devansh on 6/6/20
 */

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindNewsFeedViewModelFactory(factory: NewsListViewModelFactory)
            : ViewModelProvider.Factory

    @Binds
    internal abstract fun bindNewsDataSourceFactory(factory: NewsDataSourceFactory):
            DataSource.Factory<Int, NewsHeadline>
}
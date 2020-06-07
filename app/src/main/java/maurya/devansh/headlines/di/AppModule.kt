package maurya.devansh.headlines.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import maurya.devansh.headlines.HeadlinesApplication
import maurya.devansh.headlines.screens.newslist.viewmodel.NewsListViewModelFactory

/**
 * Created by Devansh on 6/6/20
 */

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindNewsFeedViewModelFactory(factory: NewsListViewModelFactory)
            : ViewModelProvider.Factory

//    @Binds
//    internal abstract fun bindNewsDataSourceFactory(factory: NewsDataSourceFactory):
//            DataSource.Factory<Int, NewsHeadline>

    @Binds
    abstract fun bindApplication(headlinesApplication: HeadlinesApplication):
            Context
}
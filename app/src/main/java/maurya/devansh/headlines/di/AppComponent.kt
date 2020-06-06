package maurya.devansh.headlines.di

import dagger.Component
import maurya.devansh.headlines.screens.newslist.NewsListFragment
import javax.inject.Singleton

/**
 * Created by Devansh on 6/6/20
 */

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(newsListFragment: NewsListFragment)
}
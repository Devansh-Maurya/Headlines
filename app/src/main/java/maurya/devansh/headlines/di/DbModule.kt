package maurya.devansh.headlines.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import maurya.devansh.headlines.db.NewsHeadlinesDb
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Devansh on 7/6/20
 */

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideNewsHeadlinesDb(@Named("ApplicationContext") context: Context): NewsHeadlinesDb {
        return Room.databaseBuilder(context, NewsHeadlinesDb::class.java, "news_headlines")
            .build()
    }

}
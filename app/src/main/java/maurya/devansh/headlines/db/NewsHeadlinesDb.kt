package maurya.devansh.headlines.db

import androidx.room.Database
import androidx.room.RoomDatabase
import maurya.devansh.headlines.model.NewsHeadline
import javax.inject.Singleton

/**
 * Created by Devansh on 7/6/20
 */

@Singleton
@Database(entities = [NewsHeadline::class], version = 1)
abstract class NewsHeadlinesDb : RoomDatabase() {
    abstract val newsHeadlinesDao: NewsHeadlineDao
}
package maurya.devansh.headlines.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import maurya.devansh.headlines.model.NewsHeadline

/**
 * Created by Devansh on 7/6/20
 */

@Dao
interface NewsHeadlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsHeadlines : Array<NewsHeadline>)

    @Query("SELECT * from NewsHeadline")
    fun getNewsHeadlines(): DataSource.Factory<Int, NewsHeadline>

}
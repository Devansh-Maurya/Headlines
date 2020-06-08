package maurya.devansh.headlines.data.local

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

    @Query("SELECT * FROM NewsHeadline ORDER BY publishedAt DESC")
    fun getNewsHeadlines(): DataSource.Factory<Int, NewsHeadline>

    @Query("DELETE FROM NewsHeadline")
    fun deleteAll()

}
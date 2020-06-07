package maurya.devansh.headlines.repository.news

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import maurya.devansh.headlines.db.NewsHeadlinesDb
import maurya.devansh.headlines.model.NewsHeadline
import javax.inject.Inject

/**
 * Created by Devansh on 6/6/20
 */

class NewsRepository @Inject constructor(private val newsHeadlinesDb: NewsHeadlinesDb,
                                         private val newsBoundaryCallback: NewsBoundaryCallback
) {

    fun getTopHeadlines() : LiveData<PagedList<NewsHeadline>> {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(
            newsHeadlinesDb.newsHeadlinesDao.getNewsHeadlines(),
            pagedListConfig
        ).setBoundaryCallback(newsBoundaryCallback).build()
    }

    fun getCompositeDisposable() : CompositeDisposable =
        newsBoundaryCallback.compositeDisposable
}
package maurya.devansh.headlines.datasource

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Devansh on 7/6/20
 */

class NewsDataSourceFactory @Inject constructor(private val newsRepository: NewsRepository,
                                                val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, NewsHeadline>() {

    override fun create(): DataSource<Int, NewsHeadline> {
        return NewsDataSource(newsRepository, compositeDisposable)
    }
}
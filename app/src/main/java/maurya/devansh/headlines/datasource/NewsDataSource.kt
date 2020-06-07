package maurya.devansh.headlines.datasource

import androidx.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import maurya.devansh.headlines.BuildConfig
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.repository.NewsRepository
import timber.log.Timber

/**
 * Created by Devansh on 7/6/20
 */

class NewsDataSource(private val newsRepository: NewsRepository,
                     private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Int, NewsHeadline>() {

    private var pageNumber: Int = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<NewsHeadline>) {

        newsRepository.getTopHeadlines("in", BuildConfig.API_KEY, pageNumber)
            .subscribeOn(Schedulers.io())
            .subscribe ({ topNewsHeadlines ->
                Timber.d("Load initial: $topNewsHeadlines")
                updatePageNumber()
                callback.onResult(topNewsHeadlines.articles, 0, topNewsHeadlines.totalResults)
            }, { throwable ->
                Timber.e("Initial load failed: $throwable")
                callback.onResult(listOf())
            }).also { disposable ->
                compositeDisposable.add(disposable)
            }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<NewsHeadline>) {
        newsRepository.getTopHeadlines("in", BuildConfig.API_KEY, pageNumber)
            .subscribeOn(Schedulers.io())
            .subscribe ({ topNewsHeadlines ->
                Timber.d("Load after: $topNewsHeadlines")
                updatePageNumber()
                callback.onResult(topNewsHeadlines.articles)
            }, { throwable ->
                Timber.e("Initial load failed: $throwable")
                callback.onResult(listOf())
            }).also { disposable ->
                compositeDisposable.add(disposable)
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<NewsHeadline>) { }

    override fun getKey(item: NewsHeadline): Int = pageNumber

    private fun updatePageNumber() {
        pageNumber++
    }
}
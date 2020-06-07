package maurya.devansh.headlines.screens.newslist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import maurya.devansh.headlines.R
import maurya.devansh.headlines.model.NewsHeadline

/**
 * Created by Devansh on 7/6/20
 */

class NewsListAdapter : PagedListAdapter<NewsHeadline ,NewsViewHolder>(NewsHeadline.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position) ?: NewsHeadline())
    }
}
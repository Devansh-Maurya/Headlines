package maurya.devansh.headlines.screens.newslist.recyclerview

import android.view.View
import coil.api.load
import kotlinx.android.synthetic.main.item_news.view.*
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.screens.BaseViewHolder

/**
 * Created by Devansh on 7/6/20
 */

class NewsViewHolder(itemView: View): BaseViewHolder<NewsHeadline>(itemView) {

    override fun bind(item: NewsHeadline) {
        itemView.apply {
            textViewTitle.text = item.title
            imageViewNews.load(item.urlToImage)
        }
    }
}
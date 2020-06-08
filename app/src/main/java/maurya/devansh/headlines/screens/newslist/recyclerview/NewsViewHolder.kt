package maurya.devansh.headlines.screens.newslist.recyclerview

import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import coil.api.load
import kotlinx.android.synthetic.main.item_news.view.*
import maurya.devansh.headlines.R
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.screens.BaseViewHolder
import maurya.devansh.headlines.util.combineAuthorAndSource
import maurya.devansh.headlines.util.getBoldText
import maurya.devansh.headlines.util.getFormattedTime

/**
 * Created by Devansh on 7/6/20
 */

class NewsViewHolder(itemView: View): BaseViewHolder<NewsHeadline>(itemView) {

    override fun bind(item: NewsHeadline) {
        itemView.apply {
            textViewTitle.text = item.title
            textViewDescription.text = item.description
            textViewAuthorSource.text =
                getBoldText(combineAuthorAndSource(item.author ?: "", item.source.name ?: ""))
            textViewPublishedTime.text = getFormattedTime(item.publishedAt ?: "")
            imageViewNews.load(item.urlToImage)

            setOnClickListener {
                openNewsArticle(item.url)
            }
        }
    }

    private fun openNewsArticle(newsUrl: String) {
        val context = itemView.context

        val customTabsIntent = CustomTabsIntent.Builder().run {
            setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            addDefaultShareMenuItem()
            setShowTitle(true)
            build()
        }
        // TODO: Edge case if chrome is not installed. The other browser may not support custom tabs
        customTabsIntent.launchUrl(context, Uri.parse(newsUrl))
    }
}
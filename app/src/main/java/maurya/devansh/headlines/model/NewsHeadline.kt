package maurya.devansh.headlines.model

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Devansh on 6/6/20
 */

data class NewsHeadline(
    val source: Source = Source(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
) {
    data class Source(
        val id: String = "",
        val name: String = ""
    )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsHeadline>() {
            override fun areItemsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean =
                oldItem == newItem
        }
    }
}
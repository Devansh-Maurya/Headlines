package maurya.devansh.headlines.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Devansh on 6/6/20
 */

// All members are vars because Room complaints about no setter for vals
@Entity
data class NewsHeadline(
    @PrimaryKey var url: String = "",
    @Embedded var source: Source = Source(),
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = "",
    var sourceName: String? = ""
) {

    init {
        sourceName = source.name
    }

    data class Source(
        var id: String? = "",
        var name: String? = ""
    )

    companion object {
        var DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsHeadline>() {
            override fun areItemsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: NewsHeadline, newItem: NewsHeadline): Boolean =
                oldItem == newItem
        }
    }
}
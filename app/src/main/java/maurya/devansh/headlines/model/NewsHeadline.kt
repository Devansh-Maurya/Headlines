package maurya.devansh.headlines.model

/**
 * Created by Devansh on 6/6/20
 */

data class NewsHeadline(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) {
    data class Source(
        val id: String,
        val name: String
    )
}
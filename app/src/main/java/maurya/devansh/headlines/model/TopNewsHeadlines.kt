package maurya.devansh.headlines.model

/**
 * Created by Devansh on 6/6/20
 */

data class TopNewsHeadlines(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsHeadline>
)
package maurya.devansh.headlines.util

import android.text.format.DateUtils
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Devansh on 8/6/20
 */

internal fun combineAuthorAndSource(author: String, sourceName: String) : String {
    val comma = if (author.isNotBlank() and sourceName.isNotBlank()) ", " else ""
    return StringBuilder().append(author).append(comma).append(sourceName).toString()
}

internal fun getFormattedTime(publishedTime: String): CharSequence {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(publishedTime)
    val currentTime = Calendar.getInstance().timeInMillis
    return DateUtils.getRelativeTimeSpanString(
        date?.time ?: currentTime, currentTime, DateUtils.MINUTE_IN_MILLIS)
}

internal fun getBoldText(input: String): CharSequence =
    buildSpannedString {
        bold {
            append(input)
        }
    }


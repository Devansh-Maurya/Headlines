package maurya.devansh.headlines.util

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Devansh on 8/6/20
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class UiUtilsTestMocked {

    @Test
    fun getFormattedTime_currentTimeMinusFourHours_fourHoursAgo() {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val fourHoursAgoTime = Calendar.getInstance().run {
            add(Calendar.HOUR, -4)
            time
        }
        val fourHoursAgoTimeFormatted = inputFormat.format(fourHoursAgoTime)

        Assert.assertEquals("4 hours ago", getFormattedTime(fourHoursAgoTimeFormatted))
    }

    @Test
    fun getFormattedTime_currentTimeMinusOneDay_yesterday() {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val fourHoursAgoTime = Calendar.getInstance().run {
            add(Calendar.DAY_OF_YEAR, -1)
            time
        }
        val fourHoursAgoTimeFormatted = inputFormat.format(fourHoursAgoTime)

        Assert.assertEquals("Yesterday", getFormattedTime(fourHoursAgoTimeFormatted))
    }

    @Test
    fun getFormattedTime_currentTimeMinusTwoDays_twoDaysAgo() {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val fourHoursAgoTime = Calendar.getInstance().run {
            add(Calendar.DAY_OF_YEAR, -2)
            time
        }
        val fourHoursAgoTimeFormatted = inputFormat.format(fourHoursAgoTime)

        Assert.assertEquals("2 days ago", getFormattedTime(fourHoursAgoTimeFormatted))
    }

}
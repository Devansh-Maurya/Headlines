package maurya.devansh.headlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import maurya.devansh.headlines.model.NewsHeadline
import maurya.devansh.headlines.network.NewsHeadlinesApiService
import maurya.devansh.headlines.network.RetrofitService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsHeadlinesService = RetrofitService.createService(NewsHeadlinesApiService::class.java)

        val headlinesObservable = newsHeadlinesService.getTopHeadlines("in", BuildConfig.API_KEY)
        val res = headlinesObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                Log.d("MainActivity", result.toString())
            }
            .subscribe()

    }
}

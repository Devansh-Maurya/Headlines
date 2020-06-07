package maurya.devansh.headlines

import android.app.Application
import coil.Coil
import coil.ImageLoader
import maurya.devansh.headlines.di.AppComponent
import maurya.devansh.headlines.di.ContextModule
import maurya.devansh.headlines.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Devansh on 6/6/20
 */

class HeadlinesApplication: Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Coil.setImageLoader(
            ImageLoader.Builder(this)
                .crossfade(true)
                .crossfade(300)
                .error(R.drawable.ic_news)
                .build())
    }
}
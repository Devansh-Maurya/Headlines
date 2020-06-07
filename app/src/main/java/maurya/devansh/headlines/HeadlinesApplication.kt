package maurya.devansh.headlines

import android.app.Application
import maurya.devansh.headlines.di.AppComponent
import maurya.devansh.headlines.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Devansh on 6/6/20
 */

class HeadlinesApplication: Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
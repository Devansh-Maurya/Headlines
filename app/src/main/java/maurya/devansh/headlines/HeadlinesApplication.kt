package maurya.devansh.headlines

import android.app.Application
import maurya.devansh.headlines.di.DaggerAppComponent

/**
 * Created by Devansh on 6/6/20
 */

class HeadlinesApplication: Application() {

    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
}
package maurya.devansh.headlines.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Devansh on 7/6/20
 */

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Named("ApplicationContext")
    fun provideContext(): Context = context

}
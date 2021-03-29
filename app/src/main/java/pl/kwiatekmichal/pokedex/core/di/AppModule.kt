package pl.kwiatekmichal.pokedex.core.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.provider.ActivityProvider

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideActivityProvider(
        application: Application
    ): ActivityProvider {
        return ActivityProvider(application)
    }

    @Provides
    fun provideNavOptions(): NavOptions {
        return navOptions {
            anim { enter = R.anim.nav_default_enter_anim }
            anim { exit = R.anim.nav_default_exit_anim }
            anim { popEnter = R.anim.nav_default_pop_enter_anim }
            anim { popExit = R.anim.nav_default_pop_exit_anim }
        }
    }

    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
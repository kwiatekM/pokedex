package pl.kwiatekmichal.pokedex.core.di

import android.app.Application
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.provider.ActivityProvider
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AppModule4 {
    @Provides
    @Singleton
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
}
package pl.kwiatekmichal.pokedex.core.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.navigation.navOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapper
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapperImpl
import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapper
import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapperImpl
import pl.kwiatekmichal.pokedex.core.navigation.FragmentNavigator
import pl.kwiatekmichal.pokedex.core.navigation.FragmentNavigatorImpl
import pl.kwiatekmichal.pokedex.core.network.NetworkStateProvider
import pl.kwiatekmichal.pokedex.core.network.NetworkStateProviderImpl
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

    factory {
        navOptions {
            anim { enter = R.anim.fragment_fade_enter }
            anim { exit = R.anim.fragment_fade_exit }
            anim { popEnter = R.anim.fragment_open_enter }
            anim { popExit = R.anim.fragment_open_exit }
        }
    }
    factory<FragmentNavigator> {
        FragmentNavigatorImpl(
            activityProvider = get(),
            navHostFragmentRes = R.id.nav_host_fragment,
            homeDestinationRes = R.id.navigation_home,
            defaultNavOptions = get()
        )
    }
    factory { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    factory<NetworkStateProvider> { NetworkStateProviderImpl(get()) }
    factory<ErrorWrapper> { ErrorWrapperImpl() }
    factory<ErrorMapper> { ErrorMapperImpl(androidContext()) }
}
package pl.kwiatekmichal.pokedex.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import pl.kwiatekmichal.pokedex.core.navigation.FragmentNavigator
import pl.kwiatekmichal.pokedex.core.navigation.FragmentNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class App3Module {
    @Binds
    abstract fun bindFragmentNavigator(
        fragmentNavigatorImpl: FragmentNavigatorImpl
    ): FragmentNavigator
}
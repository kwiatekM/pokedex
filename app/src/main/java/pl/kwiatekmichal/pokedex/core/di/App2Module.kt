package pl.kwiatekmichal.pokedex.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapper
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapperImpl
import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapper
import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapperImpl
import pl.kwiatekmichal.pokedex.core.network.NetworkStateProvider
import pl.kwiatekmichal.pokedex.core.network.NetworkStateProviderImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class App2Module {
    @Binds
    abstract fun bindNetworkStateProvider(
        networkStateProviderImpl: NetworkStateProviderImpl
    ): NetworkStateProvider

    @Binds
    abstract fun bindErrorWrapper(
        errorWrapperImpl: ErrorWrapperImpl
    ): ErrorWrapper

    @Binds
    abstract fun bindErrorMapper(
        errorMapperImpl: ErrorMapperImpl
    ): ErrorMapper
}
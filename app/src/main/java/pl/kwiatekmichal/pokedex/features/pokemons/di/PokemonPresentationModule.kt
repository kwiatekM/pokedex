package pl.kwiatekmichal.pokedex.features.pokemons.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator.PokemonNavigator
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator.PokemonNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonPresentationModule {
    @Binds
    abstract fun bindPokemonNavigator(
        pokemonNavigatorImpl: PokemonNavigatorImpl
    ): PokemonNavigator
}
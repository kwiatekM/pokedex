package pl.kwiatekmichal.pokedex.features.pokemons.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.features.pokemons.data.PokemonRepositoryImpl
import pl.kwiatekmichal.pokedex.features.pokemons.domain.PokemonRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonDataModule {
    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}
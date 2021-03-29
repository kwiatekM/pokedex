package pl.kwiatekmichal.pokedex.features.pokemons.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.kwiatekmichal.pokedex.features.pokemons.domain.*

@Module
@InstallIn(FragmentComponent::class)
object PokemonDomainModule {
    @Provides
    fun bindGetPokemonsUseCase(
        repository: PokemonRepository
    ): GetPokemonsUseCase {
        return GetPokemonsUseCase(pokemonRepository = repository)
    }

    @Provides
    fun bindSaveFavouritePokemonUseCase(
        repository: PokemonRepository
    ): SaveFavouritePokemonUseCase {
        return SaveFavouritePokemonUseCase(pokemonRepository = repository)
    }

    @Provides
    fun bindSaveCaughtPokemonUseCase(
        repository: PokemonRepository
    ): SaveCaughtPokemonUseCase {
        return SaveCaughtPokemonUseCase(pokemonRepository = repository)
    }

    @Provides
    fun bindGetPokemonUseCase(
        repository: PokemonRepository
    ): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository = repository)
    }
}
package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase

class SaveFavouritePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<Unit, Long>() {
    override suspend fun action(params: Long) {
        return pokemonRepository.saveFavouritePokemon(params)
    }
}
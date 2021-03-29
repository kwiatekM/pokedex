package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase
import javax.inject.Inject

class SaveFavouritePokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<Unit, Long>() {
    override suspend fun action(params: Long) {
        return pokemonRepository.saveFavouritePokemon(params)
    }
}
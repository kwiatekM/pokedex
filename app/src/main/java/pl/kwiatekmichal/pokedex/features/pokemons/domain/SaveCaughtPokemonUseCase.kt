package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase

class SaveCaughtPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<Unit, Long>() {
    override suspend fun action(params: Long) {
        return pokemonRepository.saveCaughtPokemon(params)
    }
}
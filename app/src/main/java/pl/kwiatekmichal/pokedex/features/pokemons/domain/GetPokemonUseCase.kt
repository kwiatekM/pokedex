package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<Pokemon?, Long>() {
    override suspend fun action(params: Long): Pokemon? {
        return pokemonRepository.getPokemon(params)
    }
}
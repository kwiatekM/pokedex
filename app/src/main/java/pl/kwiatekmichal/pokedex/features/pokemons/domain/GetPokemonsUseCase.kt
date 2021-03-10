package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
): BaseUseCase<List<Pokemon>, Unit>() {
    override suspend fun action(params: Unit): List<Pokemon> {
        return pokemonRepository.getPokemonSimpleList()
    }
}
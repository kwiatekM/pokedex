package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.core.base.BaseUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<Pokemon?, Long>() {
    override suspend fun action(params: Long): Pokemon? {
        return pokemonRepository.getPokemon(params)
    }
}
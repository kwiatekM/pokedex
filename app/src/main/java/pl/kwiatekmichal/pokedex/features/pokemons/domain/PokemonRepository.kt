package pl.kwiatekmichal.pokedex.features.pokemons.domain

import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonSimpleList(): List<Pokemon>
    suspend fun saveFavouritePokemon(id: Long)
    suspend fun saveCaughtPokemon(id: Long)
    suspend fun getPokemon(pokemonId: Long): Pokemon?
}
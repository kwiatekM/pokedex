package pl.kwiatekmichal.pokedex.core.api

import pl.kwiatekmichal.pokedex.core.api.model.PokemonListResponseWrapper
import pl.kwiatekmichal.pokedex.core.api.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokemonListResponseWrapper

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonId: Long
    ): PokemonResponse
}
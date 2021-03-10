package pl.kwiatekmichal.pokedex.core.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kwiatekmichal.pokedex.core.extension.toId
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
) {
    companion object

    fun toPokemon(pokemon: Pokemon?) = Pokemon(
        id = url.toId(),
        name = name,
        isFavourite = pokemon?.isFavourite ?: false,
        isCaught = pokemon?.isCaught ?: false,
        height = pokemon?.height ?: 0.0,
        weight = pokemon?.weight ?: 0.0,
        types = pokemon?.types ?: emptyList()
    )
}

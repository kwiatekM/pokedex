package pl.kwiatekmichal.pokedex.core.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kwiatekmichal.pokedex.core.extension.toId
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.PokemonType

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "base_experience")
    val baseExperience: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Long,
    @Json(name = "is_default")
    val isDefault: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "weight")
    val weight: Int,
    @Json(name = "types")
    val types: List<PokemonTypesResponse>
) {
    companion object

    fun toPokemon(pokemon: Pokemon?) = Pokemon(
        id = id,
        name = name,
        isFavourite = pokemon?.isFavourite ?: false,
        isCaught = pokemon?.isCaught ?: false,
        height = height / 10.toDouble(),
        weight = weight / 10.toDouble(),
        types = toPokemonTypes(types)
    )

    private fun toPokemonTypes(types: List<PokemonTypesResponse>): List<PokemonType> {
        return types.map {
            PokemonType(
                name = it.type.name,
                id = it.type.url.toId(),
                order = it.slot
            )
        }.sortedBy { it.order }
    }
}

@JsonClass(generateAdapter = true)
data class PokemonTypesResponse(
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: SimpleTypeResponse
)

@JsonClass(generateAdapter = true)
data class SimpleTypeResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

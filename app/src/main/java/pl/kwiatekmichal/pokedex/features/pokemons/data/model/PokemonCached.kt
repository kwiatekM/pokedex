package pl.kwiatekmichal.pokedex.features.pokemons.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

@Entity
data class PokemonCached(
    @PrimaryKey
    val pokemonId: Long,
    val name: String,
    var isFavourite: Boolean,
    var isCaught: Boolean,
    val height: Double,
    val weight: Double
) {
    constructor(pokemon: Pokemon) : this(
        pokemonId = pokemon.id,
        name = pokemon.name,
        isFavourite = pokemon.isFavourite,
        isCaught = pokemon.isCaught,
        height = pokemon.height,
        weight = pokemon.weight,
    )

    fun toPokemon() = Pokemon(
        id = pokemonId,
        name = name,
        isFavourite = isFavourite,
        isCaught = isCaught,
        height = height,
        weight = weight,
        types = emptyList()
    )
}

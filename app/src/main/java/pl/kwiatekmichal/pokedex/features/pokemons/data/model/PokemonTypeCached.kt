package pl.kwiatekmichal.pokedex.features.pokemons.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "typeId"])
data class PokemonTypeCached(
    val pokemonId: Long,
    val typeId: Long
)

package pl.kwiatekmichal.pokedex.features.pokemons.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.PokemonType

@Entity
data class TypeCached(
    @PrimaryKey
    val typeId: Long,
    val name: String,
    val order: Int
) {
    constructor(type: PokemonType) : this(
        typeId = type.id,
        name = type.name,
        order = type.order
    )

    fun toPokemonType() = PokemonType(
        id = typeId,
        name = name,
        order = order
    )
}
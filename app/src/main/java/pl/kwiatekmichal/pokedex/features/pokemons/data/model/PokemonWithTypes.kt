package pl.kwiatekmichal.pokedex.features.pokemons.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

data class PokemonWithTypes(
    @Embedded
    val pokemon: PokemonCached,
    @Relation(
        parentColumn = "pokemonId",
//        entity = TypeCached::class,
        entityColumn = "typeId",
        associateBy = Junction(
            value = PokemonTypeCached::class
//            ,
//            parentColumn = "pokemonId",
//            entityColumn = "typeId"
        )
    )
    val types: List<TypeCached> = ArrayList()
) {
    fun toPokemon() = Pokemon(
        id = pokemon.pokemonId,
        name = pokemon.name,
        isFavourite = pokemon.isFavourite,
        isCaught = pokemon.isCaught,
        height = pokemon.height,
        weight = pokemon.weight,
        types = types.map { it.toPokemonType() }
    )
}

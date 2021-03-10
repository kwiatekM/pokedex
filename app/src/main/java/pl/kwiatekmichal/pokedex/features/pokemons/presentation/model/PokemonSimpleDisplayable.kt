package pl.kwiatekmichal.pokedex.features.pokemons.presentation.model

import pl.kwiatekmichal.pokedex.core.extension.firstLetterToUpperCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

data class PokemonSimpleDisplayable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val isFavourite: Boolean,
    val isCaught: Boolean
) {
    constructor(pokemon: Pokemon) : this(
        id = pokemon.id,
        name = pokemon.name.firstLetterToUpperCase(),
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
        isFavourite = pokemon.isFavourite,
        isCaught = pokemon.isCaught
    )
}

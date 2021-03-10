package pl.kwiatekmichal.pokedex.features.pokemons.presentation.model

import pl.kwiatekmichal.pokedex.core.extension.firstLetterToUpperCase
import pl.kwiatekmichal.pokedex.core.extension.format
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon

data class PokemonDisplayable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val isFavourite: Boolean,
    val isCaught: Boolean,
    val height: String,
    val weight: String
) {
    constructor(pokemon: Pokemon) : this(
        id = pokemon.id,
        name = pokemon.name.firstLetterToUpperCase(),
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
        isFavourite = pokemon.isFavourite,
        isCaught = pokemon.isCaught,
        height = "${pokemon.height.format(minimumFractionDigits = 1, maximumFractionalDigits = 2)}m",
        weight = "${pokemon.weight.format(minimumFractionDigits = 1, maximumFractionalDigits = 2)}kg"
    )
}
package pl.kwiatekmichal.pokedex.features.pokemons.presentation.model

interface PokemonDetailsDisplayable {
}

data class PokemonDetailsHeaderImagesDisplayable(
    val images: List<String>,
    val isFavourite: Boolean,
    val isCaught: Boolean
) : PokemonDetailsDisplayable {
    constructor(pokemon: PokemonDisplayable) : this(
        images = listOf<String>("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"),
        isFavourite = pokemon.isFavourite,
        isCaught = pokemon.isCaught
    )
}

data class PokemonDetailsGeneralDisplayable(
    val name: String,
    val types: List<String>,
    val height: String,
    val weight: String
): PokemonDetailsDisplayable {
    constructor(pokemon: PokemonDisplayable): this(
        name = pokemon.name,
        types = emptyList(),
        height = pokemon.height,
        weight = pokemon.weight
    )
}
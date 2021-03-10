package pl.kwiatekmichal.pokedex.features.pokemons.domain.model

data class Pokemon(
    val name: String,
    val id: Long,
    var isFavourite: Boolean = false,
    var isCaught: Boolean = false,
    val height: Double,
    val weight: Double,
    val types: List<PokemonType>
)

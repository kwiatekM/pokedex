package pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator

interface PokemonNavigator {
    fun openPokemonDetailsScreen(pokemonId: Long)
    fun goBack()
}
package pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator

import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.navigation.FragmentNavigator
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.PokemonFragment
import javax.inject.Inject

class PokemonNavigatorImpl @Inject constructor(
    private val fragmentNavigator: FragmentNavigator
) : PokemonNavigator {
    override fun openPokemonDetailsScreen(pokemonId: Long) {
        fragmentNavigator.navigateTo(
            R.id.action_navigate_from_screen_pokemons_to_screen_pokemon_details,
            PokemonFragment.POKEMON_ID_KEY to pokemonId
        )
    }

    override fun goBack() {
        fragmentNavigator.goBack()
    }
}
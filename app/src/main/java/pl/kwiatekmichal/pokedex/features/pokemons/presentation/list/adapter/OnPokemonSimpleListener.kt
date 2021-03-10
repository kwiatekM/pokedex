package pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter

import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable

interface OnPokemonSimpleListener {
    fun onPokemonClicked(pokemonId: Long)
    fun onFavouritePokemonClicked(pokemon: PokemonDisplayable)
    fun onCatchPokemonClicked(pokemon: PokemonDisplayable)
}
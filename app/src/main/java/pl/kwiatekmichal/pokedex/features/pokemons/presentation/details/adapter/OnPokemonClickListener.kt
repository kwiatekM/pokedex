package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter

import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsHeaderImagesDisplayable

interface OnPokemonClickListener {
    fun onFavouritePokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable)
    fun onDislikePokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable)
    fun onCatchPokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable)
    fun onNotCaughtPokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable)
}
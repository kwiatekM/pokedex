package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.kwiatekmichal.pokedex.databinding.ItemFragmentPokemonGeneralDataBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsDisplayable
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsGeneralDisplayable

class HeaderGeneralDataViewHolder(
    itemView: View
): RecyclerView.ViewHolder(
    itemView
) {
    private val binding = ItemFragmentPokemonGeneralDataBinding.bind(itemView)

    fun bind(data: PokemonDetailsDisplayable?) {
        with(binding) {
            (data as? PokemonDetailsGeneralDisplayable)?.let { item ->
                pokemonName.text = item.name
                pokemonHeight.text = item.height
                pokemonWeight.text = item.weight
            }
        }
    }
}
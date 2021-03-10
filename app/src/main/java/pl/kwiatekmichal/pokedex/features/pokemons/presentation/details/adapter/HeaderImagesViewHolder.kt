package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.extension.setOnDebouncedClickListener
import pl.kwiatekmichal.pokedex.databinding.ItemFragmentPokemonHeaderBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsDisplayable
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsHeaderImagesDisplayable

class HeaderImagesViewHolder(
    itemView: View,
    private val context: Context
) : RecyclerView.ViewHolder(
    itemView
) {
    private val binding = ItemFragmentPokemonHeaderBinding.bind(itemView)

    fun bind(
        data: PokemonDetailsDisplayable?,
        listener: OnPokemonClickListener?
    ) {
        with(binding) {
            imagesViewPager.adapter =
                ItemImageAdapter((data as PokemonDetailsHeaderImagesDisplayable).images)
//            imagesTabIndicator.setupWithViewPager(imagesViewPager)
            favourite.setImageDrawable(
                ContextCompat.getDrawable(
                    context, when {
                        data.isFavourite -> R.drawable.ic_favorite
                        else -> R.drawable.ic_favorite_border
                    }
                )
            )
            caught.setImageDrawable(
                ContextCompat.getDrawable(
                    context, when {
                        data.isCaught -> R.drawable.ic_star
                        else -> R.drawable.ic_star_border
                    }
                )
            )
            listener?.let {
                favourite.setOnDebouncedClickListener {
                    when {
                        data.isFavourite -> it.onDislikePokemonClicked(data)
                        else -> it.onFavouritePokemonClicked(data)
                    }
                }
                caught.setOnDebouncedClickListener {
                    when {
                        data.isCaught -> it.onNotCaughtPokemonClicked(data)
                        else -> it.onCatchPokemonClicked(data)
                    }
                }
            }
        }
    }
}
package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.extension.inflate
import pl.kwiatekmichal.pokedex.databinding.ItemFragmentPokemonHeaderImageBinding

class ItemImageAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<ItemImageAdapter.ItemImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageViewHolder {
        return ItemImageViewHolder(parent.inflate(R.layout.item_fragment_pokemon_header_image))
    }

    override fun onBindViewHolder(holder: ItemImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ItemImageViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ) {
        private val binding = ItemFragmentPokemonHeaderImageBinding.bind(itemView)

        fun bind(imageUrl: String) {
            with(binding) {
                image.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.pokemon_placeholder)
                    error(R.drawable.ic_image_red)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}
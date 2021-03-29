package pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.extension.inflate
import pl.kwiatekmichal.pokedex.core.extension.setOnDebouncedClickListener
import pl.kwiatekmichal.pokedex.databinding.ItemFragmentPokemonListBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable
import javax.inject.Inject

class PokemonsAdapter @Inject constructor() :
    RecyclerView.Adapter<PokemonsAdapter.PokemonSimpleListViewHolder>() {

    private val pokemons by lazy { mutableListOf<PokemonDisplayable>() }
    private var listener: OnPokemonSimpleListener? = null

    fun setPokemons(pokemons: List<PokemonDisplayable>) {
        if (pokemons.isNotEmpty()) {
            this.pokemons.clear()
        }
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }

    fun setOnPokemonsClickListener(listener: OnPokemonSimpleListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSimpleListViewHolder {
        return PokemonSimpleListViewHolder(
            parent.inflate(R.layout.item_fragment_pokemon_list),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: PokemonSimpleListViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon, listener)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class PokemonSimpleListViewHolder(
        itemView: View,
        private val context: Context
    ) : RecyclerView.ViewHolder(
        itemView
    ) {
        private val binding = ItemFragmentPokemonListBinding.bind(itemView)

        fun bind(
            pokemon: PokemonDisplayable,
            listener: OnPokemonSimpleListener?
        ) {
            with(binding) {
                pokemonImage.load(pokemon.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.pokemon_placeholder)
                    error(R.drawable.ic_image_red)
                    transformations(CircleCropTransformation())
                }
                pokemonName.text = pokemon.name
                favourite.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        when {
                            pokemon.isFavourite -> R.drawable.ic_favorite
                            else -> R.drawable.ic_favorite_border
                        }
                    )
                )
                catched.setImageDrawable(
                    ContextCompat.getDrawable(
                        context, when {
                            pokemon.isCaught -> R.drawable.ic_star
                            else -> R.drawable.ic_star_border
                        }
                    )
                )
                listener?.let {
                    val pokemonId = pokemon.id
                    root.setOnDebouncedClickListener { it.onPokemonClicked(pokemonId) }
                    favourite.setOnDebouncedClickListener { it.onFavouritePokemonClicked(pokemon) }
                    catched.setOnDebouncedClickListener { it.onCatchPokemonClicked(pokemon) }
                }
            }
        }
    }
}
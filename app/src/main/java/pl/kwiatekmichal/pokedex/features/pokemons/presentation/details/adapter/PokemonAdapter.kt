package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.extension.inflate
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsDisplayable
import javax.inject.Inject

class PokemonAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HeaderImageViewType = 0
        const val HeaderMainDataViewType = 1
    }

    private val model by lazy { mutableListOf<PokemonDetailsDisplayable>() }
    private var listener: OnPokemonClickListener? = null

    fun setModel(model: List<PokemonDetailsDisplayable>) {
        if (model.isNotEmpty()) {
            this.model.clear()
        }
        this.model.addAll(model)
        notifyDataSetChanged()
    }

    fun setOnPokemonClickListener(listener: OnPokemonClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HeaderImageViewType -> HeaderImagesViewHolder(
                parent.inflate(R.layout.item_fragment_pokemon_header),
                parent.context
            )
            else -> HeaderGeneralDataViewHolder(parent.inflate(R.layout.item_fragment_pokemon_general_data))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HeaderImageViewType -> (holder as HeaderImagesViewHolder).bind(
                model[position],
                listener
            )
            else -> (holder as HeaderGeneralDataViewHolder).bind(model[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HeaderImageViewType
            1 -> HeaderMainDataViewType
            else -> super.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }
}
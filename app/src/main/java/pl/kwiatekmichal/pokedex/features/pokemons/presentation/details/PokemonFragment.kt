package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details

import android.os.Bundle
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.base.BaseFragment
import pl.kwiatekmichal.pokedex.core.extension.viewBinding
import pl.kwiatekmichal.pokedex.core.util.SpaceItemDecoration
import pl.kwiatekmichal.pokedex.databinding.FragmentPokemonBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter.OnPokemonClickListener
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter.PokemonAdapter
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsGeneralDisplayable
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDetailsHeaderImagesDisplayable
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable
import javax.inject.Inject

@AndroidEntryPoint
class PokemonFragment : BaseFragment<PokemonViewModel>(
    layoutId = R.layout.fragment_pokemon
), OnPokemonClickListener {
    companion object {
        const val POKEMON_ID_KEY = "pokemonIdKey"
    }

    override val TAG: String = "PokemonFragment"
    override val viewModel: PokemonViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)
    private val binding by viewBinding(FragmentPokemonBinding::bind)

    @Inject
    lateinit var detailsAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifyAboutData()
    }

    private fun notifyAboutData() {
        arguments?.getLong(POKEMON_ID_KEY)?.let { viewModel.onPokemonPassed(it) }
    }

    override fun initViews() {
        super.initViews()
        initList()
    }

    private fun initList() {
        with(binding.detailsList) {
            setHasFixedSize(true)
            adapter = detailsAdapter
            addItemDecoration(SpaceItemDecoration(offset = resources.getDimensionPixelOffset(R.dimen.margin_16dp)))
        }
        detailsAdapter.setOnPokemonClickListener(this)
    }

    override fun observeViewState() {
        super.observeViewState()
        observePokemon()
    }

    private fun observePokemon() {
        viewModel.pokemon.observe(this) { showDetails(it) }
    }

    override fun onPendingState() {
        super.onPendingState()
        binding.progressBar.show()
    }

    override fun onIdleState() {
        super.onIdleState()
        binding.progressBar.hide()
    }

    private fun showDetails(pokemon: PokemonDisplayable) {
        detailsAdapter.setModel(
            listOf(
                PokemonDetailsHeaderImagesDisplayable(pokemon = pokemon),
                PokemonDetailsGeneralDisplayable(pokemon = pokemon)
            )
        )
    }

    override fun onFavouritePokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable) {
        TODO("Not yet implemented")
    }

    override fun onDislikePokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable) {
        TODO("Not yet implemented")
    }

    override fun onCatchPokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable) {
        TODO("Not yet implemented")
    }

    override fun onNotCaughtPokemonClicked(pokemon: PokemonDetailsHeaderImagesDisplayable) {
        TODO("Not yet implemented")
    }
}
package pl.kwiatekmichal.pokedex.features.pokemons.presentation.list

import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.base.BaseFragment
import pl.kwiatekmichal.pokedex.core.extension.viewBinding
import pl.kwiatekmichal.pokedex.core.util.SpaceItemDecoration
import pl.kwiatekmichal.pokedex.databinding.FragmentPokemonListBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter.OnPokemonSimpleListener
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter.PokemonsAdapter
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<PokemonListViewModel>(
    layoutId = R.layout.fragment_pokemon_list
), OnPokemonSimpleListener {
    override val TAG: String = "PokemonListFragment"
    override val viewModel: PokemonListViewModel by viewModels()//hiltNavGraphViewModels(R.id.mobile_navigation)
    private val binding by viewBinding(FragmentPokemonListBinding::bind)
    @Inject
    lateinit var pokemonsAdapter: PokemonsAdapter

    override fun initViews() {
        super.initViews()
        initList()
    }

    private fun initList() {
        with(binding.pokemonSimpleList) {
            setHasFixedSize(true)
            adapter = pokemonsAdapter
            addItemDecoration(SpaceItemDecoration(offset = resources.getDimensionPixelOffset(R.dimen.margin_16dp)))
        }
        pokemonsAdapter.setOnPokemonsClickListener(this)
    }

    override fun observeViewState() {
        super.observeViewState()
        observePokemonSimpleList()
    }

    private fun observePokemonSimpleList() {
        viewModel.pokemons.observe(this) {
            pokemonsAdapter.setPokemons(it)
        }
    }

    override fun onPendingState() {
        super.onPendingState()
        binding.pokemonSimpleProgress.show()
    }

    override fun onIdleState() {
        super.onIdleState()
        binding.pokemonSimpleProgress.hide()
    }

    override fun onPokemonClicked(pokemonId: Long) {
        viewModel.onPokemonClicked(pokemonId)
    }

    override fun onFavouritePokemonClicked(pokemon: PokemonDisplayable) {
        viewModel.onFavouritePokemonClicked(pokemon)
    }

    override fun onCatchPokemonClicked(pokemon: PokemonDisplayable) {
        viewModel.onCatchPokemonClicked(pokemon)
    }
}
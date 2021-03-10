package pl.kwiatekmichal.pokedex.features.pokemons.presentation.list

import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ViewModelOwner.Companion.from
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.scope.Scope
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.base.BaseFragment
import pl.kwiatekmichal.pokedex.core.extension.viewBinding
import pl.kwiatekmichal.pokedex.core.util.SpaceItemDecoration
import pl.kwiatekmichal.pokedex.databinding.FragmentPokemonListBinding
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter.OnPokemonSimpleListener
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter.PokemonsAdapter
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable

class PokemonListFragment : BaseFragment<PokemonListViewModel>(
    layoutId = R.layout.fragment_pokemon_list
), OnPokemonSimpleListener {
    override val scope: Scope by lazy { fragmentScope() }
    override val TAG: String = "PokemonListFragment"
    override val viewModel: PokemonListViewModel by scope.viewModel(owner = { from(this) })
    private val binding by viewBinding(FragmentPokemonListBinding::bind)
    private val pokemonsAdapter: PokemonsAdapter by scope.inject()

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
package pl.kwiatekmichal.pokedex.features.pokemons.presentation.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kwiatekmichal.pokedex.R
import pl.kwiatekmichal.pokedex.core.base.BaseViewModel
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapper
import pl.kwiatekmichal.pokedex.features.pokemons.domain.GetPokemonsUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.SaveCaughtPokemonUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.SaveFavouritePokemonUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator.PokemonNavigator
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val saveFavouritePokemonUseCase: SaveFavouritePokemonUseCase,
    private val saveCaughtPokemonUseCase: SaveCaughtPokemonUseCase,
    private val pokemonNavigator: PokemonNavigator,
    private val app: Application,
    errorMapper: ErrorMapper
) : BaseViewModel(
    app = app,
    errorMapper = errorMapper
) {
    private val _pokemons by lazy { MutableLiveData<List<Pokemon>>().also { getPokemons(it) } }
    val pokemons: LiveData<List<PokemonDisplayable>> =
        _pokemons.map { pokemon -> pokemon.map { PokemonDisplayable(it) } }

    private fun getPokemons(liveData: MutableLiveData<List<Pokemon>>) {
        setPendingState()
        getPokemonsUseCase(params = Unit, scope = viewModelScope) { result ->
            setIdleState()
            result.onSuccess { liveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }

    fun onPokemonClicked(pokemonId: Long) {
        pokemonNavigator.openPokemonDetailsScreen(pokemonId = pokemonId)
    }

    fun onFavouritePokemonClicked(pokemon: PokemonDisplayable) {
        setPendingState()
        saveFavouritePokemonUseCase(
            params = pokemon.id,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess {
                getPokemons(_pokemons)
                showMessage(
                    app.getString(
                        when {
                            pokemon.isFavourite -> R.string.labels_message_dislike_pokemon
                            else -> R.string.labels_message_favourite_pokemon
                        }, pokemon.name
                    )
                )
            }
            result.onFailure { handleFailure(it) }
        }
    }

    fun onCatchPokemonClicked(pokemon: PokemonDisplayable) {
        setPendingState()
        saveCaughtPokemonUseCase(
            params = pokemon.id,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess {
                getPokemons(_pokemons)
                showMessage(
                    app.getString(
                        when {
                            pokemon.isCaught -> R.string.labels_message_not_caught_pokemon
                            else -> R.string.labels_message_caught_pokemon
                        }, pokemon.name
                    )
                )
            }
            result.onFailure { handleFailure(it) }
        }
    }
}
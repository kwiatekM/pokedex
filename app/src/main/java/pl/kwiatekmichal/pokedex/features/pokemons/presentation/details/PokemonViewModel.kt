package pl.kwiatekmichal.pokedex.features.pokemons.presentation.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kwiatekmichal.pokedex.core.base.BaseViewModel
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapper
import pl.kwiatekmichal.pokedex.features.pokemons.domain.GetPokemonUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.SaveCaughtPokemonUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.SaveFavouritePokemonUseCase
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.model.PokemonDisplayable
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val saveFavouritePokemonUseCase: SaveFavouritePokemonUseCase,
    private val saveCaughtPokemonUseCase: SaveCaughtPokemonUseCase,
    private val app: Application,
    errorMapper: ErrorMapper
) : BaseViewModel(
    app = app,
    errorMapper = errorMapper
) {
    private val _pokemon by lazy { MutableLiveData<Pokemon>() }
    val pokemon: LiveData<PokemonDisplayable> = _pokemon.map { PokemonDisplayable(it) }

    fun onPokemonPassed(pokemonId: Long) {
        setPendingState()
        getPokemonUseCase(params = pokemonId, scope = viewModelScope) { pokemonResult ->
            setIdleState()
            pokemonResult.onSuccess { _pokemon.value = it }
            pokemonResult.onFailure { handleFailure(it) }
        }
    }
}
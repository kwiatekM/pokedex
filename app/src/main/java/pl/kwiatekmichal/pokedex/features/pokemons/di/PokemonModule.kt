package pl.kwiatekmichal.pokedex.features.pokemons.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.kwiatekmichal.pokedex.features.pokemons.data.PokemonRepositoryImpl
import pl.kwiatekmichal.pokedex.features.pokemons.domain.*
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.PokemonFragment
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.PokemonViewModel
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.details.adapter.PokemonAdapter
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.PokemonListFragment
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.PokemonListViewModel
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.list.adapter.PokemonsAdapter
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator.PokemonNavigator
import pl.kwiatekmichal.pokedex.features.pokemons.presentation.navigator.PokemonNavigatorImpl

val pokemonModule = module {
    factory<PokemonRepository> { PokemonRepositoryImpl(get(), get(), get(), get()) }
    factory { GetPokemonsUseCase(get()) }
    factory { SaveFavouritePokemonUseCase(get()) }
    factory { SaveCaughtPokemonUseCase(get()) }
    scope(named<PokemonListFragment>()) {
        viewModel { PokemonListViewModel(get(), get(), get(), get(), androidApplication(), get()) }
        factory { PokemonsAdapter() }
    }
    factory<PokemonNavigator> { PokemonNavigatorImpl(get()) }
    factory { GetPokemonUseCase(get()) }
    scope(named<PokemonFragment>()) {
        viewModel {
            PokemonViewModel(
                get(),
                get(),
                get(),
                androidApplication(),
                get()
            )
        }
        factory { PokemonAdapter() }
    }
}
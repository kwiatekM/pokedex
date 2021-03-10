package pl.kwiatekmichal.pokedex.core.di

import org.koin.core.module.Module
import pl.kwiatekmichal.pokedex.features.pokemons.di.pokemonModule

val featureModules: List<Module> = listOf(pokemonModule)
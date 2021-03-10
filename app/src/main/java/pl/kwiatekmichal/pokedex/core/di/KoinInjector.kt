package pl.kwiatekmichal.pokedex.core.di

import org.koin.core.module.Module

val koinInjector: List<Module> = appModule + networkModule + databseModule + featureModules
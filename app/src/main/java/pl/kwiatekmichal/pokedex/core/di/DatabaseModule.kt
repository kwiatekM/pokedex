package pl.kwiatekmichal.pokedex.core.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.kwiatekmichal.pokedex.core.database.PokeDatabase

val databseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PokeDatabase::class.java,
            "eventsDatabase"
        ).build()
    }
    single { get<PokeDatabase>().getPokemonDao() }
}
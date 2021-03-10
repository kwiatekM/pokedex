package pl.kwiatekmichal.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.kwiatekmichal.pokedex.features.pokemons.data.PokemonDao
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.PokemonCached
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.PokemonTypeCached
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.TypeCached

@Database(
    entities = [PokemonCached::class, TypeCached::class, PokemonTypeCached::class],
    version = 1
)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}
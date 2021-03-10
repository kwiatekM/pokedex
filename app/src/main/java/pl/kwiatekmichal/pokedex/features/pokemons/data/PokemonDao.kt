package pl.kwiatekmichal.pokedex.features.pokemons.data

import androidx.room.*
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.PokemonCached
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.PokemonWithTypes
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.TypeCached

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonCached")
    suspend fun getPokemons(): List<PokemonCached>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavouritePokemon(pokemon: PokemonCached)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCaughtPokemon(pokemon: PokemonCached)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: PokemonCached)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveType(vararg type: TypeCached)

    @Query("SELECT * FROM PokemonCached WHERE pokemonId = :id")
    suspend fun getPokemon(id: Long): PokemonCached?

    @Transaction
    @Query("SELECT * FROM PokemonCached WHERE pokemonId = :id")
    suspend fun getPokemonWithTypes(id: Long): PokemonWithTypes

    @Query("SELECT * FROM TypeCached")
    suspend fun getPokemonTypes(): List<TypeCached>
}
package pl.kwiatekmichal.pokedex.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.kwiatekmichal.pokedex.core.database.PokeDatabase
import pl.kwiatekmichal.pokedex.features.pokemons.data.PokemonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePokeDatabase(
        @ApplicationContext context: Context
    ): PokeDatabase {
        return Room.databaseBuilder(
            context,
            PokeDatabase::class.java,
            "eventsDatabase"
        ).build()
    }

    @Provides
    fun providePokemonDao(
        pokeDatabase: PokeDatabase
    ): PokemonDao {
        return pokeDatabase.getPokemonDao()
    }
}
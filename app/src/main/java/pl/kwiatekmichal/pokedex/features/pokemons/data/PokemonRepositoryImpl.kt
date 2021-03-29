package pl.kwiatekmichal.pokedex.features.pokemons.data

import pl.kwiatekmichal.pokedex.core.api.PokeApi
import pl.kwiatekmichal.pokedex.core.api.model.PokemonListResponse
import pl.kwiatekmichal.pokedex.core.api.model.PokemonResponse
import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapper
import pl.kwiatekmichal.pokedex.core.extension.callOrThrow
import pl.kwiatekmichal.pokedex.core.extension.toId
import pl.kwiatekmichal.pokedex.core.network.NetworkStateProvider
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.PokemonCached
import pl.kwiatekmichal.pokedex.features.pokemons.data.model.TypeCached
import pl.kwiatekmichal.pokedex.features.pokemons.domain.PokemonRepository
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.Pokemon
import pl.kwiatekmichal.pokedex.features.pokemons.domain.model.PokemonType
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokeApi,
    private val pokemonDao: PokemonDao,
    private val networkStateProvider: NetworkStateProvider,
    private val errorWrapper: ErrorWrapper
) : PokemonRepository {
    override suspend fun getPokemonSimpleList(): List<Pokemon> {
        return when {
            networkStateProvider.isNetworkAvailable() -> callOrThrow(errorWrapper) {
                val pokemonsFromRemote = getPokemonsFromRemote()
                val pokemonsFromLocal = getPokemonsFromLocal()
                pokemonsFromRemote.map { response ->
                    val id = response.url.toId()
                    val pokemonFromLocal = pokemonsFromLocal.firstOrNull { it.id == id }
                    response.toPokemon(pokemonFromLocal)
                }
            }.also { savePokemonsToLocal(it) }
            else -> getPokemonsFromLocal()
        }
    }

    private suspend fun getPokemonsFromRemote(): List<PokemonListResponse> {
        return pokemonApi.getPokemons().results
    }

    private suspend fun getPokemonsFromLocal(): List<Pokemon> {
        return pokemonDao.getPokemons()
            .map { it.toPokemon() }
    }

    private suspend fun savePokemonsToLocal(pokemons: List<Pokemon>) {
        pokemons.map {
            val pokemonFromLocal = pokemonDao.getPokemon(id = it.id)
            if (pokemonFromLocal == null) {
                pokemonDao.savePokemon(PokemonCached(it))
            }
        }
    }

    override suspend fun saveFavouritePokemon(id: Long) {
        val pokemon = pokemonDao.getPokemon(id = id)
        if (pokemon != null) {
            return pokemonDao.saveFavouritePokemon(pokemon.apply {
                isFavourite = !pokemon.isFavourite
            })
        }
    }

    override suspend fun saveCaughtPokemon(id: Long) {
        val pokemon = pokemonDao.getPokemon(id = id)
        if (pokemon != null) {
            return pokemonDao.saveCaughtPokemon(pokemon.apply {
                isCaught = !pokemon.isCaught
            })
        }
    }

    override suspend fun getPokemon(pokemonId: Long): Pokemon? {
        return when {
            networkStateProvider.isNetworkAvailable() -> callOrThrow(errorWrapper) {
                val pokemonFromRemote = getPokemonFromRemote(pokemonId = pokemonId)
                val pokemonFromLocal = getPokemonFromLocal(pokemonId = pokemonId)
                pokemonFromRemote.toPokemon(pokemonFromLocal)
            }.also {
                savePokemonTypesToLocal(it.types)
                savePokemonToLocal(it)
            }
            else -> getPokemonFromLocal(pokemonId = pokemonId)
        }
    }

    private suspend fun getPokemonFromRemote(pokemonId: Long): PokemonResponse {
        return pokemonApi.getPokemon(pokemonId = pokemonId)
    }

    private suspend fun savePokemonToLocal(pokemon: Pokemon) {
        pokemonDao.savePokemon(PokemonCached(pokemon))
    }

    private suspend fun savePokemonTypesToLocal(types: List<PokemonType>) {
        types.map { TypeCached(it) }
            .toTypedArray()
            .let { pokemonDao.saveType(*it) }
    }

    private suspend fun getPokemonFromLocal(pokemonId: Long): Pokemon {
        return pokemonDao
//            .getPokemonTypes().map { Pokemon("", 0L, true, false, 0.0, 0.0, listOf(it.toPokemonType())) }[0]
            .getPokemonWithTypes(id = pokemonId).toPokemon()
    }
}
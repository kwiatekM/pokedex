package pl.kwiatekmichal.pokedex.core.network

interface NetworkStateProvider {
    fun isNetworkAvailable(): Boolean
}
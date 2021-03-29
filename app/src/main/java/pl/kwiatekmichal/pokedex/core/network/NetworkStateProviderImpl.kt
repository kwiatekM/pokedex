package pl.kwiatekmichal.pokedex.core.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class NetworkStateProviderImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : NetworkStateProvider {
    override fun isNetworkAvailable(): Boolean {
        val capabilities = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            }
            else -> {
                return false
                //TODO("VERSION.SDK_INT < M")
                //activeNetwork
            }
        }

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    or capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    or capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
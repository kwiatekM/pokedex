package pl.kwiatekmichal.pokedex.core.network.interactor

import okhttp3.Interceptor

interface HeaderInterceptor: Interceptor {
    fun addHeader(header: Pair<String, String>)
}
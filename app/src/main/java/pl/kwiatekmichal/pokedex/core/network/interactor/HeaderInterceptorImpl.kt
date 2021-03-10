package pl.kwiatekmichal.pokedex.core.network.interactor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptorImpl : HeaderInterceptor {
    private val headers = mutableMapOf<String, String>()

    override fun addHeader(header: Pair<String, String>) {
        if (header.first.isNotEmpty()) {
            headers[header.first] = header.second
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return when {
            headers.isNotEmpty() -> {
                val builder = chain.request().newBuilder()
                headers.forEach {
                    builder.addHeader(it.key, it.value)
                }
                chain.proceed(builder.build())
            }
            else -> chain.proceed(chain.request())
        }
    }
}
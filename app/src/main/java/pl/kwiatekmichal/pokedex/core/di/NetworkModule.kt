package pl.kwiatekmichal.pokedex.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pl.kwiatekmichal.pokedex.BuildConfig
import pl.kwiatekmichal.pokedex.core.api.PokeApi
import pl.kwiatekmichal.pokedex.core.network.interactor.HeaderInterceptor
import pl.kwiatekmichal.pokedex.core.network.interactor.HeaderInterceptorImpl
import pl.kwiatekmichal.pokedex.core.network.interactor.JsonLogger
import pl.kwiatekmichal.pokedex.core.util.Logger
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val networkModule = module {
//    single<HeaderInterceptor> {
//        HeaderInterceptorImpl().also {
//            it.addHeader("APIKey" to "4B131E05-2D0B-458D-A0A9-29AC7211F469")
//        }
//    }

    single<Interceptor> {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Logger.logJson(message)
            }
        }).apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient.Builder()
//            .addInterceptor(get<HeaderInterceptor>())
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor(JsonLogger()).also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                        .build()
                )
            )
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(PokeApi::class.java) }
}
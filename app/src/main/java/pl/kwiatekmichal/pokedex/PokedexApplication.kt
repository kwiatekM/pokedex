package pl.kwiatekmichal.pokedex

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.kwiatekmichal.pokedex.core.di.koinInjector
import pl.kwiatekmichal.pokedex.core.util.Logger

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
    }

    private fun initKoin() {
        startKoin {
            printLogger()
            androidContext(this@PokedexApplication)
            modules(koinInjector)
        }
    }

    private fun initLogger() {
        Logger.init()
    }
}
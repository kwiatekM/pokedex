package pl.kwiatekmichal.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.kwiatekmichal.pokedex.core.util.Logger

@HiltAndroidApp
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}
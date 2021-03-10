package pl.kwiatekmichal.pokedex.core.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import pl.kwiatekmichal.pokedex.BuildConfig

object Logger {
    private val DEFAULT_FORMAT_STRATEGY = PrettyFormatStrategy.newBuilder()
        .methodCount(5)
        .methodOffset(7)
        .tag("EVENT_APP")
        .build()

    fun init() {
        Logger.addLogAdapter(object : AndroidLogAdapter(DEFAULT_FORMAT_STRATEGY) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun log(message: String) {
        Logger.d(message)
    }

    fun logException(throwable: Throwable, message: String = "Error") {
        Logger.e(throwable, message)
    }

    fun logJson(json: String?) {
        Logger.json(json)
    }
}
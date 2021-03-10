package pl.kwiatekmichal.pokedex.core.network.interactor

import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import pl.kwiatekmichal.pokedex.core.util.Logger

class JsonLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        when {
            isMessageJson(message) -> Logger.logJson(message)
            else -> HttpLoggingInterceptor.Logger.DEFAULT.log(message)
        }
    }

    fun isMessageJson(message: String): Boolean {
        return try {
            val json = message.trim { it <= ' ' }
            when {
                json.startsWith("{") -> {
                    JSONObject(json)
                    true
                }
                json.startsWith("[") -> {
                    JSONObject(json)
                    true
                }
                else -> false
            }
        } catch (e: JSONException) {
            false
        }
    }
}
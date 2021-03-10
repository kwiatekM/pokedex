package pl.kwiatekmichal.pokedex.core.exception

import android.content.Context
import pl.kwiatekmichal.pokedex.core.util.Logger

class ErrorMapperImpl(
    val context: Context
): ErrorMapper {
    override fun map(throwable: Throwable): String {
        Logger.logException(throwable)
        return throwable.message ?: throwable.localizedMessage ?: "Error"
    }
}
package pl.kwiatekmichal.pokedex.core.exception

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.kwiatekmichal.pokedex.core.util.Logger
import javax.inject.Inject

class ErrorMapperImpl @Inject constructor(
    @ApplicationContext val context: Context
) : ErrorMapper {
    override fun map(throwable: Throwable): String {
        Logger.logException(throwable)
        return throwable.message ?: throwable.localizedMessage ?: "Error"
    }
}
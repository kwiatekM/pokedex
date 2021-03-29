package pl.kwiatekmichal.pokedex.core.exception

import javax.inject.Inject

class ErrorWrapperImpl @Inject constructor() : ErrorWrapper {
    override fun wrap(throwable: Throwable): Throwable {
        return throwable
    }
}
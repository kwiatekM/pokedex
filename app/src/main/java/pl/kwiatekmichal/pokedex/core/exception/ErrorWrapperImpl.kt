package pl.kwiatekmichal.pokedex.core.exception

class ErrorWrapperImpl: ErrorWrapper {
    override fun wrap(throwable: Throwable): Throwable {
        return throwable
    }
}
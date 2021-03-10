package pl.kwiatekmichal.pokedex.core.exception

interface ErrorWrapper {
    fun wrap(throwable: Throwable): Throwable
}
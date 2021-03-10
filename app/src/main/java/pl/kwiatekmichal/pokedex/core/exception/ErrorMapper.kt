package pl.kwiatekmichal.pokedex.core.exception

interface ErrorMapper {
    fun map(throwable: Throwable): String
}
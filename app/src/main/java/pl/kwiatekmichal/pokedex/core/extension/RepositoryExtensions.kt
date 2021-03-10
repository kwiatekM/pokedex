package pl.kwiatekmichal.pokedex.core.extension

import pl.kwiatekmichal.pokedex.core.exception.ErrorWrapper

suspend fun <T> callOrThrow(
    errorWrapper: ErrorWrapper,
    apiCall: suspend () -> T
): T {
    return runCatching { apiCall() }
        .getOrElse { throw errorWrapper.wrap(it) }
}
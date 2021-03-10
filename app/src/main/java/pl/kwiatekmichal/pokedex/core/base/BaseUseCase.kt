package pl.kwiatekmichal.pokedex.core.base

import kotlinx.coroutines.*

abstract class BaseUseCase<out Type, in Params> {
    abstract suspend fun action(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        executrionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Type>) -> Unit = {}
    ) {
        scope.launch {
            val result = withContext(executrionDispatcher) {
                runCatching { action(params) }
            }
            onResult(result)
        }
    }
}
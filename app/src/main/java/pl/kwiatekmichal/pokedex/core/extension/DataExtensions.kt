package pl.kwiatekmichal.pokedex.core.extension

import java.util.*

fun String.toId(): Long {
    return this.substringBeforeLast("/").substringAfterLast("/").toLong()
}

fun String.firstLetterToUpperCase(): String {
    return when {
        this.isBlank() -> this
        else -> this.substring(0, 1)
            .toUpperCase(Locale.getDefault()) + this.substring(1)
            .toLowerCase(Locale.getDefault())
    }
}
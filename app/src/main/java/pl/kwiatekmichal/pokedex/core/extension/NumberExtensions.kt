package pl.kwiatekmichal.pokedex.core.extension

import java.text.DecimalFormat

fun Double.format(minimumFractionDigits: Int, maximumFractionalDigits: Int): String {
    return DecimalFormat().also {
        it.maximumFractionDigits = maximumFractionalDigits
        it.minimumFractionDigits = minimumFractionDigits
    }.format(this)
}
package pl.kwiatekmichal.pokedex.core.extension

import android.content.Context
import androidx.core.content.ContextCompat
import pl.kwiatekmichal.pokedex.R

fun Context.getColorOrDefault(color: String?): Int {
    return ContextCompat.getColor(
        this, when (color) {
            "black" -> R.color.pokemon_black
            "blue" -> R.color.pokemon_blue
            "brown" -> R.color.pokemon_brown
            "gray" -> R.color.pokemon_gray
            "green" -> R.color.pokemon_green
            "pink" -> R.color.pokemon_pink
            "purple" -> R.color.pokemon_purple
            "red" -> R.color.pokemon_red
            "white" -> R.color.pokemon_white
            "yellow" -> R.color.pokemon_yellow
            else -> R.color.color_surface
        }
    )
}
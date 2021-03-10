package pl.kwiatekmichal.pokedex.core.base

sealed class UiState {
    object Idle : UiState()
    object Pending : UiState()
}

package pl.kwiatekmichal.pokedex.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import pl.kwiatekmichal.pokedex.core.exception.ErrorMapper

open class BaseViewModel(
    app: Application,
    private val errorMapper: ErrorMapper
) : AndroidViewModel(app),
    DefaultLifecycleObserver {
    private val _uiState by lazy { MutableLiveData<UiState>(UiState.Idle) }
    val uiState: LiveData<UiState> = _uiState

    private val _message by lazy { LiveEvent<String>() }
    val message: LiveData<String> = _message

    protected fun setPendingState() {
        _uiState.value = UiState.Pending
    }

    protected fun setIdleState() {
        _uiState.value = UiState.Idle
    }

    protected fun showMessage(message: String) {
        _message.value = message
    }

    protected fun handleFailure(throwable: Throwable) {
        showMessage(errorMapper.map(throwable))
    }
}
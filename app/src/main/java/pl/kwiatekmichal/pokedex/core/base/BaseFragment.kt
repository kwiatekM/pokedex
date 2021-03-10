package pl.kwiatekmichal.pokedex.core.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import org.koin.androidx.scope.ScopeFragment
import pl.kwiatekmichal.pokedex.core.util.Logger

abstract class BaseFragment<T : BaseViewModel>(
    @LayoutRes layoutId: Int
) : ScopeFragment(
    layoutId
) {
    abstract val TAG: String
    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.log(TAG)
        initViews()
        initObservers()
        bindViewModelToLifecycle()
    }

    open fun observeViewState() {}

    open fun initViews() {}

    open fun onPendingState() {}

    open fun onIdleState() {}

    protected fun showToast(@StringRes stringRes: Int) {
        showToast(requireContext().getString(stringRes))
    }

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun initObservers() {
        observeMessage()
        observeUiState()
        observeViewState()
    }

    private fun observeMessage() {
        viewModel.message.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                UiState.Idle -> onIdleState()
                UiState.Pending -> onPendingState()
            }
        }
    }

    private fun bindViewModelToLifecycle() {
        lifecycle.addObserver(viewModel)
    }
}
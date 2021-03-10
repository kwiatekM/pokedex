package pl.kwiatekmichal.pokedex.core.navigation

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import pl.kwiatekmichal.pokedex.core.provider.ActivityProvider

class FragmentNavigatorImpl(
    private val activityProvider: ActivityProvider,
    @IdRes private val navHostFragmentRes: Int,
    @IdRes private val homeDestinationRes: Int,
    private val defaultNavOptions: NavOptions
) : FragmentNavigator {

    override fun navigateTo(destinationId: Int, fragmentTransition: FragmentTransition?) {
        navigateTo<Unit>(destinationId, null, fragmentTransition)
    }

    override fun <T> navigateTo(destinationId: Int, param: Pair<String, T>?, fragmentTransition: FragmentTransition?) {
        val bundle = param?.let { bundleOf(it) }
        val navOptions = fragmentTransition?.let {
            navOptions {
                anim { enter = it.enterAnim }
                anim { exit = it.exitAnim }
                anim { popEnter = it.popEnterAnim }
                anim { popExit = it.popExitAnim }
            }
        } ?: defaultNavOptions

        getNavController()?.navigate(destinationId, bundle, navOptions)
    }

    private fun getNavController() = getSupportFragmentManager()
        ?.findFragmentById(navHostFragmentRes)
        ?.findNavController()

    private fun getSupportFragmentManager() =
        (activityProvider.foregroundActivity as? FragmentActivity)?.supportFragmentManager

    override fun goBack(destinationId: Int?, inclusive: Boolean) {
        when (destinationId) {
            null -> getNavController()?.popBackStack()
            else -> getNavController()?.popBackStack(destinationId, inclusive)
        }
    }

    override fun clearHistory() {
        goBack(homeDestinationRes)
    }
}
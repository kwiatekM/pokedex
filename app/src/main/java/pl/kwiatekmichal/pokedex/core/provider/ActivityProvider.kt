package pl.kwiatekmichal.pokedex.core.provider

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.inject.Inject

class ActivityProvider @Inject constructor(
    app: Application
) : Application.ActivityLifecycleCallbacks {
    var foregroundActivity: Activity? = null

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        foregroundActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        foregroundActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        foregroundActivity = null
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}
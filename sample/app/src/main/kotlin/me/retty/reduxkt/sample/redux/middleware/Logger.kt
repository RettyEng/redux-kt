package me.retty.reduxkt.sample.redux.middleware

import android.util.Log
import me.retty.reduxkt.Middleware
import me.retty.reduxkt.sample.redux.state.ApplicationState

/**
 * Created by atsukofukui on 2017/08/23.
 */
val logger: Middleware<ApplicationState> = {
    { dispatch ->
        { action ->
            Log.d("Logger", "dispatching action: ${action.javaClass.canonicalName}")
            dispatch(action)
        }
    }
}
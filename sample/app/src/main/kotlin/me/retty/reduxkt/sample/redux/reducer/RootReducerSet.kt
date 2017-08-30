package me.retty.reduxkt.sample.redux.reducer

import me.retty.reduxkt.sample.redux.Reducer
import me.retty.reduxkt.sample.redux.action.TodoAction

/**
 * Created by atsukofukui on 2017/08/23.
 */
class RootReducerSet {
    companion object {
        val aggregatedReducer: Reducer = { action, state ->
            when (action) {
                is TodoAction -> TodoReducerSet.aggregatedReducer(action, state)
                else -> state
            }
        }
    }
}
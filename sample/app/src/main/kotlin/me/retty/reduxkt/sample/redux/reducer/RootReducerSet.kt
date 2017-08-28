package me.retty.reduxkt.sample.redux.reducer

import me.retty.reduxkt.sample.redux.Reducer
import me.retty.reduxkt.sample.redux.action.creatorproducer.HogeAction

/**
 * Created by atsukofukui on 2017/08/23.
 */
class RootReducerSet {
    companion object {
        val aggregatedReducer: Reducer = { action, state ->
            when (action) {
                is HogeAction -> HogeReducerSet.aggregatedReducer(action, state)
                else -> state
            }
        }
    }
}
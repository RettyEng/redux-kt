package me.retty.reduxkt.sample.redux.reducer

import me.retty.reduxkt.sample.redux.action.creatorproducer.HogeAction
import me.retty.reduxkt.sample.redux.state.ApplicationState

/**
 * Created by atsukofukui on 2017/08/23.
 */
class HogeReducerSet {
    companion object {
        fun aggregatedReducer(action: HogeAction, state: ApplicationState) = when (action) {
            is HogeAction.OnHogeAction -> onHogeAction(action, state)
            else -> state
        }

        fun onHogeAction(action: HogeAction.OnHogeAction, state: ApplicationState) =
                state.copy(hoge = action.hoge)

    }
}
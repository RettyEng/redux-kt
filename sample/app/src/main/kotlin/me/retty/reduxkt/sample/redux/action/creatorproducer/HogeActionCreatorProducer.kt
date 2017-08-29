package me.retty.reduxkt.sample.redux.action.creatorproducer

import me.retty.reduxkt.sample.redux.AsyncActionCreator
import me.retty.reduxkt.sample.redux.action.HogeAction

/**
 * Created by atsukofukui on 2017/08/23.
 */
class HogeActionCreatorProducer {
    companion object {
        fun produceHogeAction(hoge: Boolean): AsyncActionCreator = { _, _, callback ->
            callback { _, _ ->
                HogeAction.OnHogeAction(hoge)

            }
        }
    }
}
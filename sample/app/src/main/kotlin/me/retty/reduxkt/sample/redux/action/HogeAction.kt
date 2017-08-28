package me.retty.reduxkt.sample.redux.action.creatorproducer

import me.retty.reduxkt.Action

/**
 * Created by atsukofukui on 2017/08/23.
 */
interface HogeAction : Action {
    class OnHogeAction(val hoge: Boolean) : HogeAction
}
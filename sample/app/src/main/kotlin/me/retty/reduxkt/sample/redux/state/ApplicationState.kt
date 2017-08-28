package me.retty.reduxkt.sample.redux.state

import me.retty.reduxkt.StateType

/**
 * Created by atsukofukui on 2017/08/23.
 */
data class ApplicationState constructor(val hoge: Boolean = false) : StateType

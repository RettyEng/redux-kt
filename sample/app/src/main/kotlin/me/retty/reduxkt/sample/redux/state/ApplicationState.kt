package me.retty.reduxkt.sample.redux.state

import me.retty.reduxkt.StateType
import me.retty.reduxkt.sample.data.Todo

/**
 * Created by atsukofukui on 2017/08/23.
 */
data class ApplicationState constructor(val todos: List<Todo> = emptyList()) : StateType

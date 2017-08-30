package me.retty.reduxkt.sample.redux.action

import me.retty.reduxkt.Action

/**
 * Created by atsukofukui on 2017/08/23.
 */
interface TodoAction : Action {
    class OnCreateTodoAction(val name: String, val memo: String) : TodoAction
    class OnToggleCompletedTodoAction(val id: Long) : TodoAction
}
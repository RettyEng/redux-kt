package me.retty.reduxkt.sample.redux.action.creatorproducer

import me.retty.reduxkt.sample.redux.AsyncActionCreator
import me.retty.reduxkt.sample.redux.action.TodoAction

/**
 * Created by atsukofukui on 2017/08/23.
 */
class TodoActionCreatorProducer {
    companion object {
        fun produceCreateTodoAction(name: String, memo: String): AsyncActionCreator = { _, _, callback ->
            callback { _, _ ->
                TodoAction.OnCreateTodoAction(name, memo)

            }
        }

        fun produceToggleCompletedTodoAction(id: Long): AsyncActionCreator = { _, _, callback ->
            callback { _, _ ->
                TodoAction.OnToggleCompletedTodoAction(id)
            }
        }


    }
}
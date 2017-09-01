package me.retty.reduxkt.sample.redux.reducer

import me.retty.reduxkt.sample.data.Todo
import me.retty.reduxkt.sample.redux.action.TodoAction
import me.retty.reduxkt.sample.redux.state.ApplicationState

/**
 * Created by atsukofukui on 2017/08/23.
 */
class TodoReducerSet {
    companion object {
        fun aggregatedReducer(action: TodoAction, state: ApplicationState) = when (action) {
            is TodoAction.OnCreateTodoAction -> onCreateTodoAction(action, state)
            is TodoAction.OnToggleCompletedTodoAction -> onToggleCompletedTodoAction(action, state)
            else -> state
        }

        private fun onCreateTodoAction(action: TodoAction.OnCreateTodoAction, state: ApplicationState) =
                state.copy(todos = state.todos + Todo(state.todos.size.toLong(),
                                                      action.name, action.memo, false))

        private fun onToggleCompletedTodoAction(action: TodoAction.OnToggleCompletedTodoAction,
                                        state: ApplicationState) =
                state.copy(todos= state.todos.map {
                    if (it.id == action.id) {
                        it.copy(isDone = !it.isDone)
                    } else {
                        it
                    }
                })
    }
}
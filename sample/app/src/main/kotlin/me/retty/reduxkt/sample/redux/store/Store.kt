package me.retty.reduxkt.sample.redux.store

import me.retty.reduxkt.Action
import me.retty.reduxkt.Store
import me.retty.reduxkt.sample.redux.ActionCreator
import me.retty.reduxkt.sample.redux.AsyncActionCreator
import me.retty.reduxkt.sample.redux.Subscriber
import me.retty.reduxkt.sample.redux.middleware.logger
import me.retty.reduxkt.sample.redux.reducer.RootReducerSet
import me.retty.reduxkt.sample.redux.state.ApplicationState

/**
 * Created by atsukofukui on 2017/08/23.
 */
object Store {
    private val store: Store<ApplicationState> by lazy {
        Store(getInitialState(),
              RootReducerSet.aggregatedReducer,
              listOf(logger))
    }

    private fun getInitialState(): ApplicationState {
        return ApplicationState()
    }

    fun getState(): ApplicationState = this.store.state

    fun subscribe(subscriber: Subscriber) = this.store.subscribe(subscriber)
    fun unsubscribe(subscriber: Subscriber) =
            this.store.unsubscribe(subscriber)

    fun dispatch(action: Action) = this.store.dispatch(action)
    fun dispatch(actionCreator: ActionCreator) =
            this.store.dispatch(actionCreator)

    fun dispatch(actionCreator: AsyncActionCreator) =
            this.store.dispatch(actionCreator)

    fun dispatch(actionCreator: AsyncActionCreator,
                 callback: (ApplicationState) -> Unit) =
            this.store.dispatch(actionCreator, callback)
}
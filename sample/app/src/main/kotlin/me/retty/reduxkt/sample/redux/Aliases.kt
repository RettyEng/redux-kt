package me.retty.reduxkt.sample.redux

/**
 * Created by atsukofukui on 2017/08/23.
 */
import me.retty.reduxkt.ActionCreator
import me.retty.reduxkt.AsyncActionCreator
import me.retty.reduxkt.Middleware
import me.retty.reduxkt.Reducer
import me.retty.reduxkt.StateSubscriber
import me.retty.reduxkt.sample.redux.state.ApplicationState

typealias Subscriber = StateSubscriber<ApplicationState>
typealias AsyncActionCreator = AsyncActionCreator<ApplicationState>
typealias ActionCreator = ActionCreator<ApplicationState>
typealias Reducer = Reducer<ApplicationState>
typealias Middleware = Middleware<ApplicationState>
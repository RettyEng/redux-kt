package io.github.yusaka39.reduxkt

/**
 * Created by yusaka on 3/15/17.
 */

typealias ActionCreator<State> = (State, Store<State>) -> Action
typealias AsyncActionCreator<State> = (State, Store<State>, (ActionCreator<State>) -> Unit) -> Unit
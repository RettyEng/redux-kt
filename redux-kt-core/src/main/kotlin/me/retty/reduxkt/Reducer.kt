package me.retty.reduxkt

import me.retty.reduxkt.internal.utils.comp
import me.retty.reduxkt.internal.utils.curry

/**
 * Created by yusaka on 3/14/17.
 */

typealias Reducer<StateType> = (Action, StateType) -> StateType

infix fun <T> Reducer<T>.compose(reducer: Reducer<T>): Reducer<T> = { action, state ->
    ((this curry action) comp (reducer curry (action)))(state)
}


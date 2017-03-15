package io.github.yusaka39.reduxkt

import io.github.yusaka39.reduxkt.internal.utils.comp
import io.github.yusaka39.reduxkt.internal.utils.curry

/**
 * Created by yusaka on 3/14/17.
 */

typealias Reducer<StateType> = (Action, StateType) -> StateType

infix fun <T> Reducer<T>.compose(reducer: Reducer<T>): Reducer<T> = { action, state ->
    ((this curry action) comp (reducer curry (action)))(state)
}


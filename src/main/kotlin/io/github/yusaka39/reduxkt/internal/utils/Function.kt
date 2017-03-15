package io.github.yusaka39.reduxkt.internal.utils

/**
 * Created by yusaka on 3/15/17.
 */

internal infix fun <T, U, V> ((U) -> V).comp(g: (T) -> U): (T) -> V = { arg ->
    this(g(arg))
}

internal infix fun <A1, A2, R> ((A1, A2) -> R).curry(a1: A1): (A2) -> R =
        { a2 ->
            this(a1, a2)
        }
internal infix fun <A1, A2, A3, R> ((A1, A2, A3) -> R).curry(a1: A1): (A2, A3) -> R =
        { a2, a3 ->
            this(a1, a2, a3)
        }
internal infix fun <A1, A2, A3, A4, R> ((A1, A2, A3, A4) -> R).curry(a1: A1): (A2, A3, A4) -> R =
        { a2, a3, a4 ->
            this(a1, a2, a3, a4)
        }
internal infix fun <A1, A2, A3, A4, A5, R>
        ((A1, A2, A3, A4, A5) -> R).curry(a1: A1): (A2, A3, A4, A5) -> R =
        { a2, a3, a4, a5 ->
            this(a1, a2, a3, a4, a5)
        }
internal infix fun <A1, A2, A3, A4, A5, A6, R>
        ((A1, A2, A3, A4, A5, A6) -> R).curry(a1: A1): (A2, A3, A4, A5, A6) -> R =
        { a2, a3, a4, a5, a6 ->
            this(a1, a2, a3, a4, a5, a6)
        }

package me.retty.reduxkt

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.properties.Delegates

/**
 * Created by yusaka on 3/15/17.
 */

typealias  StateSubscriber<T> = (old: T, new: T) -> Unit

class Store<T : StateType>(initialState: T, private val delegate: StoreDelegate<T>) {

    constructor(initialState: T, reducer: Reducer<T>, middlewares: List<Middleware<T>>) :
            this(initialState, object : StoreDelegate<T> {
                override fun getReducer(): Reducer<T> = reducer
                override fun getMiddlewares(): List<Middleware<T>> = middlewares
            })

    //Plain state interface
    var state: T by Delegates.observable(initialState) { _, old, new ->
        subscribers.forEach { it(old, new) }
    }
    private set

    private var subscribers: List<StateSubscriber<T>> = emptyList()

    //RxJava state interface
    private val stateSubject: BehaviorSubject<T> = BehaviorSubject.createDefault(initialState)
    val observable: Observable<T> = stateSubject.hide()

    private val lock = ReentrantLock()

    private val withLock: Middleware<T> = { _ ->
        { dispatcher ->
            { action ->
                if (lock.tryLock()) {
                    try {
                        dispatcher(action)
                    } finally {
                        lock.unlock()
                    }
                } else {
                    throw IllegalAccessError("Store is not allowed multithreaded dispatching")
                }
            }
        }
    }

    fun subscribe(subscriber: StateSubscriber<T>) {
        if (this.subscribers.contains(subscriber)) {
            throw IllegalStateException("Trying to register duplicated subscriber")
        }
        this.subscribers += subscriber
        subscriber(this.state, this.state)
    }

    fun unsubscribe(subscriber: StateSubscriber<T>) {
        this.subscribers -= subscriber
    }

    private val _dispatch: (Action) -> Unit = (this.delegate.getMiddlewares() + withLock).fold(
            { action: Action ->
                val newState = this.delegate.getReducer()(action, this.state)
                this.state = newState
                this.stateSubject.onNext(newState)
            }
    ) { acc, middleWare ->
        middleWare(this.state)(acc)
    }

    fun dispatch(action: Action) {
        this._dispatch(action)
    }

    fun dispatch(creator: ActionCreator<T>) {
        this.dispatch(creator(this.state, this))
    }

    fun dispatch(asyncCreator: AsyncActionCreator<T>) {
        this.dispatch(asyncCreator, null)
    }

    fun dispatch(asyncCreator: AsyncActionCreator<T>, callback: ((T) -> Unit)?) {
        asyncCreator(this.state, this) { creator ->
            this.dispatch(creator(this.state, this))
            callback?.invoke(this.state)
        }
    }
}

interface StoreDelegate<T : StateType> {
    fun getReducer(): Reducer<T>
    fun getMiddlewares(): List<Middleware<T>>
}
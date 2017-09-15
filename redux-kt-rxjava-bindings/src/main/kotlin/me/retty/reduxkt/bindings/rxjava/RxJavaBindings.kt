package me.retty.reduxkt.bindings.rxjava

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import me.retty.reduxkt.Action
import me.retty.reduxkt.Middleware
import me.retty.reduxkt.Reducer
import me.retty.reduxkt.StateType
import me.retty.reduxkt.Store
import me.retty.reduxkt.StoreDelegate
import java.util.WeakHashMap

object RxJavaBindings {
    fun <T: StateType> newStore(initialState: T, reducer: Reducer<T>,
                                middlewares: List<Middleware<T>>): Store<T> =
            this.newStore(initialState, object : StoreDelegate<T> {
                override fun getReducer(): Reducer<T> = reducer
                override fun getMiddlewares(): List<Middleware<T>> = middlewares
            })

    fun <T: StateType> newStore(initialState: T, delegate: StoreDelegate<T>): Store<T> {
        val fields = Fields(BehaviorSubject.createDefault (initialState))
        var store: Store<T>? = null

        store = Store(initialState, object : StoreDelegate<T> {
            override fun getReducer(): Reducer<T> = delegate.getReducer()

            override fun getMiddlewares(): List<Middleware<T>> {
                val rxjavaPublisher: Middleware<T> = { _ ->
                    { dispatcher: (Action) -> Unit ->
                        { action: Action ->
                            dispatcher(action)
                            fields.subject.onNext(store!!.state)
                        }
                    }
                }
                return listOf(rxjavaPublisher) + delegate.getMiddlewares()
            }
        })

        fieldsStore[store] = fields
        return store
    }
}

private class Fields<T: StateType>(val subject: BehaviorSubject<T>) {
    val observable: Observable<T> by  lazy {
        this.subject.hide()
    }
}

private val fieldsStore: MutableMap<Store<*>, Fields<*>> = WeakHashMap()

val <T: StateType> Store<T>.observable: Observable<T>
    @Suppress("unchecked_cast")
    get() = (fieldsStore[this] as? Fields<T>)?.observable ?: throw IllegalStateException(
            "Calling accessor of observable for store that is not prepare for RxJava binding." +
            " Use RxJavaBindings#newStore to create instance of Store")

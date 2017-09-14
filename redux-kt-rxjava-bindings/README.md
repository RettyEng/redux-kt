// TODO: Write it without slacking

Add it as a dependency.

```
compile 'com.github.rettyeng.redux-kt:redux-kt-core:0.0.2-SNAPSHOT'
```

Write codes like below

```
val store = RxJavaBindings.newStore<FooState>(
        initialState,
        { action, state -> /* reducer */ },
        /* middlewares */ listOf())

/* Store<*>.observable is an Observable of RxJava */
store.observable.subscribe {
    /* procedures */
}
```

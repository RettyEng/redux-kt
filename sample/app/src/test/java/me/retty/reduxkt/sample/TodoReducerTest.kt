package me.retty.reduxkt.sample

import me.retty.reduxkt.Store
import me.retty.reduxkt.sample.data.Todo
import me.retty.reduxkt.sample.redux.action.TodoAction
import me.retty.reduxkt.sample.redux.reducer.TodoReducerSet
import me.retty.reduxkt.sample.redux.state.ApplicationState
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by atsukofukui on 2017/08/30.
 */
class TodoReducerTest {
    var store: Store<ApplicationState>? = null

    @Before
    fun initiateStore() {
        this.store = Store(ApplicationState(listOf(Todo(0, "dummy name 0", "dummy memo 0", false),
                                                   Todo(1, "dummy name 1", "dummy memo 1", true))),
                           { action, state ->
                               TodoReducerSet.aggregatedReducer(action as TodoAction, state)
                           },
                           listOf())
    }

    @Test
    fun testOnCreateTodoActionCorrectlyHandled() {
        var isCalled = false
        this.store?.subscribe { old, new ->
            if (old === new) {
                // Ignore invoking subscriber on starting subscribe
                return@subscribe
            }
            isCalled = true
            Assert.assertEquals(2, new.todos[2].id)
            Assert.assertEquals("dummy name 2", new.todos[2].name)
            Assert.assertEquals("dummy memo 2", new.todos[2].memo)
            Assert.assertEquals(false, new.todos[2].isDone)
            Assert.assertEquals(Todo(0, "dummy name 0", "dummy memo 0", false), new.todos[0])
            Assert.assertEquals(Todo(1, "dummy name 1", "dummy memo 1", true), new.todos[1])
        }
        this.store?.dispatch(TodoAction.OnCreateTodoAction("dummy name 2", "dummy memo 2"))

        Assert.assertTrue(isCalled)
    }

    @Test
    fun testOnToggleCompletedTodoActionCorrectlyHandled() {
        var isCalled = false
        this.store?.subscribe { old, new ->
            if (old === new) {
                // Ignore invoking subscriber on starting subscribe
                return@subscribe
            }
            isCalled = true
            Assert.assertEquals(1, new.todos[1].id)
            Assert.assertEquals("dummy name 1", new.todos[1].name)
            Assert.assertEquals("dummy memo 1", new.todos[1].memo)
            Assert.assertEquals(false, new.todos[1].isDone)
            Assert.assertEquals(Todo(0, "dummy name 0", "dummy memo 0", false), new.todos[0])
        }
        this.store?.dispatch(TodoAction.OnToggleCompletedTodoAction(1))

        Assert.assertTrue(isCalled)
    }


}
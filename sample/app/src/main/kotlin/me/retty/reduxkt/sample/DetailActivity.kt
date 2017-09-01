package me.retty.reduxkt.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import me.retty.reduxkt.sample.extend.bindView
import me.retty.reduxkt.sample.redux.Subscriber
import me.retty.reduxkt.sample.redux.action.creatorproducer.TodoActionCreatorProducer
import me.retty.reduxkt.sample.redux.store.Store

/**
 * Created by atsukofukui on 2017/08/30.
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_ID_KEY = "item_id_key"
        fun start(context: Context, itemId: Long) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                this.putExtra(ITEM_ID_KEY, itemId)
            })
        }
    }

    private val itemId by lazy {
        this.intent.getLongExtra(ITEM_ID_KEY, -1)
    }
    private val memoTextView by bindView<TextView>(R.id.DetailActivity_memo_text)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)
    private val doneButton by bindView<ImageView>(R.id.DetailActivity_done_image)

    private val subscriber: Subscriber = { _, _ ->
        this.setUpView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_detail)

        this.setUpView()
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Store.subscribe(this.subscriber)
    }

    private fun setUpView() {
        val todo = Store.getState().todos[this.itemId.toInt()]
        this.toolbar.title = todo.name
        this.memoTextView.text = todo.memo
        this.doneButton.setOnClickListener {
            Store.dispatch(TodoActionCreatorProducer.produceToggleCompletedTodoAction(todo.id))
        }

        val drawable = DrawableCompat.wrap(
                ContextCompat.getDrawable(this, R.drawable.ic_check_circle_black_24dp))
        DrawableCompat.setTintList(drawable,
                                   ContextCompat.getColorStateList(this,
                                                                   if (todo.isDone) {
                                                                       R.color.already_done
                                                                   } else {
                                                                       R.color.not_completed
                                                                   })
        )
        this.doneButton.setImageDrawable(drawable)
    }

    override fun onDestroy() {
        super.onDestroy()
        Store.unsubscribe(this.subscriber)
    }
}
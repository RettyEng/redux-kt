package me.retty.reduxkt.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import me.retty.reduxkt.sample.extend.bindView
import me.retty.reduxkt.sample.redux.action.creatorproducer.TodoActionCreatorProducer
import me.retty.reduxkt.sample.redux.store.Store

/**
 * Created by atsukofukui on 2017/08/27.
 */
class EditActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, EditActivity::class.java))
        }
    }

    private val nameEditText by bindView<EditText>(R.id.EditActivity_edit_text_name)
    private val memoEditText by bindView<EditText>(R.id.EditActivity_edit_text_memo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_edit)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_done) {
            Store.dispatch(TodoActionCreatorProducer.produceCreateTodoAction(
                    this.nameEditText.text.toString(), this.memoEditText.text.toString()))
            this.finish()
            return true
        }
        if (id == R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
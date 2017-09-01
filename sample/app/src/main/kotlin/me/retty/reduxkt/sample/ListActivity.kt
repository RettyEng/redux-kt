package me.retty.reduxkt.sample

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import me.retty.reduxkt.sample.data.Todo
import me.retty.reduxkt.sample.extend.bindView
import me.retty.reduxkt.sample.redux.Subscriber
import me.retty.reduxkt.sample.redux.store.Store

class ListActivity : AppCompatActivity() {

    private val listView by bindView<ListView>(R.id.ListActivity_list_view)
    private val adapter by lazy { ListItemAdapter(this, emptyList()) }
    private val subscriber: Subscriber = { _, new ->
        this.adapter.data = new.todos
        this.adapter.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        this.setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { _ ->
            EditActivity.start(this)
        }

        this.listView.adapter = this.adapter
        Store.subscribe(this.subscriber)
    }


    override fun onDestroy() {
        super.onDestroy()
        Store.unsubscribe(this.subscriber)
    }

}

class ListItemAdapter(private val context: Context, var data: List<Todo>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val element = this.data[position]
        val view: ListItemView = convertView as ListItemView? ?: ListItemView(this.context)
        view.id = element.id
        view.textView = element.name
        view.checkbox = element.isDone
        view.setOnClickListener {
            DetailActivity.start(context, position.toLong())
        }
        return view
    }
    override fun getCount(): Int = this.data.size
    override fun getItem(position: Int): Any = this.data[position]
    override fun getItemId(position: Int): Long = this.data[position].id

}

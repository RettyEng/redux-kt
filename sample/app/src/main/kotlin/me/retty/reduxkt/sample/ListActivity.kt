package me.retty.reduxkt.sample

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import me.retty.reduxkt.sample.data.Todo
import me.retty.reduxkt.sample.extend.bindView

class ListActivity : AppCompatActivity() {

    val listView by bindView<ListView>(R.id.ListActivity_list_view)
    val adapter by lazy { ListItemAdapter(this, emptyList()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        this.setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { _ ->
            EditActivity.start(this)
        }

        //TODO(atsuko): Remove this
        this.adapter.data = listOf(Todo(0, "hogehoge", "fugafuga", true),
                                   Todo(1, "foo", "bar", false))
        this.listView.adapter = this.adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}

class ListItemAdapter(val context: Context, var data: List<Todo>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val element = this.data[position]
        val view: ListItemView = convertView as ListItemView? ?: ListItemView(this.context)
        view.textView = element.title
        view.checkbox = element.isDone
        return view
    }
    override fun getCount(): Int = this.data.size
    override fun getItem(position: Int): Any = this.data[position]
    override fun getItemId(position: Int): Long = this.data[position].id

}

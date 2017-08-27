package me.retty.reduxkt.sample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import me.retty.reduxkt.sample.extend.findAndCast
import kotlin.properties.Delegates

/**
 * Created by atsukofukui on 2017/08/25.
 */
class ListItemView : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
                                                                                    defStyleAttr)

    init {
        LayoutInflater.from(this.context).inflate(R.layout.view_list_item, this)
    }

    var textView by Delegates.observable("") { _, _, new ->
        this.findAndCast<TextView>(me.retty.reduxkt.sample.R.id.ListItemView_text_view).text = new
    }


    var checkbox by Delegates.observable(false) { _, _, new ->
        this.findAndCast<CheckBox>(
                me.retty.reduxkt.sample.R.id.ListItemView_checkbox).isChecked = new
    }
}
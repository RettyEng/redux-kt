package me.retty.reduxkt.sample.extend

import android.app.Activity
import android.view.View

/**
 * Created by atsukofukui on 2017/08/25.
 */
inline fun <reified T : View> Activity.bindView(resId: Int,
                                                noinline onClick: ((View) -> Unit)? = null): Lazy<T> =
        lazy {
            this.findViewById(resId).apply {
                onClick?.let {
                    this.setOnClickListener(it)
                }
            } as T
        }

@Suppress("UNCHECKED_CAST")
fun <T> View.findAndCast(id: Int): T = this.findViewById(id) as T

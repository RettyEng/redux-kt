package me.retty.reduxkt.sample.data

/**
 * Created by atsukofukui on 2017/08/25.
 */
data class Todo(
        val id: Long,
        val name: String,
        val memo: String,
        val isDone: Boolean
)
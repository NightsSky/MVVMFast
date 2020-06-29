package com.aleyn.mvvm.extension

import android.view.View


/**
 * Created by XKL on 2018/8/28.
 */

fun View.gone() {
    this.visibility = View.GONE
}
fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}



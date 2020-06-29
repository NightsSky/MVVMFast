package com.aleyn.mvvm.extension

import android.util.Log
import android.widget.Toast
import mvvm.whoami.mvvmfast.app.MyApp
import mvvm.whoami.mvvmfast.model.LOG_TAG


/**
 * toast 防止重复弹出
 */
private var toast:Toast?=null
fun showToast(message:String,duration:Int = 0) {
    if (toast == null) {
        toast = Toast.makeText(MyApp.getInstance(), message, duration)
    } else {
        toast?.setText(message)
        toast?.duration = duration
    }
    toast?.show()
}

/**
 * log打印
 */

fun Loge(msg:String) { Log.e(LOG_TAG, "==>  $msg  <==") }
fun Logd(msg:String) { Log.d(LOG_TAG, "==>  $msg  <==") }
fun Loge(tag:String = LOG_TAG,msg:String) { Log.e(tag, "==>  $msg  <==") }
fun Logd(tag:String = LOG_TAG,msg:String) { Log.d(tag, "==>  $msg  <==") }
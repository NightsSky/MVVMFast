package com.aleyn.mvvm.extension

import android.view.View
import android.widget.Toast
import com.aleyn.mvvm.app.BaseApp
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.TimeoutCancellationException
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


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

/**
 * toast 防止重复弹出
 */
private var toast: Toast? = null
fun showToast(message: String, duration: Int = 0) {
    if (toast == null) {
        toast = Toast.makeText(BaseApp.getInstance(), message, duration)
    } else {
        toast?.setText(message)
        toast?.duration = duration
    }
    toast?.show()
}
fun Throwable.show() {
    if (this is UnknownHostException) {
        showToast("当前无网络，请检查你的网络设置")
    } else if (this is SocketTimeoutException || this is TimeoutException || this is TimeoutCancellationException) {
        showToast("连接超时,请稍后再试")
    } else if (this is ConnectException) {
        showToast("网络不给力，请稍候重试！")
    } else if (this is HttpStatusCodeException) {
        showToast("服务器异常")
    } else if (this is JsonSyntaxException) {
        showToast("数据解析失败，请检查数据是否正确")
    } else if (this is ParseException) {
        if (errorCode != "200") {
            showToast(this.message ?: errorCode)
        }
    } else {
        showToast("服务器异常，请稍后再试")
    }
}

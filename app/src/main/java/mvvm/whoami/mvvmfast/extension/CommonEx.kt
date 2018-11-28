package mvvm.whoami.mvvmfast.extension

import android.util.Log
import android.widget.Toast
import mvvm.whoami.mvvmfast.app.MyApplication


/**
 * toast 防止重复点击
 */
private var toast:Toast?=null
fun showToast(message:String,duration:Int = 0) {
    if (toast == null) {
        toast = Toast.makeText(MyApplication.getInstance(), message, duration)
    } else {
        toast?.setText(message)
        toast?.setDuration(duration);
    }
    toast?.show()
}

/**
 * log打印
 */
const val LOG_TAG = "MSG"
fun Loge(tag:String = LOG_TAG,msg:String) { Log.e(tag, "==>  $msg  <==") }
fun Logd(tag:String = LOG_TAG,msg:String) { Log.d(tag, "==>  $msg  <==") }
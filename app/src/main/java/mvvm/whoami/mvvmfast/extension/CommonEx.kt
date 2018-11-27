package mvvm.whoami.mvvmfast.extension

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
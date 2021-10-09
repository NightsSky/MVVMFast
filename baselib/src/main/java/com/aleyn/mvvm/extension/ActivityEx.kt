
package com.aleyn.mvvm.extension

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * activity类相关扩展
 */


/**
 * 跳转，不带参数
 * example
 * activityTo(DataBindingTestActivity::class.java)
 */
  fun  Activity.activityTo(clazz: Class<*>) {
    this.startActivity(Intent(this, clazz))
}
fun Fragment.activityTo(clazz: Class<*>) {
    this.startActivity(Intent(activity, clazz))
}


/**
 * 跳转，带参数
 */
fun Activity.activityTo(clazz: Class<*>,lambda:Intent.()->Unit) {
    val intent = Intent(this, clazz)
        intent.apply { lambda() }
    this.startActivity(intent)
}
fun Fragment.activityTo(clazz: Class<*>,lambda:Intent.()->Unit) {

    val intent = Intent(activity, clazz)
    intent.apply { lambda() }
    this.startActivity(intent)
}


fun Activity.activityToForResult(clazz: Class<*>, requestCode: Int) {
    val intent = Intent()
    intent.setClass(this, clazz)
    this.startActivityForResult(intent, requestCode)
}



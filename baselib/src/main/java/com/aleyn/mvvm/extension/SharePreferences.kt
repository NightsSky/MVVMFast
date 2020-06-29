
package com.aleyn.mvvm.extension

import android.content.SharedPreferences
import com.google.gson.Gson
import mvvm.whoami.mvvmfast.app.MyApp
import java.lang.reflect.Type

/**
 * Created by XKL on 2018/6/28.
 */

val sp: SharedPreferences by lazy {
    MyApp.getInstance().getSharedPreferences("FAST", 0)
}

/**
 * 存入sp
 * @param key
 * @param value
 */
fun SharedPreferences.put(key: String, value: Any) {
    edit().apply {
        when (value) {
            is String -> putString(key,value)
            is Int -> putInt(key,value)
            is Float -> putFloat(key,value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
        }
    }.apply()

}



/**
 * 存入对象
 * 自动转为json
 */
fun  SharedPreferences.putAny(key: String, value: Any) {
    this.put(key, Gson().toJson(value))
}

/**
 * 获取对象
 */
inline fun <reified T:Any> SharedPreferences.getAny(key: String):T? {
    val str = this.getString(key,"")
    return if (str!!.isEmpty()) {
        null
    } else {
        Gson().fromJson(str, T::class.java)
    }
}

/**
 * 获取list对象
 */
inline fun <reified T:Any> SharedPreferences.getListAny(key: String,type: Type):List<T>? {
    val str = this.getString(key,"")
    return if (str!!.isEmpty()) {
        null
    } else {
        Gson().fromJson(str, type)
    }
}
/**
 * 清除所有sp
 */
fun SharedPreferences.clear() {
    this.edit().clear().apply()
}
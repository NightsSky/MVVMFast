package com.whoami.r.yellmovie.utils

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * Created by XKL on 2018/8/8.
 */
//abstract class JsonCallBack<T>(var clazz: Class<T>) :AbsCallback<T>(){
//
//    override fun convertResponse(response: Response?): T? {
//        val body  = response?.body() ?: return null
//        var data = null
//        var gson = Gson()
//        val jsonReader = JsonReader(body.charStream())
//        if (clazz!=null) data = gson.fromJson(jsonReader, clazz)
//        return data
//    }
//}

abstract class JsonCallback<T> : AbsCallback<T> {

    private var type: Type? = null
    private var clazz: Class<T>? = null

    constructor()
    constructor(type: Type) {
        this.type = type
    }

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        /*
         * 一般直接 new JsonCallback 会直接用无参构造器，但是无参构造器不能带有Bean类类型，
         * 无参的Bean类类型在泛型T中已传入，所以在这里先判断一下，如果为空，就获取一下。
         */
        if (type == null) {
            if (clazz == null) {
                val genType = javaClass.genericSuperclass
                type = (genType as ParameterizedType).actualTypeArguments[0]
            }
        }

        val body = response.body() ?: return null
        var data: T? = null
        val gson = Gson()
        val jsonReader = JsonReader(body.charStream())
        if (type != null) data = gson.fromJson(jsonReader, type)
        if (clazz != null) data = gson.fromJson<T>(jsonReader, clazz)
        return data
    }
}

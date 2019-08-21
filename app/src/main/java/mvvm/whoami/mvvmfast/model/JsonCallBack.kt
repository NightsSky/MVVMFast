package mvvm.whoami.mvvmfast.model

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import mvvm.whoami.mvvmfast.base.BaseResponse
import okhttp3.Response
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by XKL on 2018/8/8.
 */

abstract class JsonCallback<T> : AbsCallback<T>() {


    override fun onSuccess(response: com.lzy.okgo.model.Response<T>?) {
        success(response?.body())
    }

    abstract fun success(response: T?)


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0] as? ParameterizedType ?: throw IllegalStateException("没有填写泛型参数")

        val rawType = type .rawType
        val body = response.body() ?: return null
        val gson = Gson()
        val jsonReader = JsonReader(body.charStream())
        if (rawType != BaseResponse::class.java) {
            val data = gson.fromJson<T>(jsonReader, type)
            response.close()
            return data
        } else {
            //有数据类型，表示有data
            val baseResponse = gson.fromJson<BaseResponse<*>>(jsonReader, type)
            response.close()
            when (baseResponse.code) {
                0 -> {//成功
                }
                1021->{
                     throw IllegalStateException("err")
                }

                else -> {
                     throw IllegalStateException(baseResponse.message)
                }
            }
            return baseResponse as T
        }

    }

    override fun onError(response: com.lzy.okgo.model.Response<T>) {
        super.onError(response)
        if (response.exception is UnknownHostException || response.exception is ConnectException) {
        } else if (response.exception is SocketTimeoutException) {
        } else if (response.exception is IllegalStateException) {
        } else {
        }
    }
}

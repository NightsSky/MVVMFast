package mvvm.whoami.mvvmfast.model.rxhttp

import com.blankj.utilcode.util.ActivityUtils
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.TimeoutCancellationException

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

@Parser(name = "Response")
open class ResponseParser<T> : TypeParser<T> {

    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val data: Response<T> = response.convertTo(Response::class, *types)
        val t = data.data     //获取data字段
        if (data.errorCode != 0 || t == null) { //code不等于0，说明数据不正确，抛出异常
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t  //最后返回data字段



//        val type: Type = ParameterizedTypeImpl[Response::class.java, mType] //获取泛型类型
//        val data: Response<T> = convert(response, type)   //获取Response对象
//        val t = data.data                             //获取data字段
//
//        if (data.errorCode == 202) {//登录过期
//
////            ActivityUtils.startActivity(LoginMainActivity::class.java)
//            //结束除最新之外的所有 Activity
//            ActivityUtils.finishAllActivitiesExceptNewest()
//            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
//        } else if (data.errorCode != 0 || t == null) { //code不等于200，说明数据不正确，抛出异常
//            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
//        }
        return t
    }
}
//异常字段扩展

val Throwable.code: Int
    get() =
        when (this) {
            is HttpStatusCodeException -> this.statusCode //Http状态码异常
            is ParseException -> this.errorCode.toIntOrNull() ?: -1     //业务code异常
            else -> -1
        }

val Throwable.msg: String
    get() {
        return if (this is UnknownHostException) { //网络异常
            "当前无网络，请检查你的网络设置"
        } else if (
            this is SocketTimeoutException  //okhttp全局设置超时
            || this is TimeoutException     //rxjava中的timeout方法超时
            || this is TimeoutCancellationException  //协程超时
        ) {
            "连接超时,请稍后再试"
        } else if (this is ConnectException) {
            "网络不给力，请稍候重试！"
        } else if (this is HttpStatusCodeException) {               //请求失败异常
            "Http状态码异常"
        } else if (this is JsonSyntaxException) {  //请求成功，但Json语法异常,导致解析失败
            "数据解析失败,请检查数据是否正确"
        } else if (this is ParseException) {       // ParseException异常表明请求成功，但是数据不正确
            this.message ?: errorCode   //msg为空，显示code
        } else {
            "请求失败，请稍后再试"
        }
    }



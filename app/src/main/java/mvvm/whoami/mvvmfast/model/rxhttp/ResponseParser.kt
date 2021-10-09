package mvvm.whoami.mvvmfast.model.rxhttp

import com.blankj.utilcode.util.ActivityUtils

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import java.io.IOException
import java.lang.reflect.Type

@Parser(name = "Response")
open class ResponseParser<T> : AbstractParser<T> {

    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val type: Type = ParameterizedTypeImpl[Response::class.java, mType] //获取泛型类型
        val data: Response<T> = convert(response, type)   //获取Response对象
        val t = data.data                             //获取data字段

        if (data.errorCode == 202) {//登录过期

//            ActivityUtils.startActivity(LoginMainActivity::class.java)
            //结束除最新之外的所有 Activity
            ActivityUtils.finishAllActivitiesExceptNewest()
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        } else if (data.errorCode != 0 || t == null) { //code不等于200，说明数据不正确，抛出异常
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t
    }
}


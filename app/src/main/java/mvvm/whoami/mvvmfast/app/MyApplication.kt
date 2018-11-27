package mvvm.whoami.mvvmfast.app

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.lzy.okgo.model.HttpParams
import mvvm.whoami.mvvmfast.base.AppManager
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created by XKL on 2018/11/23.
 */
class MyApplication:Application() {

    companion object {
        private  var instance:Application?=null
        fun getInstance() = instance!!
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    //初始化组件
    private fun initComponent() {

        AppManager.getAppManager().initManager(this)

        initOkGo()
    }

    /**
     * 网络请求初始化设置 okgo
     */
    private fun initOkGo() {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO)
        builder.addInterceptor(loggingInterceptor)
//全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
//全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
//全局的连接超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS)
//        val headers = HttpHeaders()
//        headers.put("commonHeaderKey1", "commonHeaderValue1")    //header不支持中文，不允许有特殊字符
//        headers.put("commonHeaderKey2", "commonHeaderValue2")
        val params = HttpParams()
//        params.put("commonParamsKey1", "commonParamsValue1")     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数")
//-------------------------------------------------------------------------------------//

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)  //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params)                       //全局公共参数
    }
}
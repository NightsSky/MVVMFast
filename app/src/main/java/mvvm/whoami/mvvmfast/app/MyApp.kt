package mvvm.whoami.mvvmfast.app

import com.aleyn.mvvm.app.BaseApp
import mvvm.whoami.mvvmfast.BuildConfig
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.utils.LogUtil.setDebug


class MyApp: BaseApp(){

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    //初始化组件
    private fun initComponent() {
        RxHttpPlugins.init(null)
            .setDebug(true)
    }

}
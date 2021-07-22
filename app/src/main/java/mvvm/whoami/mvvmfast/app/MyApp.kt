package mvvm.whoami.mvvmfast.app

import android.app.Application
import com.aleyn.mvvm.app.BaseApp

/**
 * Created by XKL on 2018/11/23.
 */
class MyApp: BaseApp(){

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
    }

}
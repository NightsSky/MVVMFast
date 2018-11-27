package mvvm.whoami.mvvmfast.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import mvvm.whoami.mvvmfast.widget.LoadingView
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T:BaseViewModel> : AppCompatActivity() {
    lateinit var vm:T
    val loading:LoadingView by lazy {
        LoadingView.Builder(this).create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initVM()
        initView()
        initData()
    }

    private fun initVM() {
            val modelClass: Class<T>
            val type = javaClass.genericSuperclass
            modelClass = (type as ParameterizedType).actualTypeArguments[0] as Class<T>
            vm = createViewModel<T>(this, modelClass)
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    //初始化view
    abstract fun initView()

    //初始化数据
     fun initData(){

    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    private fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProviders.of(activity).get(cls)
    }
}
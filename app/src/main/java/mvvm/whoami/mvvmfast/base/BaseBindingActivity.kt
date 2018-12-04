package mvvm.whoami.mvvmfast.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import mvvm.whoami.mvvmfast.widget.LoadingView
import java.lang.reflect.ParameterizedType

/**
 *  created by xkl
 *  time: 2018/12/4 16:49
 *  function:
 */
abstract class BaseBindingActivity<VM:BaseViewModel,V : ViewDataBinding> : AppCompatActivity() {
    lateinit var vm:VM
    lateinit var binding:V
    val loading: LoadingView by lazy {
        LoadingView.Builder(this).create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding(savedInstanceState)
        initView()
        initData()
    }

    /**
     * 初始化 databinding和 viewmodel
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, initContentView())
        val modelClass: Class<VM>
        val type = javaClass.genericSuperclass
        modelClass = (type as ParameterizedType).actualTypeArguments[0] as Class<VM>
        vm = createViewModel<VM>(this, modelClass)
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
    private fun <VM : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<VM>): VM {
        return ViewModelProviders.of(activity).get(cls)
    }
}
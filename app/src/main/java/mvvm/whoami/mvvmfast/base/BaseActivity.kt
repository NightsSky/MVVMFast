package mvvm.whoami.mvvmfast.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.noober.background.BackgroundLibrary
import me.jessyan.autosize.AutoSizeConfig
import mvvm.whoami.mvvmfast.widget.LoadingView
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T:BaseViewModel> : AppCompatActivity() {
    lateinit var vm:T
    val loading:LoadingView by lazy {
        LoadingView.Builder(this).create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        AutoSizeConfig.getInstance().isBaseOnWidth = initBaseWidthOrHeight()
        AutoSizeConfig.getInstance().isExcludeFontScale = true//防止系统字体大小影响 app 的字体大小，即使你使用的是 sp 也可以奏效
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initVM()
        initView()
        initData()
    }
    /**
     * 重写此方法改变对宽高的适配
     * @androidAutoSize
     * @link https://github.com/JessYanCoding/AndroidAutoSize/issues/8
     * @return true  默认以宽适配    false 以高适配
     */
    open fun initBaseWidthOrHeight():Boolean{
        return true
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
package mvvm.whoami.mvvmfast.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mvvm.whoami.mvvmfast.widget.LoadingView

abstract class BaseFragment<T:BaseViewModel>:Fragment() {
    lateinit var vm:T
    val loading: LoadingView by lazy {
        LoadingView.Builder(activity).create()
    }
    public abstract fun setView(): Int
    public abstract fun initView()
    public abstract fun initViewModel():T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setView(), container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    fun initData(savedInstanceState:Bundle?){
        vm = initViewModel()

    }
}
package mvvm.whoami.mvvmfast.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import mvvm.whoami.mvvmfast.widget.LoadingView

abstract class BaseBindingFragment<VM:BaseViewModel,V : ViewDataBinding>: Fragment() {
    lateinit var vm:VM
    lateinit var binding:V
    val loading: LoadingView by lazy {
        LoadingView.Builder(activity).create()
    }
     abstract fun initContentView(): Int
     abstract fun initView()
     abstract fun initViewModel():VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, initContentView(), container, false)
        vm = initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    open fun initData(savedInstanceState:Bundle?){

    }
}
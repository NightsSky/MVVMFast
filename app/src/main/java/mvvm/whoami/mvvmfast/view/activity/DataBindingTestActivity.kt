package mvvm.whoami.mvvmfast.view.activity

import android.util.Log
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.base.BaseActivity
import mvvm.whoami.mvvmfast.base.BaseBindingActivity
import mvvm.whoami.mvvmfast.databinding.ActivityMain2Binding
import mvvm.whoami.mvvmfast.viewmodel.MainViewModel

class DataBindingTestActivity : BaseBindingActivity<MainViewModel,ActivityMain2Binding>() {
    override fun initContentView(): Int {
        return R.layout.activity_main2
    }

    override fun initView() {

        binding.vm = vm
        Log.d("MSG2",vm.number.toString())
        Log.d("MSG2",intent.getIntExtra("number",0).toString())

    }


}
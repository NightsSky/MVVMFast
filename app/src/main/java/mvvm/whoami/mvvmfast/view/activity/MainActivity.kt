package mvvm.whoami.mvvmfast.view.activity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.base.BaseActivity
import mvvm.whoami.mvvmfast.extension.activityTo
import mvvm.whoami.mvvmfast.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {
    override fun initContentView(): Int {
        return R.layout.activity_main
    }
    override fun initView() {
//        loading.show()
//        GlobalScope.launch {
//            delay(3000)
//
//            loading.dismiss()
//        }
        next.setOnClickListener {

            activityTo(Main2Activity::class.java){
                putExtra("number",3)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.number = 4000
        Log.d("MSG",vm.number.toString())

    }
}

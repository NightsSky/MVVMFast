package mvvm.whoami.mvvmfast.view.detail

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import mvvm.whoami.mvvmfast.R

class DetailActivity : BaseActivity<NoViewModel, ViewDataBinding>() {


    override fun layoutId() = R.layout.activity_detail

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
        intent.getStringExtra("url")?.let {

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

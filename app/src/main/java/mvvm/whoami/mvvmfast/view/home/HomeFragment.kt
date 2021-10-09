package mvvm.whoami.mvvmfast.view.home

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aleyn.mvvm.base.BaseFragment
import com.stx.xhb.androidx.XBanner
import mvvm.whoami.mvvmfast.R

/**
 *   @auther : Aleyn
 *   time   : 2019/11/02
 */
class HomeFragment : BaseFragment<HomeViewModel, ViewDataBinding>() {

    private var page: Int = 0
    private lateinit var banner: XBanner

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun layoutId() = R.layout.home_fragment

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun lazyLoadData() {

    }

}

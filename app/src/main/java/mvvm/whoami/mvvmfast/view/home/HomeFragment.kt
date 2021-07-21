package mvvm.whoami.mvvmfast.view.home

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleyn.mvvm.base.BaseFragment
import mvvm.whoami.mvvmfast.network.entity.ArticlesBean
import mvvm.whoami.mvvmfast.view.detail.DetailActivity
import mvvm.whoami.mvvmfast.utils.GlideImageLoader
import com.stx.xhb.androidx.XBanner
import kotlinx.android.synthetic.main.home_fragment.*
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

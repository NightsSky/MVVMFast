package mvvm.whoami.mvvmfast.view.home

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleyn.mvvm.base.BaseFragment
import com.aleyn.mvvm.extension.activityTo
import com.stx.xhb.androidx.XBanner
import kotlinx.android.synthetic.main.home_fragment.*
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.utils.init
import mvvm.whoami.mvvmfast.view.detail.DetailActivity
import mvvm.whoami.mvvmfast.view.home.adapter.HomeItemAdapter


class HomeFragment : BaseFragment<HomeViewModel, ViewDataBinding>() {

    private lateinit var mAdapter: HomeItemAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun layoutId() = R.layout.home_fragment

    //初始化控件
    override fun initView(savedInstanceState: Bundle?) {
        mAdapter = HomeItemAdapter()
        rcy.init(LinearLayoutManager(requireContext()),mAdapter)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            activityTo(DetailActivity::class.java)
        }
    }

    //数据加载
    override fun lazyLoadData() {
        viewModel.getHomeList()
        viewModel.listData.observe(this, Observer {
            mAdapter.setList(it)
        })
    }

}

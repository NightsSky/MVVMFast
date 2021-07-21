package mvvm.whoami.mvvmfast.view.me

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleyn.mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.me_fragment.*
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.databinding.MeFragmentBinding

class MeFragment : BaseFragment<MeViewModel, MeFragmentBinding>() {


    companion object {
        fun newInstance() = MeFragment()
    }

    override fun layoutId() = R.layout.me_fragment

    override fun initView(savedInstanceState: Bundle?) {
        with(rv_me_uesd_web) {
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.popularWeb.observe(viewLifecycleOwner, Observer {
//            mAdapter.setNewData(it)
        })

    }

    override fun lazyLoadData() {
        viewModel.getPopularWeb()
    }
}

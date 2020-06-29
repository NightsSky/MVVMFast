package mvvm.whoami.mvvmfast.view.project

import android.content.Intent
import android.os.Bundle
import com.aleyn.mvvm.base.BaseFragment
import com.aleyn.mvvm.event.Message
import mvvm.whoami.mvvmfast.network.entity.ArticlesBean
import mvvm.whoami.mvvmfast.view.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.databinding.ProjectFragmentBinding

class ProjectFragment : BaseFragment<ProjectViewModel, ProjectFragmentBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun layoutId() = R.layout.project_fragment

    override fun initView(savedInstanceState: Bundle?) {
//        mBinding?.viewModel = viewModel
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun lazyLoadData() {
        viewModel.getFirstData()
    }

    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {
                val bean = msg.obj as ArticlesBean
                val intent = Intent().apply {
                    setClass(activity!!, DetailActivity::class.java)
                    putExtra("url", bean.link)
                }
                startActivity(intent)
            }
        }
    }
}

package mvvm.whoami.mvvmfast.view.project

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aleyn.mvvm.base.BaseFragment
import com.aleyn.mvvm.event.Message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import mvvm.whoami.mvvmfast.R

class ProjectFragment : BaseFragment<ProjectViewModel, ViewDataBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun layoutId() = R.layout.project_fragment

    override fun initView(savedInstanceState: Bundle?) {
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun lazyLoadData() {
    }

    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {

            }
        }
    }
}

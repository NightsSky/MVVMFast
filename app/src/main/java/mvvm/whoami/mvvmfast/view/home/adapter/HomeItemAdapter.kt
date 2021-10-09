package mvvm.whoami.mvvmfast.view.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

import kotlinx.android.synthetic.main.item_home_layout.view.*
import mvvm.whoami.mvvmfast.R
import mvvm.whoami.mvvmfast.model.entity.ListItem

class HomeItemAdapter : BaseQuickAdapter<ListItem, BaseViewHolder>(R.layout.item_home_layout),
    LoadMoreModule {

    override fun convert(helper: BaseViewHolder, item: ListItem) {
        with(helper.itemView) {
            name.text = item.title
        }
    }

}
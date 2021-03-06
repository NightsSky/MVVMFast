package mvvm.whoami.mvvmfast.view.home

import androidx.lifecycle.MutableLiveData
import com.aleyn.mvvm.base.BaseViewModel
import mvvm.whoami.mvvmfast.network.entity.BannerBean
import mvvm.whoami.mvvmfast.network.entity.HomeListBean

class HomeViewModel : BaseViewModel() {

//    private val homeRepository by lazy { InjectorUtil.getHomeRepository() }

    private val mBanners = MutableLiveData<List<BannerBean>>()

    private val projectData = MutableLiveData<HomeListBean>()

    fun getBanner(refresh: Boolean = false): MutableLiveData<List<BannerBean>> {
        launchGo({
//            mBanners.value = homeRepository.getBannerData(refresh)
        })
        return mBanners
    }

    /**
     * @param page 页码
     * @param refresh 是否刷新
     */
    fun getHomeList(page: Int, refresh: Boolean = false): MutableLiveData<HomeListBean> {
        launchGo({
//            projectData.value = homeRepository.getHomeList(page, refresh)
        })
        return projectData
    }
}

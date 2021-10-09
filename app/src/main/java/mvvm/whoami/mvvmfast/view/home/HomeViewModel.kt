package mvvm.whoami.mvvmfast.view.home

import androidx.lifecycle.MutableLiveData
import com.aleyn.mvvm.base.BaseViewModel
import mvvm.whoami.mvvmfast.model.API
import mvvm.whoami.mvvmfast.model.entity.Datas
import mvvm.whoami.mvvmfast.model.entity.ListItem
import rxhttp.awaitResult
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

class HomeViewModel : BaseViewModel() {

    val listData = MutableLiveData<List<ListItem>>()
    fun getHomeList() {
        lifeScope({
            RxHttp.get(API.HomeList)
                .add("params","password")
                .toResponse<Datas>()
                .awaitResult {
                    //请求成功，通过it拿到data对象
                    listData.value = it.datas
                }.onFailure {
                    //请求异常，通过it拿到Throwable对象
                    it.printStackTrace()
                }
            //请求完成

        },isShowDialog = true)
    }
}

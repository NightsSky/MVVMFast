package mvvm.whoami.mvvmfast.view.home

import androidx.lifecycle.MutableLiveData
import com.aleyn.mvvm.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import mvvm.whoami.mvvmfast.model.API
import mvvm.whoami.mvvmfast.model.entity.Datas
import mvvm.whoami.mvvmfast.model.entity.ListItem
import mvvm.whoami.mvvmfast.model.rxhttp.Response
import rxhttp.awaitResult
import rxhttp.toFlow
import rxhttp.toList
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toFlowResponse
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

    //flow方式请求
    fun getHomeList2() {
        lifeScope({
            RxHttp.get(API.HomeList)
                .add("params","password")
                .toFlowResponse<Datas>()
                .catch {
                    //请求异常，通过it拿到Throwable对象
                    it.printStackTrace()
                }.collect {
                    //请求成功，通过it拿到data对象
                    listData.value = it.datas
                }

            //请求完成

        },isShowDialog = true)
    }


}

package mvvm.whoami.mvvmfast.view.home

import androidx.lifecycle.viewModelScope
import com.aleyn.mvvm.base.BaseViewModel
import kotlinx.coroutines.launch
import mvvm.whoami.mvvmfast.model.API
import rxhttp.wrapper.param.RxHttp

class HomeViewModel : BaseViewModel() {



    fun getHomeList() {
       viewModelScope.launch {
           RxHttp.postForm(API.login)
               .add("params","password")
               .as
       }
    }
}

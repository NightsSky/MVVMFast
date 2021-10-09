package com.aleyn.mvvm.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.aleyn.mvvm.event.Message
import com.aleyn.mvvm.event.SingleLiveEvent
import com.blankj.utilcode.util.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *   BaseViewModel
 */
open class BaseViewModel : AndroidViewModel(Utils.getApp()), LifecycleObserver {

    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    /**
     *
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun lifeScope(
        block: suspend CoroutineScope.() -> Unit,
        error: (Throwable) -> Unit = {},
        start: () -> Unit = {},
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        viewModelScope.launch{
            block()
        }
//        rxLifeScope.launch(
//            {
//                block()
//            },
//            {
//                error(it)
//                it.printStackTrace()
//                it.show()
//            },
//            {
//                start()
//                if (isShowDialog) defUI.showDialog.call()
//            },
//            {
//                defUI.dismissDialog.call()
//                complete()
//                defUI.msgEvent.value = Message(0x123, "请求完成了")
//            }
//        )
    }

    /**
     * UI事件
     */
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val msgEvent by lazy { SingleLiveEvent<Message>() }
    }


}
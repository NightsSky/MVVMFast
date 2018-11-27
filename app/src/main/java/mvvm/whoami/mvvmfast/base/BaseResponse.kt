package mvvm.whoami.mvvmfast.base

/**
 * Created by XKL on 2018/11/24.
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
 class BaseResponse<T> {
    var code: Int = 0
    var message: String? = null
    var result: T? = null

    val isOk: Boolean
        get() = code == 200
}

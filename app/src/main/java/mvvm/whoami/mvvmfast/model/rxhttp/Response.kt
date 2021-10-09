package mvvm.whoami.mvvmfast.model.rxhttp

class Response<T> {
    var errorCode = 0
    var errorMsg : String? = null
    var data : T? =null
}
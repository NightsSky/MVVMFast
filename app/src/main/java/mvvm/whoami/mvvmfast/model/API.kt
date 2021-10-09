package mvvm.whoami.mvvmfast.model

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * 接口信息
 */
class API {
    companion object {
        @DefaultDomain
         const val BASE_URL = "https://www.wanandroid.com"

        /*************************************公用接口**********************************************************/

        /**
         * 首页数据
         */
        const val HomeList = "/article/list/0/json"

        /*************************************主页**********************************************************/




    }
}

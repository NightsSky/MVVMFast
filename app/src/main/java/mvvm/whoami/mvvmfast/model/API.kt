package mvvm.whoami.mvvmfast.model

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * 接口信息
 */
class API {
    companion object {
        @DefaultDomain
         const val BASE_URL = "http://116.62.27.141:8080"

        /*************************************公用接口**********************************************************/

        /**
         * 登录
         */
        const val login = "/app/login"

        /*************************************主页**********************************************************/


        /*************************************考核**********************************************************/

        /*************************************视频编辑**********************************************************/

        /*************************************会议**********************************************************/


    }
}

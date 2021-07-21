package mvvm.whoami.mvvmfast.utils

import android.view.View
import android.widget.ImageView
import coil.load
import mvvm.whoami.mvvmfast.network.entity.BannerBean
import com.stx.xhb.androidx.XBanner

/**
 *   @auther : Aleyn
 *   time   : 2019/09/05
 */
class GlideImageLoader : XBanner.XBannerAdapter {

    override fun loadBanner(banner: XBanner?, model: Any?, view: View?, position: Int) {
        (view as ImageView).load((model as BannerBean).xBannerUrl)
    }

}
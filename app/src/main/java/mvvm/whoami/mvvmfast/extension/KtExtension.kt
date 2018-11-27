package mvvm.whoami.mvvmfast.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/**
 * Created by XKL on 2018/8/28.
 */
val options = RequestOptions()
//        .placeholder(R)	//加载成功之前占位图
//        .error(R.mipmap.ic_banana)	//加载错误之后的错误图
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)	//只缓存最终的图片

fun ImageView.load(url:Any) {
    Glide.with(this.context)
            .load(url)
            .apply(options)
            .into(this)
}

fun View.gone() {
    this.visibility = View.GONE
}
fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}



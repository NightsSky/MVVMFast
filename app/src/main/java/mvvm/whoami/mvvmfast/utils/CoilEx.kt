import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation



/**
 * 加载圆形图片
 */
fun ImageView.loadCircleImg(url: String,error:Int?=null) {
    this.load(url){
        transformations(CircleCropTransformation())
        if (error != null) {
            error(error)
        }
    }

}


package mvvm.whoami.mvvmfast.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.aleyn.mvvm.extension.gone
import com.aleyn.mvvm.extension.invisible
import com.aleyn.mvvm.extension.visible
import kotlinx.android.synthetic.main.custom_title_bar.view.*
import mvvm.whoami.mvvmfast.R

/**
 * 通用titleBar
 */
class CustomTitleBar(context: Context, var attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var stringLeftTitle: String = ""
    private var stringCenterTitle: String = ""
    private var stringRightTitle: String = ""

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true)
        initView()
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun initView() {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.TitleView)
            setLeftTitle(a.getString(R.styleable.TitleView_leftTitle) ?: "")
            setCenterTitle(a.getString(R.styleable.TitleView_centerTitle) ?: "")
            setRightTitle(a.getString(R.styleable.TitleView_rightTitle) ?: "")
            setRootBackgroundColor(a.getInt(R.styleable.TitleView_backgroundColor,Color.WHITE))
            a.recycle()
        }
    }
    fun setRootBackgroundColor(color:Int) {
        root_bar.setBackgroundColor(color)
    }


    fun setLeftClickListener(listener: () -> Unit) {
        leftImg.setOnClickListener {
            listener()
        }
    }

    fun setRightClickListener(listener: () -> Unit) {
        rightTitle.setOnClickListener {
            listener()
        }
    }
    fun setLeftTitle(title: String) {
        if (title.isNotEmpty()) {
            leftTitle.text = title
            leftTitle.visible()
        } else {
            leftTitle.invisible()
        }

    }

    fun setCenterTitle(title: String) {
        if (title.isNotEmpty()) {
            centerTitle.text = title
            centerTitle.visible()
        } else {
            centerTitle.invisible()
        }

    }

    fun setRightTitle(title: String) {
        if (title.isNotEmpty()) {
            rightTitle.text = title
            rightTitle.visible()
        } else {
            rightTitle.gone()
        }

    }

    fun setRightImg(img: Drawable) {
        rightImg.setImageDrawable(img)
        rightImg.visible()
    }

    fun setIsShowBack(boolean: Boolean) {
        if (boolean) {
            leftImg.visible()
        } else {
            leftImg.gone()
        }
    }

}
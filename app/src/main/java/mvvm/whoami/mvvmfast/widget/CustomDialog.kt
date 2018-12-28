package mvvm.whoami.mvvmfast.widget

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mvvm.whoami.mvvmfast.R

/**
 * created by xkl
 * time: 2018/12/7 11:20
 * function:自定义dialog模板
 */
class CustomDialog : Dialog {

    constructor(context: Context) : super(context) {}
    constructor(context: Context, theme: Int) : super(context, theme) {}

    class Builder(private val context: Context,style:Int = R.style.Dialog) {
        private lateinit var layout: View
        private val dialog = CustomDialog(context, style)

        /**
         * 设置dialog的自定义layout布局
         */
        fun setContentView(view: Int): Builder {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layout = inflater.inflate(view, null)
            dialog.addContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            return this
        }

        /**
         * 点击外部是否可取消
         */
        fun setCanceledOnTouchOutside(boolean: Boolean): Builder {
            dialog.setCanceledOnTouchOutside(boolean)
            return this
        }
        /**
         * 添加view的点击事件
         * 通过lamda传入了dialog对象  可用于 调用show()方法或者设置一些属性
         */
        fun addViewListener(listener:View.(Dialog)->Unit):Builder {
            layout.listener(dialog)
            return this
        }

        /**
         * 返回创建的dialog
         */
        fun create(): Dialog {
            return dialog
        }


    }
}

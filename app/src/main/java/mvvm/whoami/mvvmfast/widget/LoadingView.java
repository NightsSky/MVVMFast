package mvvm.whoami.mvvmfast.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import mvvm.whoami.mvvmfast.R;


/**
 * Created by XKL on 2018/6/1.
 *
 */


public class LoadingView extends Dialog {


    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        private Context context;
        private String message;
        private boolean isShowMessage=false;
        private boolean isCancelable=false;
        private boolean isCancelOutside=false;


        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         * @param message
         * @return
         */

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         * @param isShowMessage
         * @return
         */
        public Builder setShowMessage(boolean isShowMessage){
            this.isShowMessage=isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */
        public Builder setCancelable(boolean isCancelable){
            this.isCancelable=isCancelable;
            return this;
        }
        /**
         * 设置是否可以取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside){
            this.isCancelOutside=isCancelOutside;
            return this;
        }

        public LoadingView create(){

            LayoutInflater inflater = LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.loading_view,null);
            LoadingView loading=new LoadingView(context,R.style.DialogTheme);
//            TextView msgText= (TextView) view.findViewById(R.id.loadingText);
//            if(isShowMessage){
//                msgText.setText(message);
//            }else{
//                msgText.setVisibility(View.gone);
//            }
            loading.setContentView(view);
            loading.setCancelable(isCancelable);
            loading.setCanceledOnTouchOutside(isCancelOutside);
            return  loading;

        }


    }
}
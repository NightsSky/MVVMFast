package mvvm.whoami.mvvmfast.model;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import mvvm.whoami.mvvmfast.base.BaseResponse;
import okhttp3.ResponseBody;

public class JsonCallBack2<T> extends AbsCallback<T> {


    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];

        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("没有填写泛型参数");

        }
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != BaseResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
                //有数据类型，表示有data
                BaseResponse baseResponse = gson.fromJson(jsonReader, type);
                response.close();
                int code = baseResponse.getCode();
            if (code == 0) {

            } else if (code == 400) {
                throw new IllegalStateException("各种错误");
            }
            return (T) baseResponse;

        }
    }

    @Override
    public void onSuccess(Response<T> response) {

    }

}

package com.sinano.http;

import com.sinano.base.BaseApplication;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.Constant;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class Network {


    public static NetworkInterface getObserableIntence() {

        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request authorised = originalRequest.newBuilder()
                        .header("token", (String) SpUtils.getParam(BaseApplication.getContext(), Constant.TOKEN, ""))
                        .build();
                Response proceed = chain.proceed(authorised);
                switch (proceed.code()) {
                    case 403:
                        SpUtils.putParms(BaseApplication.getContext(), Constant.TOKEN, "");
                        SpUtils.putParms(BaseApplication.getContext(), Constant.USER_NAME, "");
                        SpUtils.putParms(BaseApplication.getContext(), Constant.USER_ID, "");
                        SpUtils.putParms(BaseApplication.getContext(), Constant.USER, "");
                        ToastUtils.showTextToast(proceed.message());
                        IntentUtils.startActivity(BaseApplication.getContext(), LoginActivity.class);
                        break;
                }

                return chain.proceed(authorised);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(mTokenInterceptor)
                .build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);
    }


    public static NetworkInterface getObserableIntenceComm() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(Constant.COMM)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);
    }

}

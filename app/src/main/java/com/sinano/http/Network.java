package com.sinano.http;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.sinano.R;
import com.sinano.base.BaseApplication;
import com.sinano.user.view.login.LoginActivity;
import com.sinano.utils.Constant;
import com.sinano.utils.DialogUtils;
import com.sinano.utils.IntentUtils;
import com.sinano.utils.NetUtil;
import com.sinano.utils.SpUtils;
import com.sinano.utils.ToastUtils;
import com.sinano.utils.UiUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
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
        return getObserableIntence(null);
    }


    public static NetworkInterface getObserableIntence(Context context) {

        if (context != null) {
            View view = DialogUtils.inflateView(context, R.layout.dialog_loading);
            DialogUtils.createDialogFour(view);
        }

        Cache cache = new Cache(BaseApplication.getContext().getCacheDir(), 10 * 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                        int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                        Request request = chain.request();
                        NetUtil.init(BaseApplication.getContext());
                        Log.e("vivi", "intercept: " + (String) SpUtils.getParam(BaseApplication.getContext(), Constant.TOKEN, ""));
                        if (NetUtil.getNetWorkState() != -1) {
                            maxAge = 0;
                            request = request.newBuilder()
                                    .addHeader("token", (String) SpUtils.getParam(BaseApplication.getContext(), Constant.TOKEN, ""))
                                    .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                                    .build();
                        } else {
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        if (NetUtil.getNetWorkState() != -1) {
                            response = response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            response = response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }

                        Response proceed = chain.proceed(request);
                        switch (proceed.code()) {
                            case 200:
                                if (context != null)
                                    DialogUtils.dissDialog();
                                break;
                            case 401:
                                SpUtils.putParms(BaseApplication.getContext(), Constant.TOKEN, "");
                                SpUtils.putParms(BaseApplication.getContext(), Constant.USER_NAME, "");
                                SpUtils.putParms(BaseApplication.getContext(), Constant.USER_ID, "");
                                SpUtils.putParms(BaseApplication.getContext(), Constant.USER, "");
                                UiUtils.getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtils.showTextToast(UiUtils.findStringBuId(R.string.card_guoqi));
                                    }
                                });
                                IntentUtils.startActivity(BaseApplication.getContext(), LoginActivity.class);
                                break;
                        }

                        return response;
                    }

                })
                .cache(cache)
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

    public static NetworkInterface getObserableIntenceLogin() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);
    }

}

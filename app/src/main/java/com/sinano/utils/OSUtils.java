package com.sinano.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.sinano.base.BaseApplication;

import java.io.File;

public class OSUtils {


    public static String langae = "ch";

    /**
     * 获取本app的安装路径
     *
     * @return
     */
    public static String getApkInstalledSrc() {
        return BaseApplication.getContext().getApplicationInfo().sourceDir;
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file    安装的apk文件
     */
    public static void installAPK(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.sinano.fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

//    public static void smartUpdate(Context context) {
//        Observable
//                .create(new Observable.OnSubscribe<File>() {
//                    @Override
//                    public void call(Subscriber<? super File> subscriber) {
//                        //假设patch.patch文件已经下载到sdcard上，切已经校验通过
//                        File patch = new File(Constant.PATCH_FILE_PATH);
//                        if (!patch.exists()) {
//                            subscriber.onError(new IOException("patch file not exist!"));
//                            return;
//                        }
//                        //定义生成的新包
//                        File newApk = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//                                getAppName(BaseApplication.getContext()) + ".apk");
//                        if (newApk.exists()) {
//                            newApk.delete();
//                        }
//                        //合并差分包
//                        try {
//                            BsPatchUtil.patch(getApkInstalledSrc(), newApk.getAbsolutePath(), patch.getAbsolutePath());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        if (newApk.exists()) {
//                            subscriber.onNext(newApk);
//                            subscriber.onCompleted();
//                            patch.delete();
//                        } else {
//                            subscriber.onError(new IOException("bspatch failed,file not exist!"));
//                        }
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                    }
//                })
//                .subscribe(new Subscriber<File>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("tag", Log.getStackTraceString(e));
//                    }
//
//                    @Override
//                    public void onNext(File file) {
//                        installAPK(context, file);
//                    }
//                });
//
//
//    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 得到App版本名
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String pName = context.getPackageName();
        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(pName,
                    PackageManager.GET_CONFIGURATIONS);
            return pinfo.versionName;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取版本号(内部识别号)
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取手机运行的android系统版本
     *
     * @return
     */
    public static String GetSystemVersion() {
        return Build.VERSION.RELEASE;
    }

}

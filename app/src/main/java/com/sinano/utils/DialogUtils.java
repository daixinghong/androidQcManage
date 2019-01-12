package com.sinano.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils {

    private static View popLayout;

    private static AlertDialog dialog;
    private static Context mContext;
    public static List<String> sList = new ArrayList<>();

    public static OnClickCallBack mOnClickCallBack;
    public static Dialog sDialog;

    public interface OnClickCallBack {
        void setOnClickCallBack();
    }

    public static void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        mOnClickCallBack = onClickCallBack;
    }


    public static View inflateView(Context context, int layoutId) {

        mContext = context;
        popLayout = View.inflate(context, layoutId, null);
        //【2new构建者设置布局+创建对话框】

        return popLayout;
    }

    public static Dialog createDialogFour(View view) {

        sDialog = new AlertDialog
                .Builder(mContext)
                .setView(view)
                .show();

        sDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        sDialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = sDialog.getWindow();
        WindowManager m = ((Activity) mContext).getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);

        if (!sDialog.isShowing()) {
            sDialog.show();
        }

        return sDialog;
    }


    public static void dissDialog() {
        if (sDialog != null)
            sDialog.dismiss();
    }


    private static IosDialogListener mIosDialogListener;

    public interface IosDialogListener {
        void onConfirmClickListener(View view);
    }

    public static void setOnConfirmClickListener(IosDialogListener iosDialogListener) {
        mIosDialogListener = iosDialogListener;
    }


}

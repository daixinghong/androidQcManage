package com.sinano.weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sinano.R;
import com.sinano.utils.Singleton;

/**
 * Dialog集合
 * Created by will.li on 2017/6/6.
 */
public class DialogCollection {

    private DialogCollection() {
    }
    private static final Singleton<DialogCollection> mInstance = new Singleton<DialogCollection>() {
        @Override
        protected DialogCollection create() {
            return new DialogCollection();
        }
    };

    public static DialogCollection getInstance() {
        return mInstance.get();
    }

    /**
     * 单按钮警示
     *
     * @param activity
     * @param strTitle      标题,可以不显示
     * @param strContent    内容
     * @param strContentTwo 内容二,可以隐藏不显示
     * @param strConfirm    按钮文字
     */

    /**
     * 双按钮警示
     *
     * @param activity
     * @param strTitle   标题
     * @param strContent 显示内容
     * @param cancel     左边按钮文字 可以为空变成单按钮/无按钮
     * @param confirm    右边按钮文字 可以为空变成单按钮/无按钮
     */
    public void showDoubleDialog(final Activity activity, String strTitle, String strContent, String cancel, String confirm, final DialogCallBack callBack) {

        final Dialog dialog = new Dialog(activity, R.style.explanation_dialog);
        dialog.show();

        //设置弹窗位置
        Window dialogWindow = dialog.getWindow();
        WindowManager m = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.gravity = Gravity.CENTER;
//        lp.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的比例
        lp.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的比例
        lp.alpha = 0.85f; // 设置屏幕透明度
        dialogWindow.setAttributes(lp);
        // dialogWindow.setWindowAnimations(R.style.dialogWindowAnim); //

        //弹窗布局
        View viewDialog = LayoutInflater.from(activity).inflate(
                R.layout.dialog_double_button, null);
        TextView title = (TextView) viewDialog.findViewById(R.id.tvExplanationContent);
        TextView content = (TextView) viewDialog.findViewById(R.id.tvExplanationContentAdd);
        Button mBtnCancel = (Button) viewDialog.findViewById(R.id.mBtnCancel);//左边按钮
        Button mBtnConfim = (Button) viewDialog.findViewById(R.id.mBtnConfirm);

        title.setText(strTitle);
        content.setText(strContent);
        if (TextUtils.isEmpty(strTitle)) {
            title.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(strContent)) {
            content.setVisibility(View.GONE);
        }
        //设置是否显示取消按钮
        if (TextUtils.isEmpty(cancel)) {
            mBtnCancel.setVisibility(View.GONE);
        } else {
            mBtnCancel.setVisibility(View.VISIBLE);
            mBtnCancel.setText(cancel);
        }
        //设置是否显示确定按钮
        if (TextUtils.isEmpty(confirm)) {
            mBtnConfim.setVisibility(View.GONE);
        } else {
            mBtnConfim.setVisibility(View.VISIBLE);
            mBtnConfim.setText(confirm);
        }

        // 设置对话框显示的View
        dialogWindow.setContentView(viewDialog);
        //设置点击外部区域是否可取消(一个按钮的时候都不可取消dialog)
        if (TextUtils.isEmpty(cancel)) {
            dialog.setCanceledOnTouchOutside(true);
        }
        if (TextUtils.isEmpty(confirm)) {
            dialog.setCanceledOnTouchOutside(true);
        }
        //没按钮和有2个按钮的时候点击外部都不消失dialog
        if (TextUtils.isEmpty(cancel) && TextUtils.isEmpty(confirm)) {
            dialog.setCanceledOnTouchOutside(false);
        }
        //
        if (!TextUtils.isEmpty(cancel) && !TextUtils.isEmpty(confirm)) {
            dialog.setCanceledOnTouchOutside(false);
        }
        // 取消操作
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (callBack != null) callBack.onCancel(dialog);
            }
        });
        // 确定操作
        mBtnConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                if (callBack != null) callBack.onConfirm(dialog);
            }
        });

    }



    /**
     * 普通弹窗按钮事件
     */
    public interface DialogCallBack {
        void onCancel(Dialog dialog);

        void onConfirm(Dialog dialog);
    }

    /**
     * input弹窗按钮事件
     */
    public interface InputCallBack {
        //初始化edittext,例如默认展示文字,显示删除图标等
        void initEditText(EditText change, IconTextView iconDelete, TextView tvConfirm);

        //输入的时候的回调事件
        void onTextChange(EditText change, IconTextView iconDelete, TextView tvConfirm, CharSequence s);

        void onCancel(Dialog dialog, EditText change, TextView cancel);

        void onConfirm(Dialog dialog, EditText change, TextView cancel);
    }

    /**
     * 列表弹窗item点击事件
     */
    public interface OnclickCallBack {
        void onclick(Dialog dialog);
    }



}

package com.mvp.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mvp.view.R;


/**
 * author：   tc
 * date：     2015/10/5 & 14:03
 * version    1.0
 * description 加载中的进度条对话框
 * modify by  ljy
 */
public class ProgressDialog extends Dialog {

    private Context context;

    public ProgressDialog(Context context) {
        super(context);
        this.context=context;
        View view = LayoutInflater.from(context).inflate(R.layout.include_progress_dialog_layout,
                null);
        setContentView(view);
        //点击屏幕dialog不消失
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }

    /**
     * 点击返回键是否可以取消进度提示
     * @param isCancelable true为可以，false不可以
     */
    public void setCancelable(boolean isCancelable) {
        this.setCancelable(isCancelable);
    }
    /**
     * 显示进度条
     */
    public void showDialog() {
        this.show();
    }

    /**
     * 隐藏进度条
     */
    public void dismissDialog() {
        this.dismiss();
    }

}

package com.mvp.view.pop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.mvp.view.R;

/**
 * Created by Asion on 2018/1/8.
 */

public class CustomPopupWindow extends PopupWindow {
    private View view, v;
    private Context mContext;
    public CustomPopupWindow(Context context, View view) {
        super(context);
        // TODO Auto-generated constructor stub
        this.v = view;
        this.mContext=context;
        init(context);
        setPopupWindow();

    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        view = inflater.inflate(R.layout.pop_orglist, null);

    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(view);// 设置View
        this.setWidth(500);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.AnimationRightFade);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        view.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = view.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        this.showAtLocation(v ,Gravity.RIGHT, 0, 0);
        setBackgroundAlpha(0.5f);
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

}

package com.mvp.view.activity.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;

import com.mvp.util.other.UtilActivityStackManager;
import com.mvp.view.R;
import com.mvp.view.dialog.ProgressDialog;

import org.zackratos.ultimatebar.UltimateBar;

/**
 * Created by Asion on 2018/1/16.
 * Activity 的基类，包括对activity的栈管理，
 * 状态栏/导航栏颜色设置 ，销毁时取消网络请求
 * 子类需要进行ButterKnife绑定。
 */

public class BaseActivity extends AppCompatActivity implements IBaseActivity {
    //加载的弹窗
    private ProgressDialog mProgressDialog;
    //页面或activity的堆栈管理
    private UtilActivityStackManager mStackManager;
    //状态栏导航栏颜色工具类
    private UltimateBar ultimateBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentLayout();//设置内容布局ID
        initBarColor();//初始化状态栏/导航栏颜色，需在设置了布局后再调用
        initView();//由具体的activity实现，做视图相关的初始化
        initData();//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    //将当前的Activity加入到activity堆栈中
    private void init() {
        mStackManager = UtilActivityStackManager.getInstance();
        mStackManager.addActivity(this);
    }

    //设置页面的布局ID
    protected void setContentLayout() {
    }

    /**
     * 实现initView来做视图相关的初始化
     */
    protected void initView() {
    }

    /**
     * 实现initData来做数据的初始化
     */
    protected void initData() {
    }

    /**
     * 实现initEvent来做事件监听的初始化
     */
    protected void initEvent() {
    }


    /**
     * ——————————————————————————————————————————————————————————————————————————————————
     * 状态栏、导航栏背景颜色设置
     **/

    //默认设置状态栏、导航栏都为蓝色
    private void initBarColor() {
        int color = getResourceColor(R.color.title_color);
        setBarColor(color, 0, color, 0);
    }

    public UltimateBar getUltimateBar() {
        if (ultimateBar == null) {
            ultimateBar = new UltimateBar(this);
        }
        return ultimateBar;
    }

    //设置状态栏、导航栏颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setBarColor(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setColorBar(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置状态栏的颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarColor(int color, int alpha) {
        getUltimateBar().setColorStatusBar(color, alpha);
    }

    //设置状态栏、导航栏颜色（有DrawerLayout时可使用这种），第二个参数控制透明度，布局内容不占据状态栏空间
    public void setBarColorForDrawer(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setColorBarForDrawer(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置状态栏的颜色（有DrawerLayout时可使用这种），第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarColorForDrawer(int color, int alpha) {
        getUltimateBar().setColorBarForDrawer(color, alpha);
    }

    //设置半透明的状态栏、导航栏颜色，第二个参数控制透明度，布局内容占据状态栏空间
    public void setBarTranslucent(int statusColor, int statusAlpha, int navColor, int navAlpha) {
        getUltimateBar().setTransparentBar(statusColor, statusAlpha, navColor, navAlpha);
    }

    //单独设置半透明的状态栏颜色，第二个参数控制透明度，布局内容不占据状态栏空间
    public void setStatusBarTranslucent(int color, int alpha) {
        getUltimateBar().setColorBarForDrawer(color, alpha);
    }

    //设置全透明的状态栏、导航栏颜色，布局内容占据状态栏空间，参数为是否也应用到
    public void setTransparentBar(boolean applyNav) {
        getUltimateBar().setImmersionBar(applyNav);
    }

    //隐藏状态栏、导航栏，布局内容占据状态栏导航栏空间，参数为是否也应用到导航栏上
    public void hideBar(boolean applyNav) {
        getUltimateBar().setHideBar(applyNav);
    }
    //——————————————————————————————————————————————————————————————————————————————————
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    @Override
    public String getResourceString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }

//    @Override
//    protected void onPause() {
//        lifecycleSubject.onNext(LifeCycleEvent.PAUSE);
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        lifecycleSubject.onNext(LifeCycleEvent.STOP);
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        //activity销毁时，将当前Activity从栈栈中删除
//        mStackManager.deleteActivity(this);
//        super.onDestroy();
//        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
//    }

    /**
     * 显示圆形进度框
     */
    public void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.showDialog();
    }

    /**
     * 显示圆形进度框（点击返回键不可关闭）
     */
    public void showNoCancelLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.showDialog();
        mProgressDialog.setCancelable(false);
    }

    /**
     * 关闭进度框
     */
    public void dismissLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.dismissDialog();
    }
    OnKeyClickListener mOnKeyClickListener;

    public void setOnKeyListener(OnKeyClickListener onKeyClickListener) {
        this.mOnKeyClickListener = onKeyClickListener;
    }

    /**
     * 按键的监听，供页面设置自定义的按键行为
     */
    public interface OnKeyClickListener {
        /**
         * 点击了返回键
         */
        void clickBack();

        //可加入其它按键事件
    }
}

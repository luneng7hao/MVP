package com.mvp.view.activity.base;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mvp.view.R;
import com.mvp.view.layout.base.LoadLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asion on 2018/1/16.
 * 含有ToolBar、加载布局（正文，加载中，加载失败，无数据）的activity基类
 * 子类不需要再绑定ButterKnife
 * 实现setContentLayout来设置布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现initData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 */

public abstract class ToolbarBaseActivity extends BaseActivity {

    LoadLayout mLoadLayout;//加载布局，可以显示各种状态的布局, 如加载中，加载成功, 加载失败, 无数据

    @BindView(R.id.base_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_toolbar_right)
    TextView mTvToolbarRight;//toolbar右侧的文字控件

    /**
     * 设置所显示的布局
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base_toolbar);

        addViewToContainer(layoutResID);
        init();
    }

    /**
     *  将布局加入到LoadLayout中
     */
    public void addViewToContainer(int layoutResId) {
        mLoadLayout = (LoadLayout) findViewById(R.id.base_content_layout);
        View view = getLayoutInflater().inflate(layoutResId, null);
        mLoadLayout.removeAllViews();
        mLoadLayout.addSuccessView(view);
    }

    /**
     * 初始化
     */
    public void init() {
        ButterKnife.bind(this);//butterknife绑定

        mToolbar.setTitle("");//必须在setSupportActionBar之前将标题置为空字符串，否则具体页面设置标题会无效
        this.setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnKeyClickListener != null) {//如果没有设置返回事件的监听，则默认finish页面。
                    mOnKeyClickListener.clickBack();
                } else {
                    finish();
                }
            }
        });
    }
    /**
     * 设置toolbar右侧文字控件的内容
     */
    public void setToolbarRightTv(String text) {
        if (mTvToolbarRight != null) {
            mTvToolbarRight.setText(text);
        }
    }
    /**
     * 获取toolbar右侧的文字控件
     */
    public TextView getTvToolbarRight() {
        return mTvToolbarRight;
    }
    /**
     * 获取toolbar
     */
    public Toolbar getToolbar() {
        return mToolbar;
    }
    /**
     * 获取加载布局，从而设置各种加载状态
     */
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLoadLayout != null) {
            mLoadLayout.closeAnim();
        }
    }



}

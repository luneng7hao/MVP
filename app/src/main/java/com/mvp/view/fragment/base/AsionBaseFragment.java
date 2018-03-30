package com.mvp.view.fragment.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.view.R;
import com.mvp.view.activity.base.BaseActivity;
import com.mvp.view.layout.base.LoadLayout;

import butterknife.ButterKnife;

/**
 * Created by Asion on 2018/2/5.
 */

public abstract class AsionBaseFragment extends Fragment {
    protected View view = null;
    protected BaseActivity mActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createLayout(inflater);
        initView();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        将这一行注释掉，阻止activity保存fragment的状态,解决Fragment穿透重叠现象
//        super.onSaveInstanceState(outState);
    }

    /**
     * 创建布局控件
     */
    private void createLayout(LayoutInflater iflater) {
        view = iflater.inflate(setContentLayout(), null);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void  initData();

    //设置并返回布局ID
    protected abstract int setContentLayout();

    protected abstract void initView();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }
}

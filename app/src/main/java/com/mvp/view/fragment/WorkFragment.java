package com.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cjj.refresh.BeautifulRefreshLayout;
import com.mvp.view.R;
import com.mvp.view.adapter.MyAdapter;
import com.mvp.view.adapter.item.MyItemDecoration;
import com.mvp.view.fragment.base.AsionBaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Asion on 2018/2/1.
 */

public class WorkFragment extends AsionBaseFragment
{
    @BindView(R.id.work_recycler)
    RecyclerView work_recycler;
    //下拉刷新的组件
    @BindView(R.id.refresh)
    BeautifulRefreshLayout refresh;
    private RecyclerView.LayoutManager mlayoutManager;
    MyAdapter mRecyclerAdapter;
    @Override
    protected int setContentLayout() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
           setRecyclerView();
        refresh.setBuautifulRefreshListener(new BeautifulRefreshLayout.BuautifulRefreshListener() {
            @Override
            public void onRefresh(final BeautifulRefreshLayout refreshLayout) {
//                refreshLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshLayout.finishRefreshing();
//                    }
//                }, 3000);
//
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                });

            }
        });

    }


    @Override
    protected void initData() {

    }
    /**
     * 设置组件RecyclerView
     */
    private void setRecyclerView() {
        mlayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerAdapter = new MyAdapter(getData());

        //设置布局管理器
        work_recycler.setLayoutManager(mlayoutManager);
        // 设置Item之间间隔样式
        work_recycler.addItemDecoration(new MyItemDecoration(mActivity));
        work_recycler.setAdapter(mRecyclerAdapter);
    }
    /**
     * RecyclerView Item 显示的数据
     * @return
     */
    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 12; i++) {
            data.add(i + temp);
        }
        return data;
    }
}

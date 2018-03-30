package com.mvp.view.activity;


import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mvp.view.R;
import com.mvp.view.activity.base.ToolbarBaseActivity;
import com.mvp.view.fragment.FinanceFragment;
import com.mvp.view.fragment.HomeFragment;
import com.mvp.view.fragment.MineFragment;
import com.mvp.view.fragment.WorkFragment;

import butterknife.BindView;

/**
 * Created by Asion on 2018/2/1.
 */

public class FragmentActivity extends ToolbarBaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private HomeFragment homeFragment;//首页
    private MineFragment mineFragment;//我的
    private WorkFragment workFragment;//工作
    private FinanceFragment financeFragment;//财务
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottom_navigation_bar;
    int lastSelectedPosition = 0;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void initView() {
        getToolbar().setTitle("Fragment示例");
//        getToolbar().setVisibility(View.GONE);
        setButtonBar();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 设置底部导航栏样式
     */
    private void setButtonBar() {
        bottom_navigation_bar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */
                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#1ccbae");//导航栏背景色
        /** 添加导航按钮 */
        bottom_navigation_bar
                .addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.finance, "财务"))
                .addItem(new BottomNavigationItem(R.drawable.work, "工作"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                //initialise 一定要放在 所有设置的最后一项
                .initialise();
        //设置默认导航栏
        setDefaultFragment();
    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        Log.i("TAG","setDeafault");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        homeFragment.setUserVisibleHint(true);
        transaction.add(R.id.tb, homeFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        //开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);

        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.tb, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (financeFragment == null) {
                    financeFragment = new FinanceFragment();
                    transaction.add(R.id.tb, financeFragment);
                } else {
                    transaction.show(financeFragment);
                }
                break;
            case 2:
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    transaction.add(R.id.tb, workFragment);
                } else {
                    transaction.show(workFragment);
                }
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.tb, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();// 事务提交
    }

    /**
     * 隐藏fragment的方法
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (financeFragment != null) {
            fragmentTransaction.hide(financeFragment);
        }
        if (workFragment != null) {
            fragmentTransaction.hide(workFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    //上一个被选择的按钮
    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }
}

package com.mvp.view.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mvp.view.R;
import com.mvp.view.banner.GlideImageLoader;
import com.mvp.view.custom.MultiCardMenu;
import com.mvp.view.fragment.base.AsionBaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;



/**
 * Created by Asion on 2018/2/1.
 */

public class HomeFragment extends AsionBaseFragment{
    @BindView(R.id.banner)
    Banner banner;
    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }



    @Override
    protected void initView() {
        setBanner();

    }

    @Override
    protected void initData() {
    }


    /**
     * 加载banner视图
     */
    private void setBanner() {
        //设置图片集合
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.banner01);
        images.add(R.mipmap.banner02);
        images.add(R.mipmap.banner03);
        List<String> titles = new ArrayList<>();
        titles.add("钢铁侠");
        titles.add("地球百子第五季");
        titles.add("荒原第三季开拍！");
        ////设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Default);
//        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
//        //设置轮播时间
//        banner.setDelayTime(1500);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.RIGHT);
//        banner.setViewPagerIsScroll(true);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setBannerAnimation(Transformer.Tablet).
                setDelayTime(2000).start();
    }
}

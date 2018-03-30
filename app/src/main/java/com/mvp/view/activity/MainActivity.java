package com.mvp.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.model.entity.BaseBean;
import com.mvp.model.entity.MovieEntity;
import com.mvp.model.http.Api;
import com.mvp.model.http.RetrofitUtil;
import com.mvp.util.encry.Encryption;
import com.mvp.util.other.UtilGson;
import com.mvp.util.other.UtilString;
import com.mvp.view.R;
import com.mvp.view.banner.GlideImageLoader;
import com.mvp.view.pop.CustomPopupWindow;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.testBtn)
    Button testBtn;
    @BindView(R.id.testPop)
    Button testPop;
    @BindView(R.id.fragment_button)
    Button fragment_button;
    @BindView(R.id.banner)
    Banner banner;
    private CustomPopupWindow mPop;
    private boolean b = true;
    Handler handler;
    Timer timer;

    private final  int msg001=001;
    private final  int msg002=002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        Intent intent=new Intent(this,FragmentActivity.class);
        startActivity(intent);
    }

    private void initView() {
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.banner01);
        images.add(R.mipmap.banner02);
        images.add(R.mipmap.banner03);
        images.add(R.mipmap.banner01);
        List<String> titles = new ArrayList<>();
        titles.add("狗年大吉");
        titles.add("吉祥如意");
        titles.add("狗年会更旺");
        titles.add("狗年会更旺");
        ////设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Tablet);
//        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
//        //设置轮播时间
//        banner.setDelayTime(1500);
//        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
//        banner.setViewPagerIsScroll(true);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setBannerAnimation(Transformer.Tablet).
                setDelayTime(1000).start();
    }

    @OnClick(R.id.testBtn)
    public void test() {
    }
    @OnClick(R.id.fragment_button)
    public void test01() {
        Intent intent=new Intent(this,FragmentActivity.class);
        startActivity(intent);
    }
    private void returnJson() {
        Api api = RetrofitUtil.getApiService();

        Observable<MovieEntity> observable = api.getTopMovie(1, 10);
        observable.subscribeOn(Schedulers.io())
//                .flatMap(new Function<Response<String>, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Response<String> response) throws Exception {
//                        String uuid = response.headers().get("uuid");
//                        String body = response.body();
//                        String result = result = Encryption.dataDencryption(body, uuid);
//                        return Observable.just(result);
//                    }
//                })
                .filter(new Predicate<MovieEntity>() {
                    @Override
                    public boolean test(@NonNull MovieEntity movieEntity) throws Exception {
                        if (movieEntity == null) {
                            System.out.println("未返回数据");
                            return false;
                        } else if (movieEntity != null) {
                            return true;
                        }
                        return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieEntity>() {
                    @Override
                    public void accept(MovieEntity movieEntity) throws Exception {
                        System.out.println(movieEntity.toString());
                        Toast.makeText(MainActivity.this, movieEntity.getSubjects().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //post请求返回String类型的加密串
    private void returnString() {
        Api api = RetrofitUtil.getApiService();
//        Log.i(TAG,"uuid"+RetrofitUtil.uuid);
//        Observable<String> observable = api.getOrgList(requestStr);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String str) throws Exception {
//                        System.out.println(str);
//                    }
//                });
        //1 、  Observable  返回response
//        Observable<Response<String>> observable = api.getResponse("mobile.do?action=getBranch",requestStr);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Response>() {
//                    @Override
//                    public void accept(Response response) throws Exception {
//                        String uuid = response.headers().get("uuid");
//                        Log.i(TAG, "uuid=" + uuid);
//                        Log.i(TAG, "response=" + response.body());
//                    }
//
//                });

        //2、 用call返回response
//        Call<String> observable = api.getOrg(requestStr);
//        observable.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String uuid = response.headers().get("uuid");
//                Log.i(TAG, "uuid=" + uuid);
//                Log.i(TAG, "str=" + call.toString());
//                Log.i(TAG, "response=" + response.body());
//              String data=  Encryption.dataDencryption(response.body(),uuid);
//                Log.i(TAG, "data=" + data);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
        Observable<Response<String>> observable = api.getResponse("", "");
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function<Response<String>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Response<String> response) throws Exception {
                        String uuid = response.headers().get("uuid");
                        String body = response.body();
                        String result = result = Encryption.dataDencryption(body, uuid);
                        return Observable.just(result);
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        if (UtilString.isNull(s)) {
                            System.out.println("未返回数据");
                            return false;
                        }
                        try {
                            BaseBean result = UtilGson.json2bean(s, BaseBean.class);
                            if (result.getResult() == 0) {
                                return true;
                            }
                            return false;
                        } catch (Exception e) {
                            System.out.println("数据解析失败");
                            return false;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        System.out.println(string);
                    }
                });
    }




    @OnClick(R.id.testPop)
    public void testPop(View view) {
        mPop = new CustomPopupWindow(this, findViewById(R.id.activity_main));
//        mPop.showAtLocation(view, Gravity.CENTER,0,0);
    }

    protected void initPopupWindow() {

//        mPop.showAtLocation(findViewById(R.id.activity_main), Gravity.NO_GRAVITY, 0, 200);
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
//do something
            b = false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {

//do something
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            b = false;
//这里操作是没有返回结果的
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.mvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.mvp.model.http.CallBackUtil;
import com.mvp.model.http.OkHttpUtils;
import com.mvp.view.R;
import com.mvp.view.fragment.base.AsionBaseFragment;
import com.mvp.view.fragment.base.BaseFragment;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.type;

/**
 * Created by Asion on 2018/2/1.
 */

public class MineFragment extends AsionBaseFragment {
    @BindView(R.id.okhttp_test_btn)
    Button okhttp_test_btn;
    @BindView(R.id.okhttp_post_json_btn)
    Button okhttp_post_json_btn;
    @BindView(R.id.okhttp_post_keyvalue_btn)
    Button okhttp_post_keyvalue_btn;
    @BindView(R.id.okhttp_post_file_btn)
    Button okhttp_post_file_btn;
    @BindView(R.id.okhttp_post_header_btn)
    Button okhttp_post_header_btn;
    @BindView(R.id.okhttp_post_string_btn)
    Button okhttp_post_string_btn;
    private OkHttpClient mOkHttpClient;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ZLoadingDialog dialog = new ZLoadingDialog(mActivity);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLUE)//颜色
//                .setHintText("Loading...")
//                .setHintTextSize(16) // 设置字体大小 dp
//                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .show();
    }

    /**
     * 按钮的点击事件处理
     */
    @OnClick(R.id.okhttp_test_btn)
    void setOkhttp_test_btn() {
        requestBlog();
    }

    /**
     * 网络请求测试
     */
    private void requestBlog() {
        String url = "http://write.blog.csdn.net/postlist/0/0/enabled/1";
        mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("OkHttp", json);
            }
        });
    }
    /**
     * POST请求提交JSON数据
     */
    @OnClick(R.id.okhttp_post_json_btn)
    void setOkhttp_post_json_btn() {
        String url = "http://write.blog.csdn.net/postlist/0/0/enabled/1";
        String json = "haha";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("OkHttp", response.body().string());
            }
        });
    }
    /**
     * POST请求提交键值对(表单)数据
     */
    @OnClick(R.id.okhttp_post_keyvalue_btn)
    void setOkhttp_post_keyvalue_btn() {
        String url = "http://write.blog.csdn.net/postlist/0/0/enabled/1";

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("name","zhangsan")
                .add("school","beida")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("OkHttp", response.body().string());
            }
        });
    }
    /**
     * POST请求 异步上传文件
     */
    @OnClick(R.id.okhttp_post_file_btn)
    void setOkhttp_post_file_btn() {
        File file = new File("/sdcard/demo.txt");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file))
                .build();
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("OkHttp", response.body().string());
            }
        });
    }
    /**
     * 发送请求头—获取响应头
     * 典型的HTTP头 像是一个Map
     */
    @OnClick(R.id.okhttp_post_header_btn)
    void setOkhttp_post_header_btn() {
        RequestBody formBody = new FormBody.Builder()
                .add("name","zhangsan")
                .add("school","beida")
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), "json"))
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                System.out.println("Server: " + response.header("Server"));
                System.out.println("Date: " + response.header("Date"));
                System.out.println("Vary: " + response.headers("Vary"));
            }
        });
    }
    /**
     * Post方式提交String数据
     * 使用HTTP POST提交请求到服务。这个例子提交了一个markdown文档到web服务，以HTML方式渲染markdown。
     * 因为整个请求体都在内存中，因此避免使用此api提交大文档（大于1MB）。
     *
     */
    @OnClick(R.id.okhttp_post_string_btn)
    void setOkhttp_post_string_btn() {
        String postBody = "1231";
        String url = "http://write.blog.csdn.net/postlist/0/0/enabled/1";
//        Request request = new Request.Builder()
//                .url("https://api.github.com/markdown/raw")
//                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
//                .build();
//        OkHttpClient client = new OkHttpClient();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response.body().string());
//            }
//        });
        OkHttpUtils.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(mActivity, "Success", Toast.LENGTH_SHORT).show();
                Log.d("kwwl", response);
            }
        });
    }
}

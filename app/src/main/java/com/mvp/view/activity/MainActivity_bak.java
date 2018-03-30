package com.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.mvp.view.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity_bak extends AppCompatActivity {
    private final String TAG=this.getClass().getSimpleName();
    private String requestStr = "";
//    @BindView(R.id.test_Post)  TextView test_Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //测试数据
        JsonObject json=new JsonObject();
        json.addProperty("account_id","sup");
        requestStr=json.toString();


    }
//    @OnClick(R.id.test_Post)
    public void testPost(){
       createObserver();
    }
  //创建一个观察者
    private void createObserver() {
        //创建一个被观察者
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                //执行一些其他操作
//                //.............
//                //执行完毕，触发回调，通知观察者
//                e.onNext("我来发射数据");
//            }
//        });
//        //直接触发观察者的onNext方法
//        Observable<String> observable=Observable.just("hello");
        //遍历集合，相当于发送每个item  多次调用观察者的onNext,每次传入一个item
        //注意：Collection接口是Iterable接口的子接口，所以所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()方法。
//        List<String> list=new ArrayList<String>();
//        for(int i=0;i<10;i++){
//            list.add("Hello"+i);
//        }
//        Observable<String> observable=Observable.fromIterable((Iterable<String>) list);
        //创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法。
//        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS);
        //创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。上述表示发射1到20的数。即调用20次nNext()方法，依次传入1-20数字。
//        Observable<Integer> observable = Observable.range(1,20);

        //创建一个Observable，它在一个给定的延迟后发射一个特殊的值，即表示延迟2秒后，调用onNext()方法。
        Observable<Long> observable = Observable.timer(2, TimeUnit.SECONDS);
        observable.subscribe(observer);

    }

    Observer<Long> observer = new Observer<Long>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        //观察者接收到通知,进行相关操作
        public void onNext(Long aLong) {
            Log.i(TAG,aLong+"");
            Log.i(TAG,"我接收到数据了");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    //    private String interface_url = "mobile.do?action=getBranch";
//    private String interface_url = "mobile.do?action=fixCount";
    private String interface_url = "/login.do?action=handleAction";


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        //测试数据  机构查询
////        JsonObject json = new JsonObject();
////        json.addProperty("account_id", "sup");
////        json.addProperty("branch_code", "B");
////        requestStr = json.toString();
//        //报修数量数据
////        JsonObject json = new JsonObject();
////        json.addProperty("account_id", "sup");
////        json.addProperty("manufaid", "B");
////        json.addProperty("roleid", "100029");
//        JsonObject json = new JsonObject();
//        json.addProperty("beanName", "mobileLogin");
//        json.addProperty("username", "sn");
//        json.addProperty("passwd", "123123");
//        requestStr = json.toString();
//
//    }
//
//    @OnClick(R.id.test_Post)
//    public void testPost() {
////        returnString();
//        returnXml();
//    }
//
//    //处理返回的XML数据（中行应用）
//    private void returnXml() {
//        Api api = RetrofitUtil.getApiService();
//        Observable<Response<String>> observable = api.getResponse(interface_url, requestStr);
//        observable.subscribeOn(Schedulers.io())
//                .flatMap(new Function<Response<String>, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Response<String> response) throws Exception {
////                        String uuid = response.headers().get("uuid");
//                        String body = response.body().substring(139);
//
//                        return Observable.just(body);
//                    }
//                })
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                        if(UtilString.isNull(s)){
//                            System.out.println("未返回数据");
//                            return false;
//                        }
//                        try {
//                            BaseBean result = UtilGson.json2bean(s, BaseBean.class);
//                            if (result.getResult() == 0) {
//                                return true;
//                            }
//                            return false;
//                        } catch (Exception e) {
//                            System.out.println("数据解析失败");
//                            return false;
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String string) throws Exception {
//                        System.out.println(string);
//                    }
//                });
//
//    }
//
//    //post请求返回String类型的加密串
//    private void returnString() {
//        Api api = RetrofitUtil.getApiService();
////        Log.i(TAG,"uuid"+RetrofitUtil.uuid);
////        Observable<String> observable = api.getOrgList(requestStr);
////        observable.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Consumer<String>() {
////                    @Override
////                    public void accept(String str) throws Exception {
////                        System.out.println(str);
////                    }
////                });
//        //1 、  Observable  返回response
////        Observable<Response<String>> observable = api.getResponse("mobile.do?action=getBranch",requestStr);
////        observable.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Consumer<Response>() {
////                    @Override
////                    public void accept(Response response) throws Exception {
////                        String uuid = response.headers().get("uuid");
////                        Log.i(TAG, "uuid=" + uuid);
////                        Log.i(TAG, "response=" + response.body());
////                    }
////
////                });
//
//        //2、 用call返回response
////        Call<String> observable = api.getOrg(requestStr);
////        observable.enqueue(new Callback<String>() {
////            @Override
////            public void onResponse(Call<String> call, Response<String> response) {
////                String uuid = response.headers().get("uuid");
////                Log.i(TAG, "uuid=" + uuid);
////                Log.i(TAG, "str=" + call.toString());
////                Log.i(TAG, "response=" + response.body());
////              String data=  Encryption.dataDencryption(response.body(),uuid);
////                Log.i(TAG, "data=" + data);
////            }
////
////            @Override
////            public void onFailure(Call<String> call, Throwable t) {
////
////            }
////        });
//        Observable<Response<String>> observable = api.getResponse(interface_url, requestStr);
//        observable.subscribeOn(Schedulers.io())
//                .flatMap(new Function<Response<String>, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Response<String> response) throws Exception {
//                        String uuid = response.headers().get("uuid");
//                        String body = response.body();
//                        String result=result= Encryption.dataDencryption(body, uuid);
//                        return Observable.just(result);
//                    }
//                })
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                        if(UtilString.isNull(s)){
//                            System.out.println("未返回数据");
//                            return false;
//                        }
//                        try {
//                            BaseBean result = UtilGson.json2bean(s, BaseBean.class);
//                            if (result.getResult() == 0) {
//                                return true;
//                            }
//                            return false;
//                        } catch (Exception e) {
//                            System.out.println("数据解析失败");
//                            return false;
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String string) throws Exception {
//                        System.out.println(string);
//                    }
//                });
//    }

}

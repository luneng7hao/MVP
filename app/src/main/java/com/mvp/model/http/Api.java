package com.mvp.model.http;

import com.mvp.model.entity.MovieEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Asion on 2017/12/14.
 * 定义的接口
 */

public interface Api {
    @GET("citys")
    Observable<String> getAllCity(@Query("key") String key);

    @POST("mobile.do?action=getBranch")
    Observable<String> getOrgList(@Body String string);

    @POST("mobile.do?action=getBranch")
    Call<String> getOrg(@Body String string);

    @POST
    Observable<Response<String>> getResponse(@Url String string1, @Body String string);

        @GET("top250cc")
        Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

}

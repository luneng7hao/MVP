package com.mvp.model.http.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Asion on 2017/12/14.
 *  设置String类型转化库
 */

public class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }
    private StringConverterFactory() {

    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,

                                                            Retrofit retrofit) {
        return new StringResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new StringRequestBodyConverter();
    }

}

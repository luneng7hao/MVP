package com.mvp.model.http.factory;

import com.mvp.model.http.RetrofitUtil;
import com.mvp.util.encry.Encryption;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Asion on 2017/12/14.
 * String类型 响应类
 */

public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {

            return value.string();
        } finally {
            value.close();
        }
    }
}

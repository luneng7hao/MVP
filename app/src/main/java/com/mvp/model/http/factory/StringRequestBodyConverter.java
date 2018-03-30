package com.mvp.model.http.factory;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mvp.app.HttpConstants;
import com.mvp.model.http.RetrofitUtil;
import com.mvp.util.encry.Encryption;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import retrofit2.Converter;

/**
 * Created by Asion on 2017/12/14.
 * String类型请求类
 */

public class StringRequestBodyConverter implements Converter<String, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset charset = Charset.forName(HttpConstants.CHARSET);
    StringRequestBodyConverter() {
    }
    @Override
    public RequestBody convert(String value) throws IOException {
        value=Encryption.dataEncryption(value, RetrofitUtil.uuid);
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), charset);
        writer.write(value);
        writer.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}

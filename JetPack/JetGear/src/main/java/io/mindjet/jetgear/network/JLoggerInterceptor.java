package io.mindjet.jetgear.network;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetutil.logger.JLogger;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Jet on 2/21/17.
 */

public class JLoggerInterceptor implements Interceptor {

    private JLogger jLogger = JLogger.get(getClass().getSimpleName());

    @Override
    public Response intercept(Chain chain) throws IOException {

        //print request base information.
        Request request = chain.request();
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        jLogger.i("--> " + request.method() + " " + request.url() + " " + protocol);

        //print request headers
        Headers headers = request.headers();
        for (int i = 0; i < headers.size(); i++) {
            jLogger.i(headers.name(0) + ": " + headers.value(0));
        }

        //print request body
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            jLogger.i("");
            jLogger.i(buffer.readString(charset));
            jLogger.i("--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
        } else {
            jLogger.i("--> END " + request.method() + " (no request body)");
        }

        //record current time
        long startNs = System.nanoTime();

        //proceed to send request
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            jLogger.e("<-- HTTP FAILED: " + e);
            throw e;
        }

        //record the time it took to request.
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        //print response base information.
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        jLogger.i("<--" + response.code() + " " + response.message() + " " + response.request().url() + " (" + tookMs + "ms) " + bodySize);

        //print useful response header information.
        jLogger.i("Content-Type: " + responseBody.contentType());

        //print response body.
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();

        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }


        jLogger.i("");
        jLogger.i(buffer.clone().readString(charset));
        jLogger.i("<-- END " + request.method() + " (" + buffer.size() + "-byte body)");
        return response;
    }


}

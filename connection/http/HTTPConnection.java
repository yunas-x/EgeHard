package org.example.connection.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Main HTTP methods placed here
 */
public final class HTTPConnection {

    /**
     * Instance of a client
     */
    static OkHttpClient httpClient = new OkHttpClient();

    /**
     * Makes get request
     * @param urlString url for request
     * @return request object
     */
    public static Request makeGetRequest(String urlString) {
        return new Request.Builder().url(urlString).get().build();
    }

    /**
     * Makes post request
     * @param urlString url for request
     * @return request object
     */
    public static Request makePostRequest(String urlString, RequestBody body) {
        return new Request.Builder().url(urlString).post(body).build();
    }

    /**
     * Extracts response body from request
     * @param request request that was made before
     * @return request body
     * @throws IOException on parsing illegal data
     */
    public static ResponseBody getResponseBody(Request request) throws IOException {
        return httpClient.newCall(request).execute().body();
    }

}

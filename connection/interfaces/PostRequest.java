package org.example.connection.interfaces;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import org.example.connection.http.HTTPConnection;

import java.io.IOException;

public interface PostRequest {

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static ResponseBody post(String URL, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = HTTPConnection.makePostRequest(URL, body);
        return HTTPConnection.getResponseBody(request);
    }

}

package org.example.connection.interfaces;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import org.example.connection.http.HTTPConnection;

import java.io.IOException;

public interface GetResponseBody {

     default ResponseBody getResponseBody(String URL) throws IOException {
        Request request = HTTPConnection.makeGetRequest(URL);
        return HTTPConnection.getResponseBody(request);
    }

}

package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpStatusChecker {
    private static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code) throws IOException {
        String url = BASE_URL + code + ".jpg";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return url;
            } else {
                throw new IOException("HTTP.cat returned a non-successful status code: " + response.code());
            }
        }
    }
}

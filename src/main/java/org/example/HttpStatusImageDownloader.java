package org.example;

import java.io.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpStatusImageDownloader {
    private HttpStatusChecker checker;
    private final int bufferSize = 8192;

    public HttpStatusImageDownloader() {
        checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws IOException {
        try {
            String imageUrl = checker.getStatusImage(code);
            String fileName = code + ".jpg";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(imageUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                try (InputStream is = response.body().byteStream();
                     OutputStream os = new FileOutputStream(fileName)) {
                    byte[] buffer = new byte[bufferSize];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Image downloaded and saved as " + fileName);
                }
            } else {
                throw new IOException("HTTP.cat returned a non-successful status code for image download: " + response.code());
            }

        } catch (IOException e) {
            throw new IOException("Error while downloading image: " + e.getMessage());
        }
    }
}

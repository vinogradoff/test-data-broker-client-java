package io.github.vinogradoff.testdatabroker.client;

import okhttp3.*;

import java.io.*;

public class TestDataBrokerClientImpl implements TestDataBrokerClient {

    OkHttpClient client;

    public TestDataBrokerClientImpl() {
        client = new OkHttpClient();
    }


    public void writeData(String dictionary, String key, String value) throws IOException {
        RequestBody body = RequestBody.create(new byte[]{});
        Request req = new Request.Builder()
                .url("http://localhost:8080/api/write/" + dictionary + "/" + key + "?value=" + value)
                .post(body)
                .build();

        client.newCall(req).execute();
    }

    public String readData(String dictionary, String key) throws IOException {
        Request req = new Request.Builder()
                .url("http://localhost:8080/api/read/" + dictionary + "/" + key)
                .build();

        return client.newCall(req).execute().body().string();

    }

    public String claimData(String dictionary, String key) throws IOException {
        RequestBody body = RequestBody.create(new byte[]{});
        Request req = new Request.Builder()
                .url("http://localhost:8080/api/claim/" + dictionary + "/" + key)
                .put(body)
                .build();

        return client.newCall(req).execute().body().string();
    }
}

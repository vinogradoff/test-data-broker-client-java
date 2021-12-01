package io.github.vinogradoff.testdatabroker.client;

import okhttp3.*;

import java.io.*;

public class DefaultTestDataBrokerClient implements TestDataBrokerClient {

    OkHttpClient client;
    String baseUrl;

    /**
     * initialized with http://localhost:8080
     */
    public DefaultTestDataBrokerClient() {
        client = new OkHttpClient();
        baseUrl = "http://localhost:8080";
    }

    /***
     *
     * @param baseUrl base url for Test Data Broker installation
     */
    public DefaultTestDataBrokerClient(String baseUrl) {
        client = new OkHttpClient();
        this.baseUrl = baseUrl;
    }

    public void writeData(String dictionary, String key, String value) throws IOException {
        RequestBody body = RequestBody.create(new byte[]{});
        Request req = new Request.Builder()
                .url(baseUrl + "/api/write/" + dictionary + "/" + key + "?value=" + value)
                .post(body)
                .build();

        client.newCall(req).execute();
    }

    public String readData(String dictionary, String key) throws IOException {
        Request req = new Request.Builder()
                .url(baseUrl + "/api/read/" + dictionary + "/" + key)
                .build();

        return client.newCall(req).execute().body().string();

    }

    public String claimData(String dictionary, String key) throws IOException {
        RequestBody body = RequestBody.create(new byte[]{});
        Request req = new Request.Builder()
                .url(baseUrl + "/api/claim/" + dictionary + "/" + key)
                .put(body)
                .build();

        return client.newCall(req).execute().body().string();
    }
}

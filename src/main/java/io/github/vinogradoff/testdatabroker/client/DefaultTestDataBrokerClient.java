package io.github.vinogradoff.testdatabroker.client;

import io.github.vinogradoff.testdatabroker.exception.*;
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
        // use deprecated version to ensure compatibility with Selenide 5 and Maven (which us old okhttp 3.14)
        RequestBody body = RequestBody.create(null, new byte[]{});
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
        Response resp = client.newCall(req).execute();
        if (resp.code() != 200 && resp.code() != 204) {
            throw new NotFoundException("Code:" + resp.code() + " Message:" + resp.body().string());
        }
        return resp.body().string();

    }

    public String claimData(String dictionary, String key) throws IOException {
        // use deprecated version to ensure compatibility with Selenide 5 and Maven (which us old okhttp 3.14)
        RequestBody body = RequestBody.create(null, new byte[]{});
        Request req = new Request.Builder()
                .url(baseUrl + "/api/claim/" + dictionary + "/" + key)
                .put(body)
                .build();

        Response resp = client.newCall(req).execute();
        if (resp.code() != 200 && resp.code() != 204) {
            throw new NotFoundException("Code:" + resp.code() + " Message:" + resp.body().string());
        }
        return resp.body().string();
    }
}

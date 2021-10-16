package de.vinogradoff.testdatabroker.client;

import java.io.*;

public interface TestDataBrokerClient {

    void writeData(String dictionary, String key, String value) throws IOException;

    String readData(String dictionary, String key) throws IOException;

    String claimData(String dictionary, String key) throws IOException;
}

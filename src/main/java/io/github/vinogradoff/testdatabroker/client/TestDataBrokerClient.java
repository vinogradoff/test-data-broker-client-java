package io.github.vinogradoff.testdatabroker.client;

import java.io.*;

public interface TestDataBrokerClient {

    /***
     * Adds a new test data
     * @param dictionary - for grouping keys
     * @param key
     * @param value
     * @throws IOException
     */
    void writeData(String dictionary, String key, String value) throws IOException;

    /***
     * Reads a test data for given key, without deleting it
     * @param dictionary
     * @param key
     * @return
     * @throws IOException
     */
    String readData(String dictionary, String key) throws IOException;

    /***
     * Reads a test data for given key and deletes it
     * @param dictionary
     * @param key
     * @return
     * @throws IOException
     */
    String claimData(String dictionary, String key) throws IOException;
}

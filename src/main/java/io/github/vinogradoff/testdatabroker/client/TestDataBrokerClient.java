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
     * @throws IOException, NotFoundException if key or dictionary don't exist
     */
    String readData(String dictionary, String key) throws IOException;

    /***
     * Reads a test data for given key and deletes it
     * @param dictionary
     * @param key
     * @return
     * @throws IOException, NotFoundException if key or dictionary don't exist
     */
    String claimData(String dictionary, String key) throws IOException;

    /***
     * Clears all values for given key in the dictionary.
     * @param dictionary
     * @param key
     * @throws IOException
     */
    void clearData(String dictionary, String key) throws IOException;

    /***
     * deletes key from the dictionary
     * @param dictionary
     * @param key
     * @throws IOException
     */
    void deleteKey(String dictionary, String key) throws IOException;

    /***
     * deletes dictionary with all its keys
     * @param dictionary
     * @throws IOException
     */
    void deleteDictionary(String dictionary) throws IOException;
}

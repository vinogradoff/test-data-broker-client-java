package io.github.vinogradoff.testdatabroker.client;

import io.github.vinogradoff.testdatabroker.exception.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

public class ClientCleanUpTest {

    TestDataBrokerClient client = new DefaultTestDataBrokerClient();

    @Test
    void shouldClearUpData() throws IOException {
        String key = "key1" + System.currentTimeMillis();
        client.writeData("dict", key, "value1");
        client.clearData("dict", key);
        assertThat(client.readData("dict", key)).isEqualTo("");
    }

    @Test
    void shouldDeleteKey() throws IOException {
        String key = "key1" + System.currentTimeMillis();
        client.writeData("dict", key, "value1");
        client.deleteKey("dict", key);
        assertThatThrownBy(() -> client.readData("dict", key))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Key");
    }

    @Test
    void shouldDeleteDictionary() throws IOException {
        String dict = "dict1" + System.currentTimeMillis();
        client.writeData(dict, "key123", "value1");
        client.deleteDictionary(dict);
        assertThatThrownBy(() -> client.readData(dict, "key123"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Dictionary");
    }
}

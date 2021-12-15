package io.github.vinogradoff.testdatabroker.client;


import io.github.vinogradoff.testdatabroker.exception.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

public class TestDataBrokerClientTest {

    TestDataBrokerClient client = new DefaultTestDataBrokerClient();

    @Test
    void shouldWriteDataInRepo() throws IOException {
        String key = "key1" + System.currentTimeMillis();
        client.writeData("dict", key, "value1");
        assertThat(client.readData("dict", key)).isEqualTo("value1");
    }

    @Test
    void shouldReadDataFromRepoMultipleTimes() throws IOException {
        String key = "key2" + System.currentTimeMillis();
        client.writeData("dict", key, "value2");
        assertThat(client.readData("dict", key)).isEqualTo("value2");
        assertThat(client.readData("dict", key)).isEqualTo("value2");
    }

    @Test
    void shouldClaimDataFromRepoJustOnce() throws IOException {
        String key = "key3" + System.currentTimeMillis();
        client.writeData("dict", key, "value3");
        assertThat(client.claimData("dict", key)).isEqualTo("value3");
        assertThat(client.readData("dict", key)).isEqualTo("");
    }

    @Test
    void shouldThrowExceptionIfDictionaryNotExist() {
        assertThatThrownBy(() -> client.readData("dict123", "key"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Dictionary");

        assertThatThrownBy(() -> client.claimData("dict123", "key"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Dictionary");
    }

    @Test
    void shouldThrowExceptionIfKeyNotExist() throws IOException {
        String key = "key4" + System.currentTimeMillis();
        client.writeData("dict", key, "value3");
        assertThatThrownBy(() -> client.readData("dict", "key123"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Key");

        assertThatThrownBy(() -> client.claimData("dict", "key123"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContainingAll("Key");
    }

}


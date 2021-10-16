package de.vinogradoff.testdatabroker.client;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.io.*;

public class TestDataBrokerClientTest {

    TestDataBrokerClient client = new TestDataBrokerClientImpl();

    @Test
    void shouldWriteDataInRepo() throws IOException {
        client.writeData("dict", "key1", "value1");
        Assertions.assertThat(client.readData("dict", "key1")).isEqualTo("value1");
    }

    @Test
    void shouldReadDataFromRepoMultipleTimes() throws IOException {
        client.writeData("dict", "key2", "value2");
        Assertions.assertThat(client.readData("dict", "key2")).isEqualTo("value2");
        Assertions.assertThat(client.readData("dict", "key2")).isEqualTo("value2");
    }

    @Test
    void shouldClaimDataFromRepoJustOnce() throws IOException {
        client.writeData("dict", "key3", "value3");
        Assertions.assertThat(client.claimData("dict", "key3")).isEqualTo("value3");
        Assertions.assertThat(client.readData("dict", "key3")).isEqualTo("");
    }
}


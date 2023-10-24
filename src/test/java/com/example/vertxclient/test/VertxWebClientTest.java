package com.example.vertxclient.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertxWebClientTest {
    private VertxWebClient vertxWebClient;

    @BeforeEach
    void setUp() {
        vertxWebClient = new VertxWebClient();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendGet() {
        vertxWebClient.sendGet();
    }

    @Test
    void senAbs() {
        vertxWebClient.sendAbs();
    }
}
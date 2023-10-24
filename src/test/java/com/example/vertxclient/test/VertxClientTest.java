package com.example.vertxclient.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertxClientTest {

    private VertxClient vertxClient;

    @BeforeEach
    void setUp() {
        vertxClient = new VertxClient();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSend() {
        vertxClient.getSend();
    }
}
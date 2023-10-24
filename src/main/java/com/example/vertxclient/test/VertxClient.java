package com.example.vertxclient.test;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class VertxClient {

    private static final Vertx VERTX = Vertx.vertx(new VertxOptions().setWorkerPoolSize(25));
    private HttpClient httpClient;

    public VertxClient() {
        HttpClientOptions clientOptions = clientOptions();
        httpClient = VERTX.createHttpClient(clientOptions);
    }

    private static HttpClientOptions clientOptions() {
        String host = "https://mvnrepository.com";
        int port = 443;

        return new HttpClientOptions()
                .setDefaultHost(host)
                .setDefaultPort(port)
                .setProtocolVersion(HttpVersion.HTTP_2)
                .setHttp2MaxPoolSize(50)
                .setHttp2MultiplexingLimit(1000)
                .setHttp2KeepAliveTimeout((int) Duration.ofMinutes(60).toSeconds())
                .setUseAlpn(true);
    }

    public void getSend() {

        String path = "/artifact/io.vertx/vertx-core/4.3.8";

        httpClient.request(HttpMethod.GET, path)
                .onSuccess(httpRequest -> {
                    httpRequest.send()
                            .onSuccess(response -> handleResponse(response.statusCode(), response.body()))
                            .onFailure(exception -> handleSendException(httpRequest, exception));
                })
                .onFailure(exception -> handleRequestException(exception));

        //release it
        path = null;
    }

    private void handleRequestException(Throwable exception) {
        System.out.println("Failed request" + exception);
    }

    private void handleSendException(HttpClientRequest httpRequest, Throwable exception) {
        System.out.println("Failed send" + exception);
    }

    private void handleResponse(int status, Future<Buffer> body) {
        System.out.println(status);
        final Buffer result = body.result();

    }
}

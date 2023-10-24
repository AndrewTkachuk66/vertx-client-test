package com.example.vertxclient.test;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpVersion;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class VertxWebClient {
    private WebClient client;
    private final Vertx vertx = Vertx.vertx(new VertxOptions());

    public VertxWebClient() {

        WebClientOptions options = new WebClientOptions()
                .setDefaultHost("www.google.com")
                .setDefaultPort(443)
                .setProtocolVersion(HttpVersion.HTTP_2)
                .setSsl(true)
                .setUseAlpn(true)
                .setUserAgent("My-App/1.2.3");
        options.setKeepAlive(false);
        client = WebClient.create(vertx, options);
    }

    public void sendGet() {
        final HttpResponse<Buffer> result = client
                .get("/doodles")
                .send()
                .onSuccess(res -> {
                            System.out.println("Received response with status code" + res.statusCode());
                            final String body = res.bodyAsString();
                        }
                ).onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()))
                .result();
    }

    public void sendAbs() {
        final boolean succeeded = client
                .getAbs("https://www.google.com:443/doodles")
                .send()
                .onSuccess(res ->
                        System.out.println("Received response with status code" + res.statusCode()))
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()))
                .succeeded();
        System.out.println(succeeded);
    }
}

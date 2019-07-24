package com.github.taven.core.future;

import io.vertx.core.Vertx;

public class FutureExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer().requestHandler(req -> {
            // 未使用Future
            vertx.executeBlocking(blockFuture -> {
                // balabala

            }, as -> {
                // balabala
            });

            // 使用Future

        });
    }
}

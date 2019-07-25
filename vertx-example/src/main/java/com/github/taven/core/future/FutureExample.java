package com.github.taven.core.future;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

/**
 * 使用Future避免回调地狱
 */
public class FutureExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer().requestHandler(req -> {
            // 未使用Future
//            unFuture(vertx, req);

            // 使用Future
            future(vertx, req);

        }).listen(8080);
    }

    private static void future(Vertx vertx, HttpServerRequest req) {
        Future<Object> future = Future.future();
        future.setHandler(as -> {
            System.out.println(as.result());
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end(as.result() + "," + as.succeeded());
        });

        vertx.executeBlocking((Future<Object> blockFuture) -> {
            // balabala
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockFuture.complete("block result");

        }, future);
    }

    private static void unFuture(Vertx vertx, HttpServerRequest req) {
        vertx.executeBlocking((Future<Object> blockFuture) -> {
            // balabala
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockFuture.complete("block result");

        }, as -> {
            // balabala
            System.out.println(as.result());
            System.out.println(as.succeeded());
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end(as.result() + "," + as.succeeded());
        });
    }
}

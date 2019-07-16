package com.github.taven.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class BlockOperation {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().blockingHandler(routingContext -> {
            String path = routingContext.request().path() + routingContext.request().getParam("uid");
            System.out.println("处理请求:" + path + ", time:" + System.currentTimeMillis());
            doSomethings();

            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.end("Hello World from Vert.x-Web!");
            System.out.println("请求结束:" + path + ", time:" + System.currentTimeMillis());
        }, false); // 第二个参数默认为true, 设置为false时，代码块中的代码会并发执行

        server.requestHandler(router::accept).listen(8080);
    }

    public static void doSomethings() {
        double x = 2;
        for (int i = 0; i < 1000000; i++) {
            x = Math.pow(x, 100);
        }
    }
}

package com.github.taven.web;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebClient4Test {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        System.out.println("执行多线程测试");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("Thread:" + Thread.currentThread().getName() + ", time:" + System.currentTimeMillis());
                    WebClient client = WebClient.create(vertx);
                    // 发送GET请求
                    client
                        .get(8080, "localhost", "/some-uri?uid="+UUID.randomUUID())
                        .send(ar -> {
                            if (ar.succeeded()) {
                                // 获取响应
                                HttpResponse<Buffer> response = ar.result();
                                System.out.println("Thread:" + Thread.currentThread().getName() + response.bodyAsString());
                            } else {
                                System.out.println("Something went wrong " + ar.cause().getMessage());
                            }
                        });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.countDown();
    }
}

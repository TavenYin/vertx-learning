package com.github.taven.core.future;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

public class ComposeExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer().requestHandler(req -> {
            FileSystem fs = vertx.fileSystem();
            Future<Void> startFuture = Future.future();

            Future<Void> fut1 = Future.future();
            fs.createFile("/foo", fut1.completer());

            fut1.compose(v -> {
                // fut1中文件创建完成后执行
                Future<Void> fut2 = Future.future();
                fs.writeFile("/foo", Buffer.buffer(), fut2.completer());
                return fut2;
            }).compose(v -> {
                // fut2文件写入完成后执行
                fs.move("/foo", "/bar", startFuture.completer());
            },
            // 如果任何一步失败，将startFuture标记成failed
            startFuture).setHandler(event -> {
                req.response()
                        .putHeader("content-type", "text/plain")
                        .end("future" + startFuture.succeeded());
            });

        }).listen(8080);
    }
}

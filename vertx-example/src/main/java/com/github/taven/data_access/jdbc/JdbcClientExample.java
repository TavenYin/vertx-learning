package com.github.taven.data_access.jdbc;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;

/**
 * vert.x handle中jdbc操作不会阻塞
 */
public class JdbcClientExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // jdbc
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/test")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("username", "root")
                .put("password", "root");
        JDBCClient client = JDBCClient.createShared(vertx, config);

        router.route().handler(routingContext -> {
            client.getConnection(res -> {
                if (res.succeeded()) {
                    SQLConnection connection = res.result();
                    connection.query("select SLEEP(5)", res2 -> {
                        if (res2.succeeded()) {
                            ResultSet rs = res2.result();
                            // 用结果集results进行其他操作
                            HttpServerResponse response = routingContext.response();
                            response.putHeader("content-type", "text/plain");
                            response.end("vert.x jdbc client");
                        }
                    });
                } else {
                    // 获取连接失败 - 处理失败的情况
                }
            });

        });

        server.requestHandler(router::accept).listen(8080);
    }
}

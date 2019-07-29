package com.github.taven.data_access.mysql_postgres;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;

public class MySQLPostgresExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        JsonObject mysqlConfig = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/test")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("username", "root")
                .put("password", "root");
        AsyncSQLClient mysqlClient = MySQLClient.createShared(vertx, mysqlConfig);
        mysqlClient.getConnection(scas -> {
            if (scas.succeeded()) {
                SQLConnection sqlConnection = scas.result();

            }
        });
    }
}

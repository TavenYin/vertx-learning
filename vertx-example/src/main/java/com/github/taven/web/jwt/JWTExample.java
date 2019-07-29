package com.github.taven.web.jwt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class JWTExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyVerticle.class.getName());
    }

    public static class MyVerticle extends AbstractVerticle {
        public void start() {
            HttpServer server = vertx.createHttpServer();
            Router router = Router.router(vertx);

            JWTAuthOptions authConfig = new JWTAuthOptions()
                    .addPubSecKey(new PubSecKeyOptions()
                            .setAlgorithm("HS256")
                            .setPublicKey("keyboard cat")
                            .setSymmetric(true));
            authConfig.setJWTOptions(new JWTOptions().setExpiresInMinutes(30 * 24 * 60));
            JWTAuth authProvider = JWTAuth.create(vertx, authConfig);
            router.route("api/*").handler(JWTAuthHandler.create(authProvider));

            router.get("api/111").handler(routingContext -> {
                routingContext.response()
                        .putHeader("content-type", "application/json")
                        .end("hello jwt");
            });

            router.post("login").handler(routingContext -> {

            });

            server.requestHandler(router).listen(8080);
        }
    }
}

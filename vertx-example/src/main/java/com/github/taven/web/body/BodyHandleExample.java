package com.github.taven.web.body;

import com.github.taven.utils.Assert;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class BodyHandleExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyVerticle.class.getName());
    }

    public static class MyVerticle extends AbstractVerticle {
        public void start() {
            HttpServer server = vertx.createHttpServer();
            Router router = Router.router(vertx);

            router.route()
                .handler(BodyHandler.create());
            router.post("/user/add")
                .consumes("application/json")
                .produces("application/json")
                .handler(routingContext -> {
                    System.out.println(Thread.currentThread());
                    JsonObject jsonObject = routingContext.getBodyAsJson();
                    Assert.notNull(jsonObject, "not null");
                    User user = jsonObject.mapTo(User.class);

                    routingContext.response()
                            .putHeader("content-type", "application/json")
                            .end(Json.encode(user));

                });

            server.requestHandler(router).listen(8080);
        }
    }

    public static class User {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

package com.github.taven.utils;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class VertxUtils {
    public static void ok(RoutingContext routingContext, Object result) {
        ok(routingContext, Json.encode(result));
    }

    public static void ok(RoutingContext routingContext, String result) {
        routingContext.response()
                .putHeader("content-type", "application/json")
                .end(result);
    }
}

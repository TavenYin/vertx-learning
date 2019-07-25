package com.github.taven.core.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(EventBusVerticle.class.getName(), asyncResult -> {
            EventBus eventBus = vertx.eventBus();
            eventBus.send("news.uk.sport", "Yay! Someone kicked a ball");
        });

    }
}

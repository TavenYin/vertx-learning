package com.github.taven.core.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class EventBusVerticle extends AbstractVerticle {
    public void start() {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });
    }
}

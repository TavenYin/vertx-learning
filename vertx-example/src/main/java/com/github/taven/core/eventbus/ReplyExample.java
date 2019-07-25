package com.github.taven.core.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

public class ReplyExample {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(EventBusVerticle.class.getName(), asyncResult -> {
            EventBus eventBus = vertx.eventBus();
            Future<Message<String>> future = Future.future();
            future.setHandler(ar -> {
                System.out.println("Received reply: " + ar.result().body());
            });
            eventBus.send("news.uk.sport", "Yay! Someone kicked a ball", future);
        });
    }

    public static class EventBusVerticle extends AbstractVerticle {
        public void start() {
            EventBus eventBus = vertx.eventBus();
            MessageConsumer<String> consumer = eventBus.consumer("news.uk.sport");
            consumer.handler(message -> {
                System.out.println("I have received a message: " + message.body());
                message.reply("how interesting!");
            });
        }
    }
}

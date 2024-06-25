package org.dacd.luis.eventstore.application;

public interface Subscriber {
    void start();
    void subscribe(String topicName);
    void close();
}

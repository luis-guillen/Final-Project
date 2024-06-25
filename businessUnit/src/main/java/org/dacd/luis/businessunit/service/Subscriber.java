package org.dacd.luis.businessunit.service;

public interface Subscriber {
    void start();
    void subscribe(String topicName);
    void close();
}

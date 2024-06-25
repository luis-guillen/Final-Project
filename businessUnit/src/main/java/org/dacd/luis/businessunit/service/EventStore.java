package org.dacd.luis.businessunit.service;

public interface EventStore {
    void storeEvent(String json, String topicName);
}

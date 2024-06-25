package org.dacd.luis.eventstore.application;

public interface EventStore {
    void storeEventToFile(String json, String topicName);
}


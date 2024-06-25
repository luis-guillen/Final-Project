package org.dacd.luis.sensorprovider.application;

import org.dacd.luis.sensorprovider.model.Hotel;

public interface HotelPublisher {
    void start();
    void publish (Hotel event);
    void close();
}

package org.dacd.luis.predictionprovider.application;

import org.dacd.luis.predictionprovider.model.Weather;

public interface Publisher {
    void start();
    void publish (Weather event);
    void close();
}

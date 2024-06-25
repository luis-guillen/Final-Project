package org.dacd.luis.sensorprovider;

import org.dacd.luis.sensorprovider.application.HotelPeriodicTask;
import org.dacd.luis.sensorprovider.application.HotelSupplier;
import org.dacd.luis.sensorprovider.application.XoteloProvider;
import org.dacd.luis.sensorprovider.model.Hotel;

import java.util.ArrayList;
import java.util.Timer;

public class Main {

    public static void main(String[] args){
        ArrayList<Hotel> hotels = HotelSupplier.canaryIslandsHotels();
        XoteloProvider xoteloProvider = new XoteloProvider();
        Timer timer = new Timer();
        HotelPeriodicTask updaterTask = new HotelPeriodicTask(xoteloProvider, hotels);
        timer.scheduleAtFixedRate(updaterTask, 0, 6 * 60 * 60 * 1000);
    }
}

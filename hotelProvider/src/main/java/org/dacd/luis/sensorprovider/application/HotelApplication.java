package org.dacd.luis.sensorprovider.application;

import org.dacd.luis.sensorprovider.model.Hotel;
import org.dacd.luis.sensorprovider.model.Rate;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelApplication {
    private final HotelProvider xoteloProvider;
    private final ArrayList<Hotel> hotels;
    private final HotelPublisher publisher;

    public HotelApplication(HotelProvider hotelProvider, ArrayList<Hotel> hotels, HotelPublisher publisher) {
        this.xoteloProvider = hotelProvider;
        this.hotels = hotels;
        this.publisher = publisher;
    }
    public void execute() {
        LocalDate currentDate = LocalDate.now();
        for (Hotel hotel : hotels) {
            List<Rate> hotelRates = xoteloProvider.getHotelRates(hotel.getHotelKey(),currentDate);
            Instant instant = Instant.now();
            Hotel event = new Hotel(instant, "hotel-provider",hotel.getName(),hotel.getHotelKey(),
                    hotel.getLocation(), hotelRates, currentDate.toString(),currentDate.plusDays(5).toString());
            publisher.publish(event);
        }
    }
}

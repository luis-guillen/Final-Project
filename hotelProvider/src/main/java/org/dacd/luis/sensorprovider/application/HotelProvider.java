package org.dacd.luis.sensorprovider.application;

import org.dacd.luis.sensorprovider.model.Rate;
import java.time.LocalDate;
import java.util.List;

public interface HotelProvider {
    List<Rate> getHotelRates(String hotelKey, LocalDate currentDate );
}

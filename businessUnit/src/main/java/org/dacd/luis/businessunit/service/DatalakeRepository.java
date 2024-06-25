package org.dacd.luis.businessunit.service;

import org.dacd.luis.businessunit.model.Hotel;

import java.io.IOException;
import java.util.List;

public interface DatalakeRepository {
    List<Hotel> getHotelHistoricalData() throws IOException;
}

package org.dacd.luis.businessunit.service;

import org.dacd.luis.businessunit.model.Hotel;

import java.util.List;

public interface HotelRepository {

    void saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    List<Hotel> getHotelByLocation(String location);
    List<Hotel> getHotelByDate(String location, String checkIn, String checkOut);
}

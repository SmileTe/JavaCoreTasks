package com.homework;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static List<Integer> hotels;

    public Hotel() {
        hotels = new ArrayList<>();
    }

    public static void bookRoom(int roomNumber) {
        if (hotels.contains(roomNumber)) {
            throw new RoomNotAvailableException("комната занята");
        }
        hotels.add(roomNumber);
    }
}

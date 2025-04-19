package com.homework;

public class RoomNotAvailableException extends RuntimeException{
    public RoomNotAvailableException() {
        super();
    }
    public RoomNotAvailableException(String message) {
        super(message);
    }
}

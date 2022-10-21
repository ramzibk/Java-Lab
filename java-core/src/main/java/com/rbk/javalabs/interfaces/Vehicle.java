package com.rbk.javalabs.interfaces;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface Vehicle {

    void start();
    void accelerate(int rate);
    void stop();
    void turn(int radius);

    // default methods enable you to add new functionality to the interfaces of your libraries
    // and ensure binary compatibility with code written for older versions of those interfaces.
    default void fly() {
        System.out.println("Hurra! I can fligh!");
        // you can override this method to define your own logic, but you don't have to
    }

    // using interface static methods is like defining static helper methods in a separate class
    static String geZonedDatetTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId).toString();
    }
}

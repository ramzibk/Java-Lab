package com.rbk.javalabs.interfaces;

import java.time.ZoneId;

public class Train implements Vehicle{

    @Override
    public void start() {
        System.out.println("Tut tut tut");
    }

    @Override
    public void accelerate(int rate) {
        System.out.println("Tut tut tuuuuut");
    }

    @Override
    public void stop() {
        System.out.println("ladies and gentlemen, it is "+ Vehicle.geZonedDatetTime(ZoneId.of("Europe/Berlin")));
        System.out.println("We arrived at the end station, please step out of the train carefully");
    }

    @Override
    public void turn(int radius) {
        System.out.println("Zzzzzzzz");
    }

    // default methods can be overriden
    @Override
    public void fly() {
        System.out.println("Sorry, I can't fly, I'm a train not a Batmobile!");
    }
}

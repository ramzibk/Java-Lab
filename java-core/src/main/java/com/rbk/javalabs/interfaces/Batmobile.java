package com.rbk.javalabs.interfaces;

import java.time.ZoneId;

public class Batmobile implements Vehicle {

    int speed;
    int direction;

    @Override
    public void start() {
        System.out.println("Wrum Wrum Wrum");
        speed = 0;
    }

    @Override
    public void accelerate(int rate) {
        System.out.println("Wrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        speed = speed+rate;
        System.out.println(String.format("speed: %d", speed));
    }

    @Override
    public void stop() {
        System.out.println("Zzzzzzzzz");
        while(speed > 1) {
            System.out.print("<");
            speed /= 2;
        }
        speed = 0;
        System.out.println(String.format("speed: %d", speed));
    }

    @Override
    public void turn(int radius) {
        direction = (direction+radius)%360;
        System.out.println("We're in Gotham city, the jocker is on the visier");
    }

    @Override
    public void fly() {
        System.out.println("Owww, Seat belt on, it's "+Vehicle.geZonedDatetTime(ZoneId.of("Europe/Berlin"))+" and we're fliyhing to Gotham city");
    }
}

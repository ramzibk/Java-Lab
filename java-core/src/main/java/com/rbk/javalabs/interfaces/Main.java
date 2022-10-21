package com.rbk.javalabs.interfaces;

import java.time.ZoneId;

public class Main {

    public static void main(String[] args) {
        Vehicle shadow = new Batmobile();
        shadow.start();
        shadow.start();
        shadow.accelerate(20);
        shadow.turn(35);
        shadow.stop();
        shadow.fly();
        Vehicle.geZonedDatetTime(ZoneId.of("Europe/Berlin"));

        Vehicle train = new Train();
        train.start();
        train.accelerate(30);
        train.fly();
        train.stop();
    }
}

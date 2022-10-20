package com.rbk.javalabs.java9.exceptionhandling;

import java.io.Closeable;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        CoffeeShop coffeeShop = new CoffeeShop(10);

        try {
            coffeeShop.receiveCustomers(3); // throws an exception, CoffeeShop was not opened yet
        } catch (Exception e) {
            e.printStackTrace();
        }

/**
 * Try, catch, finally
 */
        try {
            coffeeShop.open();
            coffeeShop.serveCoffee(12); // throws an exception, stock is not enough
        } catch (Exception e) {
            e.printStackTrace();
            coffeeShop.addStock(2);
        } finally {
            coffeeShop.serveCoffee(12);
            coffeeShop.close();
        }

/**
 * suppressed Exceptions
 */
        try {
            coffeeShop.open();
            try {
                coffeeShop.receiveCustomers(3);
                coffeeShop.serveCoffee(12); // this exception will be suppressed by an exception thrown in the finally block
            } finally {
                coffeeShop.close(); // throws an exception, because customer > 0
            }
        } catch (Exception e) {
            e.printStackTrace();
            coffeeShop.leaveHouse(coffeeShop.customers);
            coffeeShop.close();
        }

/**
 * Try with resources, auto closeable
 */
        String hisName = "";
        try (CoffeeShop closeableCS = new CoffeeShop( "Dear One", 10, true)) {
            hisName = closeableCS.name;
            closeableCS.serveCoffee(20); // throws an exception, stock is not enough
            // the coffeeShop will be closed automatically at this point, catch an finally blocks will run after that
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("rest in peace %s coffeeShop :(", hisName));
        }

/**
 *
 */
        CoffeeShop finalCS = new CoffeeShop("Final Coffee", 10, true);
        // final CoffeeShop finalCS = new CoffeeShop("a Final Coffee", 10, true);
        try (finalCS) {} // if you have a final or effectively final resource you can use it directly
    }
}

class CoffeeShop implements Closeable {
    String name = "Anonymous";
    int stock;
    int customers;
    boolean opened = false;

    public CoffeeShop(int stock) {
        this.stock = stock;
    }

    public CoffeeShop(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public CoffeeShop(String name, int stock, boolean open) {
        this(name, stock);
        if ( open ) {
            open();
        }
    }
    public void open() {
        if ( this.opened ) {
            throw new RuntimeException(String.format("The %s coffeeShop is already open!", this.name));
        }
        this.opened = true;
        System.out.println(String.format("The %s coffeeShop is open!", this.name));
    }

    public boolean isOpen() {
        return this.opened;
    }

    @Override
    public void close() throws IOException {
        if ( this.customers > 0 ) {
            throw new RuntimeException(String.format("the %s coffeeShop cannot be closed, customers still inside", this.name));
        }
        this.opened = false;
        System.out.println(String.format("the %s coffeShop is closed!", this.name));
    }

    public void addStock( int countity) {
        this.stock += countity;
        System.out.println(String.format("There is %d in stock in the %s coffeeShop", this.stock, this.name));
    }

    public void serveCoffee(int count) {
        if ( !this.opened ) {
            throw new RuntimeException(String.format("The %s coffeeShop is not open!", this.name));
        }
        if ( this.stock < 0 || count > this.stock) {
            throw new RuntimeException("Stock is not enough");
        }
        this.stock = this.stock - count;
        System.out.println(String.format("%d was served in the %s coffeeShop, %s are left in stock", count, this.name, this.stock));
    }

    public void receiveCustomers(int count) {
        if ( !this.opened ) {
            throw new RuntimeException(String.format("The %s coffeeShop is not open!", this.name));
        }
        this.customers += count;
        System.out.println(String.format("%d customers in the %s coffeShop", this.customers, this.name));
    }

    public void leaveHouse(int count) {
        if ( this.customers < count ) {
            System.out.println(String.format("All customers aleady left from the %s coffeeShop", this.name));
        }
        this.customers = this.customers > count ? this.customers - count : 0;
        System.out.println(String.format("%d customer left, %d customers left in the %s coffeShop", count, this.customers, this.name));
    }

}

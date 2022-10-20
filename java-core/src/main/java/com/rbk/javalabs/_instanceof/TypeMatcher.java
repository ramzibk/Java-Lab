package com.rbk.javalabs._instanceof;

public class TypeMatcher {

    public static void main(String[] args) {

        Number num = 10;
        if ( num instanceof Integer) {
            Integer value = (Integer) num;
            System.out.println("object is an Integer with value " + value);
        }

        // using pattern matching
        if ( num instanceof Integer data) {
            System.out.println("object is an Integer with value " + data);
        }

        // pattern matching requires the pattern variable (data) to be of a subtype of the variable on the left side (num)
        //if ( num instanceof Number data) { // compile error
        //}
        //if ( num instanceof String data) { // compile error
        //}

        // the pattern variable can be used in a statement
        if ( num instanceof Integer data && data >= 0) {
            System.out.println("object is a positive Integer with value " + data);
        }
        // this works only in conjunction the logical AND (&&) operator
        // if ( num instanceof Integer data || data >= 0 ) // compile error

        Shape rectangle = new Rectangle(5, 5);
        Shape circle = new Circle(8);

        System.out.println("rectangle has a perimeter of " + Shape.getPerimeter(rectangle));
        System.out.println("circle has a perimeter of " + Shape.getPerimeter(circle));
    }

}

interface Shape {
    static double getPerimeter(Shape shape) throws IllegalArgumentException {
        if (shape instanceof Rectangle r) {
            return 2 * r.length() + 2 * r.width();
        } else if (shape instanceof Circle c) {
            return 2 * c.radius() * Math.PI;
        } else {
            throw new IllegalArgumentException("Unrecognized shape");
        }
    }
}

class Circle implements Shape {
    final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    double radius() { return radius; }
}

class Rectangle implements Shape {
    final double length;
    final double width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    double length() { return length; }
    double width() { return width; }
}

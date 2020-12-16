package model;

public class Order {
    private String address;
    private String time;
    private String pizzaType;
    private String pizzaSize;
    private String extraDrink;
    private String sauce;

    public Order() {
    }

    public Order(String address, String time, String pizzaType, String pizzaSize, String extraDrink, String sauce) throws IllegalArgumentException {
        this.address = address;
        this.time = time;
        this.pizzaType = pizzaType;
        this.pizzaSize = pizzaSize;
        this.extraDrink = extraDrink;
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return "Address " + address + " Time " + time + " pizzaType " + pizzaType + " pizzaSize " + pizzaSize + " extraDrink " + extraDrink + " sauce " + sauce;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }
}

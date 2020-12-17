package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.ParseException;
import java.util.Objects;

public class Order {
    private Address address;
    private Time time;
    private PizzaType pizzaType;
    private PizzaSize pizzaSize;
    private ExtraDrink extraDrink;
    private Sauce sauce;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setPizzaSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setExtraDrink(ExtraDrink extraDrink) {
        this.extraDrink = extraDrink;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public Order() {
    }

    public Order(String address, String time, String pizzaType, String pizzaSize, String extraDrink, String sauce) throws IllegalArgumentException {
        try {
            this.address = Address.parse(address);
            this.time = Time.parse(time);
            this.pizzaType = PizzaType.parse(pizzaType);
            this.pizzaSize = PizzaSize.parse(pizzaSize);
            this.extraDrink = ExtraDrink.parse(extraDrink);
            this.sauce = Sauce.parse(sauce);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

//    @Override
////    public String toString() {
////        return "Address " + address + " Time " + time + " pizzaType " + pizzaType + " pizzaSize " + pizzaSize + " extraDrink " + extraDrink + " sauce " + sauce;
////    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(address)
                .append(time)
                .append(pizzaType)
                .append(pizzaSize)
                .append(extraDrink)
                .append(sauce)
                .toString();
    }

    public Address getAddress() {
        return address;
    }

    public Time getTime() {
        return time;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }

    public ExtraDrink getExtraDrink() {
        return extraDrink;
    }

    public Sauce getSauce() {
        return sauce;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Order rhs = (Order) obj;
        return new EqualsBuilder()
                .append(address, rhs.address)
                .append(time, rhs.time)
                .append(pizzaType, rhs.pizzaType)
                .append(pizzaSize, rhs.pizzaSize)
                .append(extraDrink, rhs.extraDrink)
                .append(sauce, rhs.sauce)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, time, pizzaType, pizzaSize, extraDrink, sauce);
    }
}

package test;

import model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @Test
    public void basicValidation() {
        Order expected = new Order("name, 40, 66", "11:45", PizzaType.Pepperoni.toString(), PizzaSize.Low.toString(), ExtraDrink.Cola.toString(), Sauce.Cheese.toString());
        Order actual = new Order("name, 40, 66", "11:45", PizzaType.Pepperoni.toString(), PizzaSize.Low.toString(), ExtraDrink.Cola.toString(), Sauce.Cheese.toString());
        assertEquals(expected, actual);
    }

    @Test
    public void parsingValidation() {
        String address = "street, 5, 7",
                time = "11:30",
                pizzaType = "Pepperoni",
                pizzaSize = "Low",
                extraDrink = "Pepsi",
                sauce = "BBQ";

        Order order = new Order(address, time, pizzaType, pizzaSize, extraDrink, sauce);

        assertEquals(address, order.getAddress().toString());
        assertEquals(time, order.getTime().toString());
        assertEquals(pizzaSize, order.getPizzaSize().toString());
        assertEquals(pizzaType, order.getPizzaType().toString());
        assertEquals(extraDrink, order.getExtraDrink().toString());
        assertEquals(sauce, order.getSauce().toString());
    }
}

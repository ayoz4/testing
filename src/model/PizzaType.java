package model;

import java.text.ParseException;

public enum PizzaType {
    Pepperoni,
    Margarita,
    Hawaiian;

    private PizzaType() {
    }

    ;

    public static PizzaType parse(String pizza) throws ParseException {
        if (pizza.equals("Pepperoni"))
            return PizzaType.Pepperoni;
        else if (pizza.equals("Margarita"))
            return PizzaType.Margarita;
        else if (pizza.equals("Hawaiian"))
            return PizzaType.Hawaiian;
        else
            throw new ParseException(pizza, 0);
    }
}

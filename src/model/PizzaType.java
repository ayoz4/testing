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
        if (pizza == "Пепперони")
            return PizzaType.Pepperoni;
        else if (pizza == "Маргарита")
            return PizzaType.Margarita;
        else if (pizza == "Гавайская")
            return PizzaType.Hawaiian;
        else
            throw new ParseException(pizza, 0);
    }
}

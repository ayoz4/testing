package model;

import java.text.ParseException;

public enum ExtraDrink {
    Cola,
    Pepsi,
    Fanta;

    private ExtraDrink() {
    }

    ;

    public static ExtraDrink parse(String drink) throws ParseException {
        if (drink.equals("Cola"))
            return ExtraDrink.Cola;
        else if (drink.equals("Pepsi"))
            return ExtraDrink.Pepsi;
        else if (drink.equals("Fanta"))
            return ExtraDrink.Fanta;
        else
            throw new ParseException(drink, 0);
    }
}

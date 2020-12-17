package model;

import java.text.ParseException;

public enum PizzaSize {
    Low,
    Middle,
    High;

    private PizzaSize() {
    }

    ;

    public static PizzaSize parse(String size) throws ParseException {
        if (size.equals("Low"))
            return PizzaSize.Low;
        else if (size.equals("Middle"))
            return PizzaSize.Middle;
        else if (size.equals("High"))
            return PizzaSize.High;
        else
            throw new ParseException(size, 0);
    }
}

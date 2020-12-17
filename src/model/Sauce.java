package model;

import java.text.ParseException;

public enum Sauce {
    Cheese,
    Teriyaki,
    BBQ;

    private Sauce() {
    }
    public static Sauce parse(String sauce) throws ParseException {
        if (sauce.equals("Cheese"))
            return Sauce.Cheese;
        else if (sauce.equals("Teriyaki"))
            return Sauce.Teriyaki;
        else if (sauce.equals("BBQ"))
            return Sauce.BBQ;
        else
            throw new ParseException(sauce, 0);
    }
}

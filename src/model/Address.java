package model;

import java.text.ParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
    public static final String SEPARATOR = ",";

    private String address;

    public Address(String address) {
        this.address = address;
    }

    public static Address parse(String address) throws ParseException {
        address = address.trim();
        Pattern pattern = Pattern.compile("\\D*, \\d*, \\d*");
        Matcher matcher =  pattern.matcher(address);

        if (matcher.matches()) {
            return new Address(address);
        }

        throw new ParseException(address, 0);
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}

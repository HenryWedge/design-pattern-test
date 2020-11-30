package de.ppi.here.tcu.util;

public class StringUtil {

    public static boolean containsLowerCase(String value) {
        return !value.toUpperCase().equals(value);
    }
}

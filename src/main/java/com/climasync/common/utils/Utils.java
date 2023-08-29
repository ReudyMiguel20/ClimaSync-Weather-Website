package com.climasync.common.utils;

import java.util.Locale;

public class Utils {

    //Method that converts the country name into its corresponding two-letter country code, if the country doesn't exist
    //then it returns null
    public static String getCountryCode(String countryName) {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            if (countryName.equalsIgnoreCase(locale.getDisplayCountry())) {
                return locale.getCountry();
            }
        }

        return null;
    }

}

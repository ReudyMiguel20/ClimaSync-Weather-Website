package com.climasync.common.utils;

import com.climasync.weather.exception.CountryNotFoundException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.Locale;

public class Utils {

    // Method that converts the country name into its corresponding two-letter country code, if the country doesn't exist then it returns null

    // The system language must be in English for this method to work. This can brick the entire project.
    // -- Need to add a way for it that the user system language doesn't depends on this to work and set a default to english --
    public static String getCountryCode(String countryName) {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            if (countryName.equalsIgnoreCase(locale.getDisplayCountry())) {
                return locale.getCountry();
            }
        }

        throw new CountryNotFoundException();
    }

    // Method that copies the non-null properties from one object to another object of the same type (e.g. from Location to Location) using Apache Commons BeanUtils
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
        PropertyUtilsBean propertyUtilsBean = beanUtilsBean.getPropertyUtils();

        try {
            for (PropertyDescriptor descriptor : propertyUtilsBean.getPropertyDescriptors(source)) {
                String propertyName = descriptor.getName();

                if (propertyUtilsBean.isReadable(source, propertyName) && propertyUtilsBean.isWriteable(target, propertyName)) {
                    Object sourceValue = PropertyUtils.getProperty(source, propertyName);
                    Object targetValue = PropertyUtils.getProperty(target, propertyName);

                    if (sourceValue != null && targetValue != null) {
                        PropertyUtils.setProperty(target, propertyName, sourceValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

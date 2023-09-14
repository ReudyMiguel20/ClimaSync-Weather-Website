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
    public static String getCountryCode(String countryName) {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            System.out.println(locale.getDisplayCountry());
            if (countryName.equalsIgnoreCase(locale.getDisplayCountry())) {
                return locale.getCountry();
            }
        }

        throw new CountryNotFoundException();
    }

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

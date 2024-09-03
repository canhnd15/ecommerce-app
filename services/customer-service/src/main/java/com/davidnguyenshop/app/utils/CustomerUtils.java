package com.davidnguyenshop.app.utils;

import com.davidnguyenshop.app.entities.Customer;
import org.apache.logging.log4j.util.Strings;

public class CustomerUtils {
    public static String getFullName(Customer customer) {
        if (Strings.isEmpty(customer.getFirstName()) || Strings.isEmpty(customer.getFirstName())) {
            return "";
        }
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public static String  getFullAddress(Customer customer) {
        return customer.getAddress().getHouseNumber() + " " + customer.getAddress().getStreet() + " " + customer.getAddress().getCity();
    }
}

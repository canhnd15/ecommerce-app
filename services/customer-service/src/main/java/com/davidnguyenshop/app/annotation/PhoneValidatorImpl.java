package com.davidnguyenshop.app.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PhoneValidatorImpl implements ConstraintValidator<PhoneValidator, String> {
    private static final Map<String, String> COUNTRY_PHONE_REGEX = new HashMap<>();

    static {
        COUNTRY_PHONE_REGEX.put(String.valueOf(CountryEnums.VN), "^(03|05|07|08|09)\\d{8}$");
        COUNTRY_PHONE_REGEX.put(String.valueOf(CountryEnums.US), "^\\+1\\d{10}$");
    }

    private String country;

    @Override
    public void initialize(PhoneValidator constraintAnnotation) {
        this.country = constraintAnnotation.country();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        String regex = COUNTRY_PHONE_REGEX.get(country);
        if (regex == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }
}

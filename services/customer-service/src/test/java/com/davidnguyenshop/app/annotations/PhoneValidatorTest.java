package com.davidnguyenshop.app.annotations;

import com.davidnguyenshop.app.annotation.CountryEnums;
import com.davidnguyenshop.app.annotation.PhoneValidator;
import com.davidnguyenshop.app.annotation.PhoneValidatorImpl;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneValidatorTest {
    private PhoneValidatorImpl phoneValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        phoneValidator = new PhoneValidatorImpl();
        context = mock(ConstraintValidatorContext.class);

        PhoneValidator phoneValidatorAnnotationVn = Mockito.mock(PhoneValidator.class);
        when(phoneValidatorAnnotationVn.country()).thenReturn(CountryEnums.VN.name());

        phoneValidator.initialize(phoneValidatorAnnotationVn);
    }

    @Test
    public void testValidVietnamPhoneNumber() {
        assertTrue(phoneValidator.isValid("0912345678", context));
        assertTrue(phoneValidator.isValid("0312345678", context));
        assertTrue(phoneValidator.isValid("0812345678", context));
    }

    @Test
    public void testInvalidVietnamPhoneNumber() {
        assertFalse(phoneValidator.isValid("123456789", context)); // Not matching pattern
        assertFalse(phoneValidator.isValid("0212345678", context)); // Invalid start digits
        assertFalse(phoneValidator.isValid("09123456789", context)); // Too long
    }

    @Test
    public void testValidUSPhoneNumber() {
        PhoneValidator phoneValidatorAnnotation = Mockito.mock(PhoneValidator.class);
        when(phoneValidatorAnnotation.country()).thenReturn(CountryEnums.US.name());
        phoneValidator.initialize(phoneValidatorAnnotation);

        assertTrue(phoneValidator.isValid("+11234567890", context));
    }

    @Test
    public void testInvalidUSPhoneNumber() {
        assertFalse(phoneValidator.isValid("1234567890", context)); // Missing country code
        assertFalse(phoneValidator.isValid("+1234567890", context)); // Missing digit
        assertFalse(phoneValidator.isValid("+112345678901", context)); // Too long
    }

    @Test
    public void testUnsupportedCountry() {
        PhoneValidator phoneValidatorAnnotation = Mockito.mock(PhoneValidator.class);
        when(phoneValidatorAnnotation.country()).thenReturn("NONE");
        phoneValidator.initialize(phoneValidatorAnnotation);

        assertFalse(phoneValidator.isValid("0912345678", context)); // Unsupported country
    }

    @Test
    public void testNullPhoneNumber() {
        assertFalse(phoneValidator.isValid(null, context));
    }

    @Test
    public void testEmptyPhoneNumber() {
        assertFalse(phoneValidator.isValid("", context));
    }
}

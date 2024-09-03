package com.davidnguyenshop.app.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ResourceMessageImpl implements ResourceMessage{
    private final MessageSource messageSource;

    Locale localeVi = new Locale("vi");
    Locale localeEn = new Locale("en");

    @Override
    public String getVietnameseMessage(String code) {
        return messageSource.getMessage(code, null, localeVi);
    }

    @Override
    public String getEnglishMessage(String code) {
        return messageSource.getMessage(code, null, localeEn);
    }

    @Override
    public String getMessage(String code, Locale locale) {
        return "";
    }
}

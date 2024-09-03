package com.davidnguyenshop.app.utils;

import java.util.Locale;

public interface ResourceMessage {
    String getVietnameseMessage(String code);
    String getEnglishMessage(String code);
    String getMessage(String code, Locale locale);
}

package com.davidnguyenshop.app.errors;

import java.util.Map;

public record ResponseError(
        Map<String, String> errors
) {
}

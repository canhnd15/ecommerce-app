package com.davidnguyenshop.app.dtos;

public record NewCustomerResp (
        String id,
        String firstName,
        String lastName,
        String fullName,
        String email,
        String phone,
        String fullAddress,
        String zipCode
) {
}

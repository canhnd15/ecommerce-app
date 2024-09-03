package com.davidnguyenshop.app.dtos;

import com.davidnguyenshop.app.annotation.CountryEnums;
import com.davidnguyenshop.app.annotation.PhoneValidator;
import com.davidnguyenshop.app.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerCreateReq {
        String id;

        @NotNull(message = "Customer firstname is required!")
        String firstName;

        @NotNull(message = "Customer lastname is required!")
        String lastName;

        @NotNull(message = "Customer email is required!")
        @Email(message = "Customer email is invalided!")
        String email;

        @PhoneValidator(message = "Phone number is not valid in Vietnam. It must start with 03, 05, 07, 08, or 09 and have 10 digits.")
        @NotNull(message = "Customer phone is required!")
        String phone;

        Address address;
}

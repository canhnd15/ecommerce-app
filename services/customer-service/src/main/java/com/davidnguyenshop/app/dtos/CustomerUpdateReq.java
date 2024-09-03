package com.davidnguyenshop.app.dtos;

import com.davidnguyenshop.app.annotation.PhoneValidator;
import com.davidnguyenshop.app.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerUpdateReq {
    @NotBlank(message = "Firstname cannot be empty or blank")
    String firstName;

    @NotBlank(message = "Lastname cannot be empty or blank")
    String lastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty or blank")
    @Email(message = "Customer email is invalided!")
    String email;

    @PhoneValidator(message = "Phone number is not valid in Vietnam. It must start with 03, 05, 07, 08, or 09 and have 10 digits.")
    @NotBlank(message = "Phone cannot be empty or blank")
    String phone;

    Address address;
}

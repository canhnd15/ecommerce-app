package com.davidnguyenshop.app.dtos;

import com.davidnguyenshop.app.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerReq {
        String id;

        @NotNull(message = "Customer firstname is required!")
        String firstName;

        @NotNull(message = "Customer lastname is required!")
        String lastName;

        @NotNull(message = "Customer email is required!")
        @Email(message = "Customer email is invalided!")
        String email;

        @NotNull(message = "Customer phone is required!")
        String phone;

        Address address;
}

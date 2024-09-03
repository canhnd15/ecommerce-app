package com.davidnguyenshop.app.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerResp {
    String id;
    String firstName;
    String lastName;
    String fullName;
    String email;
    String phone;
    String fullAddress;
    String zipCode;
}

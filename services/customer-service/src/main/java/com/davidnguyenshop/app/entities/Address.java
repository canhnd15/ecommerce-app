package com.davidnguyenshop.app.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document
@Validated
public class Address {
    @Id
    private String id;
    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
}

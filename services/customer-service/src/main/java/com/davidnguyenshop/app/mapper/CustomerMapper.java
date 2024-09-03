package com.davidnguyenshop.app.mapper;

import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerResp;
import com.davidnguyenshop.app.entities.Customer;
import com.davidnguyenshop.app.enums.CustomerStatus;
import com.davidnguyenshop.app.utils.CustomerUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerCreateReq req){
        if(Optional.ofNullable(req).isEmpty())
            return null;

        return Customer.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .status(req.getStatus())
                .address(req.getAddress())
                .build();
    }

    public CustomerResp toCustomerResp(Customer customer) {
        if(Optional.ofNullable(customer).isEmpty())
            return null;

        return CustomerResp.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .fullName(CustomerUtils.getFullName(customer))
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .fullAddress(CustomerUtils.getFullAddress(customer))
                .zipCode(customer.getAddress().getZipCode())
                .build();
    }
}

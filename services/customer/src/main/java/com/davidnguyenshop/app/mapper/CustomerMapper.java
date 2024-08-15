package com.davidnguyenshop.app.mapper;

import com.davidnguyenshop.app.dtos.NewCustomerReq;
import com.davidnguyenshop.app.dtos.NewCustomerResp;
import com.davidnguyenshop.app.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerMapper {
    public Customer toCustomer(NewCustomerReq req){
        if(Optional.ofNullable(req).isEmpty())
            return null;

        return Customer.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .phone(req.phone())
                .address(req.address())
                .build();
    }

    public NewCustomerResp toCustomerResp(Customer customer) {
        if(Optional.ofNullable(customer).isEmpty())
            return null;

        return new NewCustomerResp(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getLastName() + " " + customer.getFirstName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress().getHouseNumber() + " " + customer.getAddress().getStreet() + " " + customer.getAddress().getCity(),
                customer.getAddress().getZipCode());
    }
}

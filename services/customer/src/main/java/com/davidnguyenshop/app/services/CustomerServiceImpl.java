package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.entities.Customer;
import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerReq;
import com.davidnguyenshop.app.dtos.CustomerResp;
import com.davidnguyenshop.app.enums.StatusCode;
import com.davidnguyenshop.app.exceptions.CustomerNotFoundException;
import com.davidnguyenshop.app.mapper.CustomerMapper;
import com.davidnguyenshop.app.repositories.CustomerRepository;
import com.davidnguyenshop.app.utils.CustomerUtils;
import com.davidnguyenshop.app.utils.ResourceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ResourceMessage resourceMessage;

    @Override
    @Transactional
    public ApiResponse<?> create(CustomerReq req) {
        Customer customer = customerMapper.toCustomer(req);

        customer = customerRepository.save(customer);

        CustomerResp resp = customerMapper.toCustomerResp(customer);

        return ApiResponse.builder()
                        .data(resp)
                        .message("Add new customer successfully!")
                        .status(String.valueOf(StatusCode.SUCCESS))
                .build();
    }

    @Override
    public ApiResponse<?> getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        CustomerResp resp = CustomerResp.builder()
                .id(customerId)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .fullName(CustomerUtils.getFullName(customer))
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .fullAddress(CustomerUtils.getFullAddress(customer))
                .zipCode(customer.getAddress().getZipCode())
                .build();

        return ApiResponse.builder()
                .status(String.valueOf(StatusCode.SUCCESS))
                .message("")
                .data(resp)
                .build();
    }

    @Override
    public ApiResponse<?> getCustomers() {
        List<CustomerResp> customers = customerRepository.findAll().stream()
                .map((customer) -> CustomerResp.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .fullName(CustomerUtils.getFullName(customer))
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .fullAddress(CustomerUtils.getFullAddress(customer))
                        .zipCode(customer.getAddress().getZipCode())
                        .build())
                .toList();

        return ApiResponse.builder()
                .status(String.valueOf(StatusCode.SUCCESS))
                .message("")
                .data(customers)
                .build();
    }
}

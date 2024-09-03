package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.dtos.CustomerUpdateReq;
import com.davidnguyenshop.app.entities.Customer;
import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerResp;
import com.davidnguyenshop.app.enums.StatusCode;
import com.davidnguyenshop.app.exceptions.CustomerNotFoundException;
import com.davidnguyenshop.app.mapper.CustomerMapper;
import com.davidnguyenshop.app.repositories.CustomerRepository;
import com.davidnguyenshop.app.utils.CustomerUtils;
import com.davidnguyenshop.app.utils.ResourceMessage;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
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
    public ApiResponse<?> create(CustomerCreateReq req) {
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
    public ApiResponse<?> findAllCustomers() {
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

    @Override
    public ApiResponse<?> update(String customerId, CustomerUpdateReq req) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if(!Strings.isEmpty(req.getFirstName()) && !Strings.isBlank(req.getFirstName()))
            customer.setFirstName(req.getFirstName());

        if(!Strings.isEmpty(req.getLastName()) && !Strings.isBlank(req.getLastName()))
            customer.setLastName(req.getLastName());

        if(!Strings.isEmpty(req.getEmail()) && !Strings.isBlank(req.getEmail()))
            customer.setFirstName(req.getFirstName());


        return null;
    }
}

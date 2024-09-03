package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.dtos.CustomerUpdateReq;
import com.davidnguyenshop.app.entities.Customer;
import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerResp;
import com.davidnguyenshop.app.enums.CustomerStatus;
import com.davidnguyenshop.app.enums.StatusCode;
import com.davidnguyenshop.app.exceptions.CustomerNotFoundException;
import com.davidnguyenshop.app.mapper.CustomerMapper;
import com.davidnguyenshop.app.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

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
    @Transactional(readOnly = true)
    public ApiResponse<?> getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found with id %s", customerId)));

        CustomerResp resp = customerMapper.toCustomerResp(customer);

        return ApiResponse.builder()
                .status(String.valueOf(StatusCode.SUCCESS))
                .message("Found!")
                .data(resp)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<?> findAllCustomers() {
        List<CustomerResp> customers = customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResp)
                .toList();

        return ApiResponse.builder()
                .status(String.valueOf(StatusCode.SUCCESS))
                .message("Found!")
                .data(customers)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<?> update(String customerId, CustomerUpdateReq req) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setPhone(req.getPhone());
        customer.setAddress(req.getAddress());

        customer = customerRepository.save(customer);

        return ApiResponse.builder()
                .status(StatusCode.SUCCESS.name())
                .message("Updated!")
                .data(customerMapper.toCustomerResp(customer))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<?> findCustomerByIdAndStatus(String id, String status) {
        Customer customer = customerRepository.findCustomerByIdAndStatus(id, status)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found with id %s and status %s", id, status)));

        return ApiResponse.builder()
                .status(StatusCode.SUCCESS.name())
                .message("Found!")
                .data(customerMapper.toCustomerResp(customer))
                .build();
    }

    @Override
    public ApiResponse<?> changeStatus(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer not found with id %s", id)));

        if(CustomerStatus.ACTIVE.name().equals(customer.getStatus())) {
            customer.setStatus(CustomerStatus.INACTIVE.name());
        } else {
            customer.setStatus(CustomerStatus.ACTIVE.name());
        }

        return ApiResponse.builder()
                .status(StatusCode.SUCCESS.name())
                .message("Updated!")
                .data(customerMapper.toCustomerResp(customer))
                .build();
    }
}

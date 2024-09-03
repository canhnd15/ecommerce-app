package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerUpdateReq;

public interface CustomerService {
    ApiResponse<?> create(CustomerCreateReq req);

    ApiResponse<?> getCustomerById(String customerId);

    ApiResponse<?> findAllCustomers();

    ApiResponse<?> update(String customerId, CustomerUpdateReq req);
}

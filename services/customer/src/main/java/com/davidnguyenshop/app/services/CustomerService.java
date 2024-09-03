package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerReq;

public interface CustomerService {
    ApiResponse<?> create(CustomerReq req);

    ApiResponse<?> getCustomerById(String customerId);

    ApiResponse<?> getCustomers();
}

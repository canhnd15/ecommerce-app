package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.NewCustomerReq;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ApiResponse<?>> addNewCustomer(NewCustomerReq req);
}

package com.davidnguyenshop.app.services;

import com.davidnguyenshop.app.entities.Customer;
import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.NewCustomerReq;
import com.davidnguyenshop.app.dtos.NewCustomerResp;
import com.davidnguyenshop.app.enums.StatusCode;
import com.davidnguyenshop.app.mapper.CustomerMapper;
import com.davidnguyenshop.app.repositories.CustomerRepository;
import com.davidnguyenshop.app.utils.ResourceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ResourceMessage resourceMessage;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> addNewCustomer(NewCustomerReq req) {
        Customer customer = customerMapper.toCustomer(req);

        customer = customerRepository.save(customer);

        NewCustomerResp resp = customerMapper.toCustomerResp(customer);

        return ResponseEntity.ok(ApiResponse.builder()
                        .data(resp)
                        .message(resourceMessage.getEnglishMessage("CUST-001"))
                        .status(String.valueOf(StatusCode.SUCCESS))
                .build());
    }
}

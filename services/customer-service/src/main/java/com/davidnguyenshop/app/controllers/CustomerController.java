package com.davidnguyenshop.app.controllers;

import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerUpdateReq;
import com.davidnguyenshop.app.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid CustomerCreateReq req) {
        return new ResponseEntity<>(customerService.create(req), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable(name = "customerId") String customerId,
                                                 @RequestBody @Valid CustomerUpdateReq req) {
        return new ResponseEntity<>(customerService.update(customerId, req), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> getCustomers() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<?>> getCustomerById(@PathVariable(name = "customerId") String customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
}

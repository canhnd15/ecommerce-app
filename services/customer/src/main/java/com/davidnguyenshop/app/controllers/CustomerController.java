package com.davidnguyenshop.app.controllers;

import com.davidnguyenshop.app.entities.Address;
import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.NewCustomerReq;
import com.davidnguyenshop.app.services.CustomerService;
import com.davidnguyenshop.app.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> getCustomers() {
        return ResponseEntity.ok(ApiResponse.builder()
                        .data(IntStream.range(0, 10).mapToObj(i -> Customer.builder()
                                        .firstName("firstname " + i)
                                        .lastName("lastname " + i)
                                        .email("email_" + i + "@gmail.com")
                                        .phone("035766012" + i)
                                        .address(Address.builder()
                                                .city("city " + i)
                                                .street("street " + i)
                                                .houseNumber("" + i)
                                                .zipCode("00012" + i)
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                .build());
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<?>> addNewCustomer(@RequestBody @Valid NewCustomerReq req) {
        return customerService.addNewCustomer(req);
    }
}

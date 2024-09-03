package com.davidnguyenshop.app.service;

import com.davidnguyenshop.app.dtos.ApiResponse;
import com.davidnguyenshop.app.dtos.CustomerCreateReq;
import com.davidnguyenshop.app.dtos.CustomerResp;
import com.davidnguyenshop.app.entities.Address;
import com.davidnguyenshop.app.enums.StatusCode;
import com.davidnguyenshop.app.repositories.CustomerRepository;
import com.davidnguyenshop.app.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	void create_customer_should_return_created_customer() {
		CustomerCreateReq req = CustomerCreateReq.builder()
				.firstName("david")
				.lastName("nguyen")
				.email("test.email@gmail.com")
				.phone("012312312")
				.address(Address.builder()
						.id(String.valueOf(UUID.randomUUID()))
						.city("Hanoi")
						.street("Abc")
						.houseNumber("123")
						.zipCode("10000")
						.build())
				.build();

		CustomerResp respBody = CustomerResp.builder()
				.id(String.valueOf(UUID.randomUUID()))
				.firstName("david")
				.lastName("nguyen")
				.email("test.email@gmail.com")
				.phone("012312312")
				.fullAddress(req.getAddress().getHouseNumber() + " " + req.getAddress().getStreet() + " " + req.getAddress().getCity())
				.zipCode("10000")
				.build();

		ApiResponse<CustomerResp> expectedResp = new ApiResponse<>(String.valueOf(StatusCode.SUCCESS), "acc", respBody);

//		when(customerService.create(req)).thenReturn(expectedResp);

		ApiResponse<?> createdCustomer = customerService.create(req);

		verify(customerRepository, times(1)).save(any());

		assertNotNull(createdCustomer);
		assertNotNull(createdCustomer.getData());
		assertEquals(createdCustomer.getStatus(), String.valueOf(StatusCode.SUCCESS));
	}
}

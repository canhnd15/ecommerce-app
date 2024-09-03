package com.davidnguyenshop.app.repositories;

import com.davidnguyenshop.app.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{id:?0, status:?1}")
    Optional<Customer> findCustomerByIdAndStatus(String id, String status);
}

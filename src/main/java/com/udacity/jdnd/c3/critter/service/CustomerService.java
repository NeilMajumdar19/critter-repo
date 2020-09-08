package com.udacity.jdnd.c3.critter.service;

import com.udacity.jdnd.c3.critter.entity.Customer;
import com.udacity.jdnd.c3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Long customerId)
    {
        return customerRepository.findById(customerId).get();
    }

    public Customer saveCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }

}

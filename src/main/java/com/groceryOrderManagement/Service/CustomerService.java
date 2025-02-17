package com.groceryOrderManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryOrderManagement.Entity.Customer;
import com.groceryOrderManagement.Repository.CustomerRepository;

@Service
public class CustomerService 
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id)
    {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer)
    {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id)
    {
        customerRepository.deleteById(id);
    }
}
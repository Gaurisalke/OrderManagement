package com.groceryOrderManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceryOrderManagement.Entity.Customer;
import com.groceryOrderManagement.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController 
{

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) 
    {
        Optional<Customer> customer = customerService.getCustomerById(id);
        //If the Optional<Customer> contains a customer,it returns an HTTP 200 OK response with the customer data.
        //If the Optional<Customer> is empty, it returns HTTP 404 Not Found response.
        return customer.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) 
    {
        //Wraps the updated customer in a ResponseEntity with HTTP status 200 OK.
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) 
    {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();

        //ResponseEntity.noContent()=>Returns an HTTP 204 No Content response,
        // which is the standard status code for a successful DELETE request.
        
        //build() =>Builds and returns the ResponseEntity without any response body.
    }
}
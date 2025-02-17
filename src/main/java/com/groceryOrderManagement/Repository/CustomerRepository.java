package com.groceryOrderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groceryOrderManagement.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>
{

}
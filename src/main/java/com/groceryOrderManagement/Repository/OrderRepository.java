package com.groceryOrderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groceryOrderManagement.Entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
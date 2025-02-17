package com.groceryOrderManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groceryOrderManagement.Entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem,Long>
{

}
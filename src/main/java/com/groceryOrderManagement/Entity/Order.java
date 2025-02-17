package com.groceryOrderManagement.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="customer_order")
@Data
public class Order 
{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; 

    @ManyToMany
    @JoinTable(name="order_groceryItem",
               joinColumns = @JoinColumn(name="order_id"),
               inverseJoinColumns= @JoinColumn(name="groceryItem_Id")
    )
    private List<GroceryItem> groceryItems; 
    private Date orderDate; 
    private double totalPrice;
}
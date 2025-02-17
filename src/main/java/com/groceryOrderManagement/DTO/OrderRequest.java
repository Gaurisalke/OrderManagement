package com.groceryOrderManagement.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest 
{
    private Long customerId;
    private List<Long> groceryItemId;
    private Date orderDate; 
    private double totalPrice;
}

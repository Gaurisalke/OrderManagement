package com.groceryOrderManagement.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryOrderManagement.DTO.OrderRequest;
import com.groceryOrderManagement.Entity.Customer;
import com.groceryOrderManagement.Entity.GroceryItem;
import com.groceryOrderManagement.Entity.Order;
import com.groceryOrderManagement.Repository.CustomerRepository;
import com.groceryOrderManagement.Repository.GroceryItemRepository;
import com.groceryOrderManagement.Repository.OrderRepository;
import com.groceryOrderManagement.exception.ResourceNotFoundException;

@Service
public class OrderService 
{
@Autowired
private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private GroceryItemRepository groceryItemRepository;

public List<Order> getAllOrders()
{
    return orderRepository.findAll();
}

public Optional<Order> getOrderById(Long id)
{
    return orderRepository.findById(id);
}

public Order createOrder(OrderRequest orderRequest)
{
     // Fetch full Customer entity by ID
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId()));
        
        // Fetch full GroceryItems list
        List<GroceryItem> groceryItems = groceryItemRepository.findAllById(orderRequest.getGroceryItemId());
        
        if (groceryItems.size() != orderRequest.getGroceryItemId().size()) {
            throw new ResourceNotFoundException("One or more products not found");
        }     

        Order order = new Order();

        // Set full entities into the order
         order.setCustomer(customer);
         order.setGroceryItems(groceryItems);
         

       // Fetch orderDate and totalPrice from JSON and set them in the entity
        order.setOrderDate(orderRequest.getOrderDate()); 
        order.setTotalPrice(orderRequest.getTotalPrice());

        // Save and return the order
        return orderRepository.save(order);      
}

public Order updateOrder(Long id, Order order)
{
    order.setId(id);
    return orderRepository.save(order);
}

public void deleteOrder(Long id)
{
    orderRepository.deleteById(id);
}
}
package com.groceryOrderManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceryOrderManagement.DTO.OrderRequest;
import com.groceryOrderManagement.Entity.Order;
import com.groceryOrderManagement.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController 
{
    @Autowired
    private OrderService orderService;

     @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

     @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) 
    {
        Optional<Order> item = orderService.getOrderById(id);
               return item.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
       // Order createdOrder = orderService.createOrder(order);
       // return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        return orderService.createOrder(orderRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) 
    {
        //Wraps the updated GroceryIem in a ResponseEntity with HTTP status 200 OK.
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) 
    {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();       
    }
}
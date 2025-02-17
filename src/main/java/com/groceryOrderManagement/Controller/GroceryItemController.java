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

import com.groceryOrderManagement.Entity.GroceryItem;
import com.groceryOrderManagement.Service.GroceryItemService;

@RestController
@RequestMapping("/api/items")
public class GroceryItemController 
{

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemService.getAllGroceryItems();
    }

     @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) 
    {
        Optional<GroceryItem> item = groceryItemService.getGroceryItemById(id);
               return item.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PostMapping
    public ResponseEntity<GroceryItem> createGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem createdGroceryItem = groceryItemService.createGroceryItem(groceryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroceryItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) 
    {
        //Wraps the updated GroceryIem in a ResponseEntity with HTTP status 200 OK.
        return ResponseEntity.ok(groceryItemService.updateGroceryItem(id, groceryItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) 
    {
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();       
    }
}
package com.groceryOrderManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryOrderManagement.Entity.GroceryItem;
import com.groceryOrderManagement.Repository.GroceryItemRepository;

@Service
public class GroceryItemService 
{
@Autowired
private GroceryItemRepository groceryItemRepository;

public List<GroceryItem> getAllGroceryItems()
{
    return groceryItemRepository.findAll();
}

public Optional<GroceryItem> getGroceryItemById(Long id)
{
    return groceryItemRepository.findById(id);
}

public GroceryItem createGroceryItem(GroceryItem groceryItem)
{
    return groceryItemRepository.save(groceryItem);
}

public GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem)
{
    groceryItem.setId(id);
    return groceryItemRepository.save(groceryItem);
}

public void deleteGroceryItem(Long id)
{
  groceryItemRepository.deleteById(id);
}
}
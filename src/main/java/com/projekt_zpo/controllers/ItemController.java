package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.Item;
import com.projekt_zpo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @PostMapping(value = "/items-category", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    List<Item> categoryItems(String category)
    {
        return itemRepository.findByCategoryId(category);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-item", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    ResponseEntity newItem (Item newitem){
        ResponseEntity response;

        itemRepository.save(newitem);
        response = new ResponseEntity(HttpStatus.OK);

        return response;

    }
}

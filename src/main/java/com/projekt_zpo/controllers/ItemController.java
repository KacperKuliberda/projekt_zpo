package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.Item;
import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.ItemRepository;
import com.projekt_zpo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/items")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @PostMapping(value = "/items-category", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    List<Item> categoryItems(Integer categoryId)
    {
        return itemRepository.findByCategoryId(categoryId);

    }

    @PostMapping(value="/item-id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    Map<String,Object> requestedItem(Integer itemId)
    {
        Map<String,Object> itemDetails= new HashMap<>();

        Item requested = itemRepository.findItemById(itemId);
        User addedBy = userRepository.findUserById(requested.getUserId());

        itemDetails.put("item",requested);
        itemDetails.put("user",addedBy);

        return itemDetails;
        //return itemRepository.findItemById(itemId);
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

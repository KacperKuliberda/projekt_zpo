package com.projekt_zpo.controllers;


import com.projekt_zpo.entities.Item;
import com.projekt_zpo.repositories.CategoryRepository;
import com.projekt_zpo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ItemRepository itemRepository;

    @Autowired
    public ProductController( ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @GetMapping("/productPage")
    public String showProduct(@CookieValue(value = "username",required = false)String username,
                              @RequestParam(value = "productID") Integer productID,
                              Model model) {

        Item item = itemRepository.findItemById(productID);
        model.addAttribute("item", item);
        return "product";
    }
}


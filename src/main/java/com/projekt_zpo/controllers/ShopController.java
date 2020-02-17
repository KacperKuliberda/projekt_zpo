package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.Category;
import com.projekt_zpo.entities.Item;
import com.projekt_zpo.repositories.CategoryRepository;
import com.projekt_zpo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ShopController {
    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ShopController(CategoryRepository categoryRepository,ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }


    @GetMapping("/shop")
    public String shopPage(@CookieValue(value = "username",required = false)String username,
                           Model model,
                           @RequestParam(value = "keyword",required = false)String keyword,
                           @RequestParam(value = "filterCategory",required = false)Integer filterCategory){

        List<Category> categories = categoryRepository.findAll();


        if((filterCategory!=null && (keyword != null && !keyword.isEmpty()))){
            List<Item> filtered_items = itemRepository.findAllByDescriptionContainsOrNameContainsAndCategoryId(keyword,keyword,filterCategory);
            model.addAttribute("items",filtered_items);
        }else if(filterCategory!=null){
            List<Item> filtered_items = itemRepository.findByCategoryId(filterCategory);
            model.addAttribute("items",filtered_items);
        }else if(keyword != null && !keyword.isEmpty()){
            List<Item> filtered_items = itemRepository.findAllByDescriptionContainsOrNameContains(keyword,keyword);
            model.addAttribute("items",filtered_items);
        }else {
            List<Item> items = itemRepository.findAll();
            model.addAttribute("items",items);
        }



        model.addAttribute("filterCategory",filterCategory);
        model.addAttribute("keyword",keyword);
        model.addAttribute("categories",categories);
        model.addAttribute("user",username);
        return "store";
    }


}

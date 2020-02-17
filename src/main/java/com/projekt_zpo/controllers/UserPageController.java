package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.Category;
import com.projekt_zpo.entities.Item;
import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.CategoryRepository;
import com.projekt_zpo.repositories.ItemRepository;
import com.projekt_zpo.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserPageController {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserPageController(UserRepository userRepository,ItemRepository itemRepository,CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/user_page")
    public String showUserPage(@CookieValue(value = "username",required = true)String username,
                               @RequestParam(value = "action", required = true) String action,
                               Model model,
                               HttpServletResponse response) {
        if(username==null || username.isEmpty()){
            return "redirect:/login2";
        }

        User user = userRepository.findByEmail(username);

        if(action.equals("products")){
            List<Item> items = itemRepository.findAllByUserId(user.getId());
            model.addAttribute("items",items);

        }else if(action.equals("account_info")){

        }else if(action.equals("logout")){
            //delete cookie
            Cookie cookie = new Cookie("username", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/shop";

        }else if(action.equals("add_product")){
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("item",new Item());
        }





        model.addAttribute("action",action);
        model.addAttribute("user", user);
        return "UserPage";

    }

    @GetMapping("/delete_item")
    public String deleteItem(@RequestParam(value="itemId")Integer itemId,
                             RedirectAttributes redirectAttributes){

        Item item = itemRepository.findItemById(itemId);
        itemRepository.delete(item);

        redirectAttributes.addAttribute("action","products");
        return "redirect:/user_page";
    }

    @PostMapping("/add_product")
    public String post(@ModelAttribute Item item,
                       @CookieValue(value = "username",required = false)String username,
                       Model model,
                       RedirectAttributes re){


        User user = userRepository.findByEmail(username);

        if (item.getNoOfVisits()==null){
            item.setNoOfVisits(0);
        }
        if(item.getUserId()==null){
            item.setUserId(user.getId());
        }



        if (item.getName().length() < 5||item.getName()==null) {
            System.out.println("Password too Short");

        } else if (item.getDescription().length()<5) {
            System.out.println("Bad Email");

        }  else {
            itemRepository.save(item);

        }

        re.addAttribute("action","products");
        return "redirect:/user_page";
    }
}

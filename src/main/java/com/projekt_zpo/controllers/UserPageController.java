package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserPageController {

    private final UserRepository userRepository;

    @Autowired
    public UserPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/user_page")
    public String showUserPage(@CookieValue(value = "username",required = true)String username,
                               @RequestParam(value = "action", required = true) String action,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               HttpServletResponse response) {
        if(username==null || username.isEmpty()){
            return "redirect:/login2";
        }

        User user = userRepository.findByEmail(username);

        if(action.equals("products")){

        }else if(action.equals("account_info")){

        }else if(action.equals("logout")){
            //delete cookie
            Cookie cookie = new Cookie("username", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/shop";

        }


        model.addAttribute("action",action);
        model.addAttribute("user", user);
        return "UserPage";

    }
}

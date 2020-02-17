package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final UserRepository userRepository;

    @Autowired
    public SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String email) {
        //System.out.println(email);
        User user = userRepository.findByEmail(email);
        System.out.println("GET USER: " + user);
        return user;
    }

    @GetMapping("/signup")
    public String get(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/create")
    public String post(@ModelAttribute User user, Model model){
        if (user.getPassword().length() < 8) { //TODO ustalić dł hasła, podać info w formularzu tworzenia
            System.out.println("Password too Short");
            return "redirect:/signup";
            //TODO komunikat na ekranie
        } else if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            System.out.println("Bad Email");
            //TODO komunikat na ekranie
            return "redirect:/signup";
        } else if (getUser(user.getEmail()) != null) {
            System.out.println(user.getEmail());
            System.out.println("Account with this Email already exists.");
            //TODO komunikat na ekranie
            return "redirect:/signup";
        } else {
            user.setFirstName(StringUtils.capitalize(user.getFirstName()));
            user.setLastName(StringUtils.capitalize(user.getLastName()));
            userRepository.save(user);
            model.addAttribute("getUser",user);
            return "redirect:/login2";
        }
    }
}

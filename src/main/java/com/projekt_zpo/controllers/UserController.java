package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils.*;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
/*
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
*/

    @GetMapping("/usertest")
    public User getUser() {
        User user = userRepository.findByEmail("kackul000@utp.edu.pl");
        System.out.println(user);
        return user;
    }

    @PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void newUser(User newUser) {
        // System.out.println(newUser.getEmail());
        //System.out.println(newUser.getLastName());
        if (newUser.getPassword().length() < 8) { //TODO ustalić dł hasła, podać info w formularzu tworzenia
            System.out.println("Password too Short");
            //TODO komunikat na ekranie
        } else if (!EmailValidator.getInstance().isValid(newUser.getEmail())) {
            System.out.println("Bad Email");
            //TODO komunikat na ekranie
        } else {
            newUser.setFirstName(StringUtils.capitalize(newUser.getFirstName()));
            newUser.setLastName(StringUtils.capitalize(newUser.getLastName()));
            userRepository.save(newUser);
        }
    }
    /*public User addUser(@RequestBody User user)
    {
        User newUser = new User();
        BeanUtils.copyProperties(user,newUser);
        System.out.println(newUser);
        //User newUser = new User("Adam","Adamcewicz","adamus@longboi.com","genericpassword","48345345345");
        newUser = userRepository.save(newUser);
        return newUser;
    }*/
}

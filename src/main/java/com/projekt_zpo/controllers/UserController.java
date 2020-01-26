package com.projekt_zpo.controllers;

import com.projekt_zpo.entities.User;
import com.projekt_zpo.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/get-user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User getUser(String email) {
        //System.out.println(email);
        User user = userRepository.findByEmail(email);
        System.out.println("GET USER: " + user);
        return user;
    }

    @RequestMapping ( method = RequestMethod.POST, value = "/create-user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    ResponseEntity newUser(User newUser) {
        //System.out.println(newUser.getLastName());
        //System.out.println(newUser.getEmail());
        //System.out.println(getUser(newUser.getEmail()).getEmail());
        ResponseEntity response;

        if (newUser.getPassword().length() < 8) { //TODO ustalić dł hasła, podać info w formularzu tworzenia
            System.out.println("Password too Short");
            response =  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            //TODO komunikat na ekranie
        } else if (!EmailValidator.getInstance().isValid(newUser.getEmail())) {
            System.out.println("Bad Email");
            //TODO komunikat na ekranie
            response =  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (getUser(newUser.getEmail()) != null) {
            System.out.println(newUser.getEmail());
            System.out.println("Account with this Email already exists.");
            response =  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //TODO komunikat na ekranie
        } else {
            newUser.setFirstName(StringUtils.capitalize(newUser.getFirstName()));
            newUser.setLastName(StringUtils.capitalize(newUser.getLastName()));
            userRepository.save(newUser);
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void login(User user) {
        User attempt = user;
        User validateAgainst = getUser(user.getEmail());
        //System.out.println(attempt.getEmail() + attempt.getPassword());
        //System.out.println(validateAgainst.getEmail() + validateAgainst.getPassword());
        if (attempt.getEmail().equals(validateAgainst.getEmail())) {
            if (attempt.getPassword().equals(validateAgainst.getPassword()) ) {
                System.out.println(attempt.getEmail() + " logged in with password " + attempt.getPassword());
                //TODO create a token/cookie
            }
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

package com.eshop.shopnow.controllers;

import com.eshop.shopnow.models.Users;
import com.eshop.shopnow.services.HashService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class AdminController {
    @RequestMapping(value = "/")
    public String defaultView(){
        return "index";
    }

    @RequestMapping(value = "/signup")
    public String signUp(){
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(HttpSession session,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password){

        Users users = new Users();
        users.setFirstname(firstName);
        users.setLastname(lastName);
        users.setUsername(username);

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String encodeSalt = Base64.getEncoder().encodeToString(salt);

        users.setSalt(encodeSalt);
        HashService hashService = new HashService();
        String hashPassword = hashService.getHashedValue(password, encodeSalt);

        users.setPassword(hashPassword);

        return "redirect:/signup?success";
    }

    @RequestMapping(value = "/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password
    ){


        //if (authenticationProvider.authorizeUser(username, password, userRepository)){

            //System.out.print(session.getId());
            //session.setAttribute("userid", userid);
            return "redirect:/home";
        //} else {
            //return "redirect:/login?error";
        //}

    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/home")
    public String homePage(){
        return "home";
    }

}


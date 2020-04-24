package com.eshop.shopnow.controllers;

import com.eshop.shopnow.mapper.AdminMapper;
import com.eshop.shopnow.mapper.ItemMapper;
import com.eshop.shopnow.mapper.UserMapper;
import com.eshop.shopnow.models.Admins;
import com.eshop.shopnow.models.Items;
import com.eshop.shopnow.models.Users;
import com.eshop.shopnow.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Controller
public class AdminController {

    @Autowired UserMapper userMapper;
    @Autowired AdminMapper adminMapper;
    @Autowired ItemMapper itemMapper;

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

        userMapper.createUser(users);

        return "redirect:/signup?success";
    }

    @RequestMapping(value = {"/admin", "/product"})
    public String adminPage(Model model){
        List<Items> allItems = itemMapper.findAllItems();
        model.addAttribute("items", allItems);
        return "admin";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        Admins admin = adminMapper.findByUsername(username);
        if(username.equals(admin.getUsername())) {
            return "redirect:/admin";
        } else {

            Users user = userMapper.findByUsername(username);

            HashService hashService = new HashService();
            String encoded = hashService.getHashedValue(password, user.getSalt());

            if (username.equals(user.getUsername()) && encoded.equals(user.getPassword())) {
                return "redirect:/home";
            } else {
                return "redirect:/login?error";
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/home")
    public String homePage(){
        return "home";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String addItem(Authentication authentication,
                          @RequestParam("itemName") String itemName,
                          @RequestParam("itemDescription") String itemDescription,
                          @RequestParam("itemPrice") Float itemPrice,
                          @RequestParam("imageUrl") String imageUrl){

        String adminName = authentication.getName();
        Admins admin = adminMapper.findByUsername(adminName);

        Items items = new Items();

        items.setAdminId(admin.getAdminid());
        items.setItemName(itemName);
        items.setItemDescription(itemDescription);
        items.setPrice(itemPrice);
        items.setImageUrl(imageUrl);

        itemMapper.createItem(items);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/cart")
    public String showCart(){
        return "cart";
    }

    @RequestMapping(value = "/cart/items", method = RequestMethod.POST)
    public String addToCart(){
        return "home";
    }

}


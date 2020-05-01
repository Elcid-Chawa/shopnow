package com.eshop.shopnow.controllers;

import com.eshop.shopnow.mapper.AdminMapper;
import com.eshop.shopnow.mapper.CartMapper;
import com.eshop.shopnow.mapper.ItemMapper;
import com.eshop.shopnow.mapper.UserMapper;
import com.eshop.shopnow.models.Admins;
import com.eshop.shopnow.models.Cart;
import com.eshop.shopnow.models.Items;
import com.eshop.shopnow.models.Users;
import com.eshop.shopnow.services.HashService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired UserMapper userMapper;
    @Autowired AdminMapper adminMapper;
    @Autowired ItemMapper itemMapper;
    @Autowired CartMapper cartMapper;

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
        users.setUSER_ROLE("USER_ROLE");

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
    public String homePage(Authentication authentication,
                           Model model){
        String username = authentication.getName();
        Users users = userMapper.findByUsername(username);

        List<Items> items = itemMapper.findAllItems();

        model.addAttribute("items", items);

        try {
            List<Cart> cartList = cartMapper.findCartByUserId(users.getUserId());
            model.addAttribute("cartCount", cartList.size());
        } catch (NullPointerException e){
            return "redirect:/";
        }

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

        items.setAdminid(admin.getAdminid());
        items.setItemName(itemName);
        items.setItemDescription(itemDescription);
        items.setPrice(itemPrice);
        items.setImageUrl(imageUrl);

        itemMapper.createItem(items);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit/item/", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editItem(Authentication authentication,
                           @RequestParam("itemId") String itemid,
                           @RequestParam("itemName") String itemName,
                           @RequestParam("itemDescription") String itemDescription,
                           @RequestParam("itemPrice") String price,
                           @RequestParam("imageUrl") String imageUrl){

        try {
            String adminName = authentication.getName();
            Admins admins = adminMapper.findByUsername(adminName);

            Integer itemId = Integer.parseInt(itemid);
            Float itemPrice = Float.parseFloat(price);

            Integer adminid = admins.getAdminid();

            Items item = itemMapper.findByItemId(itemId);

            item.setItemName(itemName);
            item.setItemDescription(itemDescription);
            item.setPrice(itemPrice);
            item.setImageUrl(imageUrl);
            item.setAdminid(adminid);

            itemMapper.updateItem(item);
        } catch (Exception e){return "redirect/:login?error";}


        return "redirect:/admin";
    }

    @RequestMapping(value = "/cart")
    public String showCart(Model model, Authentication authentication){
        String username = authentication.getName();
        Users user = userMapper.findByUsername(username);

        List<Cart> carts = cartMapper.findCartByUserId(user.getUserId());

        System.out.println(carts.size());
        List<Items> cartItem = new ArrayList<Items>();
        for (Cart cart : carts){

            cartItem.add(new Items("Product", "Description", Float.parseFloat("2.2"), "image") );
            cartItem.add(new Items("Product 2", "Description 2", Float.parseFloat("2.2"), "image") );
        }

        model.addAttribute("itemsCount",cartItem.size());

        model.addAttribute("items", cartItem);
        //model.addAttribute("carts");
        return "cart";
    }

    @RequestMapping(value = "/cart/items", method = RequestMethod.POST)
    public String addToCart(Authentication authentication,
                            Model model,
                            @RequestParam("itemid") Integer itemid,
                            @RequestParam("quantity") Integer quantity){
        String username = authentication.getName();
        Users users = userMapper.findByUsername(username);
        Items items = itemMapper.findByItemId(itemid);
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cart.setCartid(users.getUserId());
        cart.setUsers(users);
        cart.setItem(items);
        cart.setItemid(items.getItemid());

        cartMapper.createCart(cart);

        return "redirect:/home";
    }

}


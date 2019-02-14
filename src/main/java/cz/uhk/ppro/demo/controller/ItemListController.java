package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Service.ItemService;
import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ItemListController {

    private final ItemService itemService;
    private final UserService userService;
    private final MyAuthenticationProvider authentication;
    private final MyUserDetailService myUserDetailService;


    @Autowired
    public ItemListController(ItemService itemService, MyAuthenticationProvider myAuthenticationProvider, UserService userService, MyUserDetailService myUserDetailService) {
        this.itemService = itemService;
        this.authentication = myAuthenticationProvider;
        this.userService = userService;
        this.myUserDetailService = myUserDetailService;
    }

    @CrossOrigin
    @PostMapping(value = "/getItems")
    public List<Item> showList(@RequestBody ShoppingList shoppingList) {
        List<Item> is = itemService.findItems(shoppingList);
        return itemService.findItems(shoppingList);
    }
}

package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Service.ItemService;
import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.ShoppingListService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.security.MyAuthenticationProvider;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ShoppingListsController {

    private final ItemService itemService;
    private final UserService userService;
    private final MyAuthenticationProvider authentication;
    private final MyUserDetailService myUserDetailService;
    private final ShoppingListService shoppingListService;


    @Autowired
    public ShoppingListsController(ItemService itemService, MyAuthenticationProvider myAuthenticationProvider, UserService userService, MyUserDetailService myUserDetailService, ShoppingListService shoppingListService) {
        this.itemService = itemService;
        this.authentication = myAuthenticationProvider;
        this.userService = userService;
        this.myUserDetailService = myUserDetailService;
        this.shoppingListService = shoppingListService;
    }

    @CrossOrigin
    @GetMapping(value = "/getLists")
    public List<ShoppingList> showList(HttpServletRequest request) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userService.findByUsername(myUserPrincipal.getUsername());
            if (user.isPresent()) {
                return shoppingListService.findItemsByUserId(user.get());
            }
        }
        // TODO better handling
        return new ArrayList(Arrays.asList(new ShoppingList()));
    }

    @CrossOrigin
    @PostMapping(value = "/saveList")
    public ShoppingList saveList(@RequestBody List<Item> items) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userService.findByUsername(myUserPrincipal.getUsername());
            if (user.isPresent()) {
                List<Item> itemList = new ArrayList<>();
                ShoppingList shoppingList = new ShoppingList();
                shoppingList.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                shoppingList.setUser(user.get());
                shoppingListService.saveItem(shoppingList);
                for (Item item : items) {
                    Optional<Item> dbItem = itemService.findItem(item);
                    if (dbItem.isPresent()) {
                        dbItem.get().setShoppingList(shoppingList);
                        itemService.saveItem(dbItem.get());
                        itemList.add(dbItem.get());
                    }
                }
                shoppingList.setItems(itemList);
                shoppingListService.saveItem(shoppingList);
                return shoppingList;
            }
        }
        return new ShoppingList();
    }


}

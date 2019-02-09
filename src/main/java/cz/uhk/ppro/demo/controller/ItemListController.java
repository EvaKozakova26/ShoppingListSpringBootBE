package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Service.ItemService;
import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<Item> showList(HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            request.getUserPrincipal();
            //   MyUserPrincipal ss = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }

        return itemService.findItems();
    }
}

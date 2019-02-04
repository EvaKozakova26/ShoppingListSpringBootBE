package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.dto.UserDto;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MyUserPrincipal loginUser(@RequestBody UserDto userDto) {
        MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());
        //TODO vracet jen dto bez hesla?
        if (currentUser == null) {
            return userService.createNewUser(userDto);
        } else {
            return currentUser;
        }
    }
}

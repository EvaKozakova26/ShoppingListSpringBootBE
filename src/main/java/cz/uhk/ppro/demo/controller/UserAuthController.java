package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.dto.UserDto;
import cz.uhk.ppro.demo.security.MyAuthenticationRequest;
import cz.uhk.ppro.demo.security.MyUserAuth;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class UserAuthController {

    private final UserService userService;

    private final MyUserDetailService myUserDetailService;


    @Autowired
    public UserAuthController(UserService userService, MyUserDetailService myUserDetailService) {
        this.userService = userService;
        this.myUserDetailService = myUserDetailService;
    }


    @CrossOrigin
    @RequestMapping(value = "/loginn", method = RequestMethod.POST)
    public UserDto loginUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());

        SecurityContextHolder.getContext().setAuthentication(
                new MyAuthenticationRequest(currentUser.getUsername()));

        UserDto user = new UserDto();
        user.setName(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        MyUserAuth myUserAuth = new MyUserAuth(currentUser);
        SecurityContextHolder.getContext().setAuthentication(myUserAuth);

        MyUserPrincipal ss = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MyUserPrincipal registerUser(@RequestBody UserDto userDto) {
        //  MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());
        //TODO vracet jen dto bez hesla?
        //if (currentUser == null) {
        return userService.createNewUser(userDto);
        //  } else {
        //      return currentUser;
        //  }
    }
}

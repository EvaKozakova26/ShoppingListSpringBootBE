package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.dto.UserDto;
import cz.uhk.ppro.demo.security.MyAuthenticationRequest;
import cz.uhk.ppro.demo.security.MyUserAuth;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/loginn")
    public UserDto loginUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());

        SecurityContextHolder.getContext().setAuthentication(
                new MyAuthenticationRequest(currentUser.getUsername()));

        UserDto user = new UserDto();
        user.setName(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        MyUserAuth myUserAuth = new MyUserAuth(currentUser);
        SecurityContextHolder.getContext().setAuthentication(myUserAuth);

        return user;
    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        //  MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());
        //TODO vracet jen dto bez hesla?
        //if (currentUser == null) {
        MyUserPrincipal currentUser = userService.createNewUser(userDto);
        UserDto user = new UserDto();
        user.setName(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        return user;
        //  } else {
        //      return currentUser;
        //  }
    }

    @CrossOrigin
    @PostMapping(value = "/logoutt")
    public MyUserPrincipal logoutUser() {
        SecurityContextHolder.clearContext();
        //TODO better handling
        return new MyUserPrincipal(new User());
    }
}

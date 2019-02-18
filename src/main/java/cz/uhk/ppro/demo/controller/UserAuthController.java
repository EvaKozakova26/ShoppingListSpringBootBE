package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Service.MyUserDetailService;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.dto.UserDto;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserAuthController(UserService userService, MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    @CrossOrigin
    @PostMapping(value = "/loginn")
    public UserDto loginUser(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        MyUserPrincipal currentUser = (MyUserPrincipal) myUserDetailService.loadUserByUsername(userDto.getName());

        if (passwordEncoder.matches(userDto.getPassword(), currentUser.getPassword())) {
            UserDto user = new UserDto();
            user.setName(currentUser.getUsername());
            user.setPassword(currentUser.getPassword());
            Authentication a = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);

            return user;
        }

        System.out.println("wrong password");
        return new UserDto();

    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        // TODO - rovnou login?
        MyUserPrincipal currentUser = userService.createNewUser(userDto);
        UserDto user = new UserDto();
        user.setName(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        return user;
    }

    @CrossOrigin
    @PostMapping(value = "/logoutt")
    public MyUserPrincipal logoutUser() {
        SecurityContextHolder.clearContext();
        //TODO better handling
        return new MyUserPrincipal(new User());
    }
}

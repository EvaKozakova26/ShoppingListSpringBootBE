package cz.uhk.ppro.demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthenticationRequest authRequest =
                (MyAuthenticationRequest) authentication;

        // some logic here: perhaps loading user from db

        return new MyUserAuth((UserDetails) authentication.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MyAuthenticationRequest.class.equals(authentication);

    }
}


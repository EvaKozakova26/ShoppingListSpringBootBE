package cz.uhk.ppro.demo.Service;


import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Repository.UserRepository;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public MyUserDetailService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        return new MyUserPrincipal(optionalUser.get());
    }
}

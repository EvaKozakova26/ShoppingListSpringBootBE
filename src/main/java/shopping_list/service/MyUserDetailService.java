package shopping_list.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shopping_list.model.User;
import shopping_list.repository.UserRepository;
import shopping_list.security.MyUserPrincipal;

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

        return optionalUser.map(MyUserPrincipal::new).get();

    }
}

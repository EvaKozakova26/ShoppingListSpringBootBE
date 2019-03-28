package shopping_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shopping_list.dto.UserDto;
import shopping_list.model.User;
import shopping_list.repository.RoleRepository;
import shopping_list.repository.UserRepository;
import shopping_list.security.MyUserPrincipal;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public MyUserPrincipal createNewUser(@Valid UserDto userDto) throws DataAccessException {
        User u = userRepository.findByName(userDto.getName());
        if (u != null) return new MyUserPrincipal(u);

        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(roleRepository.findByName("user"));
        userRepository.save(user);
        return new MyUserPrincipal(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

}

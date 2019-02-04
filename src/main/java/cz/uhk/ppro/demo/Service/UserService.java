package cz.uhk.ppro.demo.Service;

import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Repository.UserRepository;
import cz.uhk.ppro.demo.dto.UserDto;
import cz.uhk.ppro.demo.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MyUserPrincipal createNewUser(@Valid UserDto userDto) throws DataAccessException {
        Optional<User> u = userRepository.findByUsername(userDto.getName());
        if (u.isPresent()) return new MyUserPrincipal(u.get());

        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return new MyUserPrincipal(user);
    }

}

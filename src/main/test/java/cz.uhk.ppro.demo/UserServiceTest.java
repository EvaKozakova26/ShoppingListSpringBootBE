package cz.uhk.ppro.demo;

import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Repository.UserRepository;
import cz.uhk.ppro.demo.Service.UserService;
import cz.uhk.ppro.demo.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void createItemTest() {

        UserDto userDto = new UserDto();
        userDto.setPassword("abcdef");
        userDto.setName("test");
        userService.createNewUser(userDto);

        Optional<User> user = userService.findByUsername("test");
        Assert.assertEquals(user.get().getName(), userDto.getName());

    }


}

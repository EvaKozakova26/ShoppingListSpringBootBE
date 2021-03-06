package shopping_list;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shopping_list.dto.UserDto;
import shopping_list.model.User;
import shopping_list.repository.UserRepository;
import shopping_list.service.UserService;

import javax.transaction.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void createUserTest() {

        UserDto userDto = new UserDto();
        userDto.setPassword("abcdef");
        userDto.setName("test");
        userService.createNewUser(userDto);

        User user = userService.findByUsername("test");
        Assert.assertEquals(user.getName(), userDto.getName());

    }


}

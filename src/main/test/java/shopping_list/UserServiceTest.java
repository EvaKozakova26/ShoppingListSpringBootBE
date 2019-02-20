package shopping_list;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shopping_list.Model.User;
import shopping_list.Repository.UserRepository;
import shopping_list.Service.UserService;
import shopping_list.dto.UserDto;

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

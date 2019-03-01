package shopping_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import shopping_list.configuration.WebConfig;
import shopping_list.configuration.WebSecurityConfig;

@SpringBootApplication
@EnableCaching
@Import({WebSecurityConfig.class, WebConfig.class})
public class ShoppingListApplication {

	public static void main(String[] args) {
        SpringApplication.run(ShoppingListApplication.class, args);
	}

}

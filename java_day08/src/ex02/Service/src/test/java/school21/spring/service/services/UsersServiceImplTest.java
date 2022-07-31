package school21.spring.service.services;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersServiceImplTest {

    @Test
    void signUpTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersServiceImpl usersService = context.getBean(UsersServiceImpl.class);

        assertNotNull(usersService.signUp("max@yandex.ru"));
    }
}

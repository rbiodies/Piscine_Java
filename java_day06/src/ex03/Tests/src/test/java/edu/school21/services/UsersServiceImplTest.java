package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersServiceImplTest {

    @Mock
    UsersRepository usersRepositoryMock;
    UsersServiceImpl usersService;

    public UsersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.usersService = new UsersServiceImpl(usersRepositoryMock);
    }

    @Test
    void testAuthenticateTrue() {
        User user = new User(1L, "user69", "qwerty", false);
        User userTrue = new User(1L, "user69", "qwerty", true);

        Mockito.when(usersRepositoryMock.findByLogin("user69")).thenReturn(user);
        assertTrue(usersService.authenticate(user.getLogin(), user.getPassword()));
        Mockito.verify(usersRepositoryMock).update(userTrue);
    }

    @Test
    void testAuthenticateFalseForLogin() {
        User user = new User(1L, "user69", "qwerty", false);

        Mockito.when(usersRepositoryMock.findByLogin(user.getLogin())).thenReturn(user);
        assertFalse(usersService.authenticate("terminator21", user.getPassword()));
    }

    @Test
    void testAuthenticateFalseForPassword() {
        User user = new User(1L, "user69", "qwerty", false);

        Mockito.when(usersRepositoryMock.findByLogin(user.getLogin())).thenReturn(user);
        assertFalse(usersService.authenticate(user.getLogin(), "111111"));
    }
}

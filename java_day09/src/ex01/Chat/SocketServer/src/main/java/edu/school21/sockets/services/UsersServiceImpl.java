package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UsersService {
    PasswordEncoder passwordEncoder;
    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryImpl") UsersRepository usersRepository, @Qualifier("encoder") PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUp(String username, String password) {
        if (usersRepository.findByUsername(username).isPresent()) {
            return false;
        } else {
            User user = new User(username, passwordEncoder.encode(password));

            usersRepository.save(user);

            return true;
        }
    }

    @Override
    public User signIn(String username, String password) {
        if (usersRepository.findByUsername(username).isPresent()) {
            User user = usersRepository.findByUsername(username).get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

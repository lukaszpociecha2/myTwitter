package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveUser(User user) {
        System.out.println(user.getEmail());
        if (isEmailUnique(user.getEmail())) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } else return false;
    }

    public User verify(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        } else {
            return null;
        }
    }

    private boolean isEmailUnique(String email) {
        return !userRepository.findUserByEmail(email).isPresent();

    }

    @Transactional
    public boolean editUser(User user) {
        if (isEmailUnique(user.getEmail()) || user.getId().equals(userRepository.findUserByEmail(user.getEmail()).get().getId())) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            User oldUser = userRepository.getOne(user.getId());
            oldUser.setPassword(password);
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setEmail(user.getEmail());
            return true;
        } else {
            return false;
        }
    }
}

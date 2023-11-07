package com.jsh.newsp.service;

import com.jsh.newsp.entity.Employee;
import com.jsh.newsp.entity.User;
import com.jsh.newsp.exception.EmployeeNotFoundException;
import com.jsh.newsp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    public User getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    public User saveUser(User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EmployeeNotFoundException(id, User.class);
    }


}

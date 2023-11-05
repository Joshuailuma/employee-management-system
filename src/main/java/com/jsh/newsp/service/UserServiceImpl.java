package com.jsh.newsp.service;

import com.jsh.newsp.entity.User;
import com.jsh.newsp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserById(Long id) {
       // Optional<User> user = userRepository.findById(id);
        //return unwrapUser(user, id);
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public User saveUser(User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }


}

package com.jsh.newsp.service;

import com.jsh.newsp.entity.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    User saveUser(User user);
}

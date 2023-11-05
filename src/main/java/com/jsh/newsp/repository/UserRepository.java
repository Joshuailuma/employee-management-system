package com.jsh.newsp.repository;

import org.springframework.data.repository.CrudRepository;
import com.jsh.newsp.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

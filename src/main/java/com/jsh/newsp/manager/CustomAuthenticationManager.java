package com.jsh.newsp.manager;

import com.jsh.newsp.entity.User;
import com.jsh.newsp.service.UserService;
import com.jsh.newsp.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
/ This class will check if the username and password passed in by the user(Authentication filter)
 is correct
 */
@Component
@AllArgsConstructor// Make the class a bean
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the username
        User user = userService.getUserByUsername(authentication.getName());
        // Get the password and hash it for comparison with the one in the database
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            //If there isn't a match, throw an error
            throw new BadCredentialsException("You provided an incorrect password");

        }
        //If it is correct
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }
}

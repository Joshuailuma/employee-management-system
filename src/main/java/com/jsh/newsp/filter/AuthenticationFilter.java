package com.jsh.newsp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsh.newsp.Constants;
import com.jsh.newsp.entity.User;
import com.jsh.newsp.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor //Autowire bean inside here
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private CustomAuthenticationManager authenticationManager;

    // WIll be invoked when login is invoked
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Get the username and password from the body and deserialize it into a Java object.
        // Map the data of our post request to an object of type user
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // Creating an object of authentication passing in the username and password
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            // Pass this into the authentication manager
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // When authentication is not successful, this will get invoked
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //Get the failed message from the authentication
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    // WHen authentication is successful, this will get invoked
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create().withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 720000))
                .sign(Algorithm.HMAC512(Constants.SECURITY_KEY));
        response.addHeader("Authorization", "Bearer" + token);
    }
}

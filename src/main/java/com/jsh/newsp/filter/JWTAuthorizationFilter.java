package com.jsh.newsp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jsh.newsp.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class validates the JWT token the user has provided to enusre it hasn't
 * been tampered with and then proceed to authorizing the relevant pages
 */

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    // The Header will be like this Authorization: Bearer JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization"); // This will return the header which include the Bearer and JWT...
        // If the user doesn't have a header/ registering for the first time, allow the...
        //...call the resource he intends in the controller/ register resource
        if(header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        // ...but we don't need the bearer
        // Replace the string "Bearer" with nothing to be left with the token
        String token = header.replace("Bearer ", "");
        //Check if the token is the same from the one that was initially produced...
        // using same algorithm and key that was used to sign it
        String user = JWT.require(Algorithm.HMAC512(Constants.SECURITY_KEY)).build().verify(token)
                .getSubject();// THis...
        //...will return the decoded JWT, and we can get the username uusing get subject
        // Store the authentication object in spring security context. This will set the authentication field to true
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response); // Perform the action the user require in the controller
    }
}

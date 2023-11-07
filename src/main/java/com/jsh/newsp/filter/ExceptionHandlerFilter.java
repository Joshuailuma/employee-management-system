package com.jsh.newsp.filter;

import com.jsh.newsp.exception.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
/The aim of this class is to throw a bad request(403) if a user sends a wrong json field..
instead of 403
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // try to run the next filter(Authentication filter) that has been specified
        // in security.config
        try {
            filterChain.doFilter(request, response);
        }catch (UserNotFoundException e){
            //WHen trying to authenticate a user that doesn't exist
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();

        }
        catch (RuntimeException e){
            // WHen it is a run time exception
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("That user doesn't exist");
            response.getWriter().flush();

        }
    }
}

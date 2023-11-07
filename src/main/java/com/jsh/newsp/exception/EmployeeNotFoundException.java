package com.jsh.newsp.exception;

import com.jsh.newsp.entity.User;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Long id, Class<User> userClass) {
        super("The employee id '" + id + "' does not exist");
    }
}

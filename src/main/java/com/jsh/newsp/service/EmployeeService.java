package com.jsh.newsp.service;

import com.jsh.newsp.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);
    Employee saveEmployee(Employee employee);
    List<Employee> getEmployees();

}

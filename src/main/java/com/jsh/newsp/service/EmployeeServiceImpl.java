package com.jsh.newsp.service;

import com.jsh.newsp.entity.Employee;
import com.jsh.newsp.entity.User;
import com.jsh.newsp.exception.EmployeeNotFoundException;
import com.jsh.newsp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return unwrapEmployee(employee, id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>)employeeRepository.findAll();
    }

    static Employee unwrapEmployee(Optional<Employee> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EmployeeNotFoundException(id, User.class);
    }
}

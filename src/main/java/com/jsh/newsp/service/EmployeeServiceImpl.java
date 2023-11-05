package com.jsh.newsp.service;

import com.jsh.newsp.entity.Employee;
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
        Optional<Employee> student = employeeRepository.findById(id);

        //Optional<Employee> student = employeeRepository.findById(id);
        //return unwrapStudent(student, id);
        return student.get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>)employeeRepository.findAll();
    }
}

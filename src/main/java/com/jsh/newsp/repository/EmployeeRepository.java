package com.jsh.newsp.repository;

import com.jsh.newsp.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}

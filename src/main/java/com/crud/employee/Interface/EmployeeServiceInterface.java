package com.crud.employee.Interface;


import com.crud.employee.Entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee addEmployee(Employee employee);
    Employee getEmployee(Long id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeeList();
    Employee updateEmployee(Long id, Employee updatedEmployee);
    ResponseEntity<?> deleteEmployee(Long id);
}

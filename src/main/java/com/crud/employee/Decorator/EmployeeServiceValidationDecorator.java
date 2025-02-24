package com.crud.employee.Decorator;

import com.crud.employee.Entity.Employee;
import com.crud.employee.Exception.BadRequestException;
import com.crud.employee.Interface.EmployeeServiceDecoratorInterface;
import com.crud.employee.Interface.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceValidationDecorator implements EmployeeServiceDecoratorInterface {
    private final EmployeeServiceInterface decoratedEmployeeService;

    @Autowired
    public EmployeeServiceValidationDecorator(EmployeeServiceInterface decoratedEmployeeService){
        this.decoratedEmployeeService = decoratedEmployeeService;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        validateEmployee(employee);
        return decoratedEmployeeService.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        validateEmployeeId(id);
        return decoratedEmployeeService.getEmployee(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        validateEmail(email);
        return decoratedEmployeeService.getEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return decoratedEmployeeService.getEmployeeList();
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        validateEmployeeId(id);
        validateEmployee(updatedEmployee);
        return decoratedEmployeeService.updateEmployee(id, updatedEmployee);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(Long id) {
        validateEmployeeId(id);
        return decoratedEmployeeService.deleteEmployee(id);
    }

    private void validateEmployee(Employee employee) {
        if (employee.getEmail() == null || !employee.getEmail().contains("@")) {
            throw new BadRequestException("Invalid email address");
        }

    }

    private void validateEmployeeId(Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("Invalid employee ID");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new BadRequestException("Invalid email address");
        }
    }

}

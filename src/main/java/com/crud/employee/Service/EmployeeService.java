package com.crud.employee.Service;

import com.crud.employee.Entity.Employee;
import com.crud.employee.Exception.BadRequestException;
import com.crud.employee.Exception.InternalServerErrorException;
import com.crud.employee.Exception.NotFoundException;
import com.crud.employee.Interface.EmployeeServiceInterface;
import com.crud.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee){
        try{
            return employeeRepository.save(employee);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException("Some Internal Error Occurred");
        }
    }

    // get by id
    @Override
    public Employee getEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()) throw new NotFoundException("Employee not found");
        return employee.get();
    }

    //get by email
    @Override
    public Employee getEmployeeByEmail(String email){
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(email);
        if(employee.isEmpty()) throw new NotFoundException("Employee not found");
        return employee.get();
    }

    //get complete list
    @Override
    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException("Employee does not exist");
        }

        Employee oldEmployee = employeeOptional.get();
        oldEmployee.setDepartment(updatedEmployee.getDepartment());
        oldEmployee.setEmail(updatedEmployee.getEmail());
        oldEmployee.setFirstname(updatedEmployee.getFirstname());
        oldEmployee.setLastname(updatedEmployee.getLastname());

        return employeeRepository.save(oldEmployee);
    }
    @Override
    public ResponseEntity<?> deleteEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
        Map<String,Object> response = new HashMap<>();
        response.put("success",true);
        return ResponseEntity.ok(response);
    }
}

package com.crud.employee.Controller;

import com.crud.employee.Entity.Employee;
import com.crud.employee.Interface.EmployeeServiceInterface;
import com.crud.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInterface employeeService;

    @PostMapping("")
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("all")
    public List<Employee> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee){
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

}

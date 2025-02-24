package com.crud.employee.Configuration;

import com.crud.employee.Decorator.EmployeeServiceValidationDecorator;
import com.crud.employee.Interface.EmployeeServiceInterface;
import com.crud.employee.Service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EmployeeServiceConfig {
    @Bean
    @Primary
    public EmployeeServiceInterface decoratedEmployeeService(EmployeeService employeeService){
        return new EmployeeServiceValidationDecorator(employeeService);
    }
}

package com.crud.employee.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class Health {
    @GetMapping
    public String heathResponse(){
        return "Pong";
    }
}

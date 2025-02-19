package com.crud.employee;

import com.crud.employee.Entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setup(){
        employee = new Employee();
        employee.setFirstname("Akash");
        employee.setLastname("Sharma");
        employee.setEmail("akash@gmail.com");
        employee.setDepartment("BTPD");
    }


    @Test
    @Order(1)
    void testCreateEmployee() throws Exception{
        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Akash"))
                .andExpect(jsonPath("$.lastname").value("Sharma"))
                .andExpect(jsonPath("$.email").value("akash@gmail.com"))
                .andExpect(jsonPath("$.department").value("BTPD"));
    }

    @Test
    @Order(2)
    void testGetEmployeeById() throws Exception{
        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    void testGetAllEmployees() throws Exception{
        mockMvc.perform(get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    void testUpdateEmployee() throws Exception{
        Employee updatedEmployee = new Employee();
        updatedEmployee.setFirstname("Hello");
        updatedEmployee.setLastname("World");
        updatedEmployee.setDepartment("CXO");
        updatedEmployee.setEmail("cxo@mail.com");

        mockMvc.perform(put("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Hello"))
                .andExpect(jsonPath("$.lastname").value("World"))
                .andExpect(jsonPath("$.email").value("cxo@mail.com"))
                .andExpect(jsonPath("$.department").value("CXO"));
    }



    @Test
    @Order(5)
    void testDeleteEmployee() throws Exception{
        mockMvc.perform(delete("/employee/1"))
                .andExpect(status().isOk());
    }
}

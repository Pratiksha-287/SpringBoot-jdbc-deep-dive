package com.example.jdbctemplate.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.jdbctemplate.entity.Employee;
import com.example.jdbctemplate.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/post")
    public int saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/get")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }
    @PutMapping("/updateAll/{id}")
    public int updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        int count = employeeService.updateEmployee(id,employee);
        if(count!=0) {
            return 1;
        }
        return 0;
    }
    @DeleteMapping("/delete/{id}")
    public int deleteEmployee(@PathVariable Long id){
        int count=employeeService.deleteEmployee(id);
        if(count!=0) {
            return 1;
        }
        return 0;

    }
    @PatchMapping("/update/{id}")
    public int updatePatchEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.updatePatchEmployee(id, employee);
    }
    
    
}

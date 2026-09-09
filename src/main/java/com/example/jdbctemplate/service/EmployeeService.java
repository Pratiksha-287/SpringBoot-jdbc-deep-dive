package com.example.jdbctemplate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jdbctemplate.entity.Employee;
import com.example.jdbctemplate.repository.EmployeeRepository;
import com.example.jdbctemplate.repository.EmployeeRepository2;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeRepository2 employeeRepository2;
    public EmployeeService(EmployeeRepository employeeRepository,EmployeeRepository2 employeeRepository2) {
        this.employeeRepository = employeeRepository;
        this.employeeRepository2 = employeeRepository2;
    }
    public int saveEmployee(Employee employee) {
        System.out.println("save service");
        return employeeRepository2.saveEmployee(employee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository2.getAllEmployee();
    }
    public Employee getById(Long id) {
        return employeeRepository2.getById(id);
    }
    public int updateEmployee(Long id, Employee employee) {
        return employeeRepository2.updateEmployee(employee,id);
    }
    public int deleteEmployee(Long id) {
        return employeeRepository2.deleteEmployee(id);
    }
    public int updatePatchEmployee(Long id, Employee employee) {
        return employeeRepository2.updatePatchEmployee(employee, id);
    }

}

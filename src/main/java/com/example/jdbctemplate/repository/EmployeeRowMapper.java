package com.example.jdbctemplate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.jdbctemplate.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp=new Employee();
        emp.setId(rs.getLong("id"));
        emp.setName(rs.getString("name"));
        emp.setDepartment(rs.getString("department"));
        emp.setSalary(rs.getDouble("salary"));
        return emp;
    }
}

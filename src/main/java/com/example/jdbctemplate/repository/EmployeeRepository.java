package com.example.jdbctemplate.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.jdbctemplate.entity.Employee;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public EmployeeRepository( @Qualifier("mySQLJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int saveEmployee(Employee employee) {
        // String sql = "insert into employee(name, department, salary) values (?,?,?)";
        return jdbcTemplate.update("insert into employee(name, department, salary) values (?,?,?)",employee.getName(),employee.getDepartment(),employee.getSalary());
    }
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
    }
    public Employee getById(Long id) {
        return jdbcTemplate.queryForObject("select * from employee where id=?", new EmployeeRowMapper(),id);
    }
    public int updateEmployee(Employee employee, Long id) {
        String sql="update employee set name=?, department=?,salary=? where id=?";
        return jdbcTemplate.update(sql,employee.getName(),employee.getDepartment(),employee.getSalary(),id);
    }
    public int deleteEmployee(Long id) {
        return jdbcTemplate.update("delete from employee where id=?",id);
    }
    public int updatePatchEmployee(Long id, Employee employee) {
        StringBuilder sql=new StringBuilder("update employee set ");
        List<Object> param=new ArrayList<>();
        if(employee.getSalary()!=null){
            sql.append("salary=?,");
            param.add(employee.getSalary());
        }
        if(employee.getName()!=null){
            sql.append("name=?,");
            param.add(employee.getName());
        }
        if(employee.getDepartment()!=null){
            sql.append("department=?,");
            param.add(employee.getDepartment());
        }
        param.add(id);
        sql.setLength(sql.length()-1);
        sql.append(" where id=?");
        return jdbcTemplate.update(sql.toString(),param.toArray());
       
    
     
    }
    
}

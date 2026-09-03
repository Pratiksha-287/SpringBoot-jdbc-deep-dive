package com.example.jdbctemplate.repository;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.jdbctemplate.entity.Employee;

@Repository
public class EmployeeRepository2 {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public EmployeeRepository2(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public int saveEmployee(Employee employee){
        MapSqlParameterSource m=new MapSqlParameterSource();
        m.addValue("name", employee.getName());
        m.addValue("department", employee.getDepartment());
        m.addValue("salary", employee.getSalary());
        return namedParameterJdbcTemplate.update("insert into employee(name, department, salary) values(:name,:department,:salary)", m);
    }
    public List<Employee> getAllEmployee(){
        return namedParameterJdbcTemplate.query("select id, name, department, salary from employee", new MapSqlParameterSource(),new EmployeeRowMapper());
    }
    public Employee getById(Long id){
        return namedParameterJdbcTemplate.queryForObject("select id, name, department, salary from employee where id=:id", new MapSqlParameterSource("id",id),new EmployeeRowMapper());
    }
    public int updateEmployee(Employee employee, Long id){
        MapSqlParameterSource m=new MapSqlParameterSource()
                                    .addValue("name", employee.getName())
                                    .addValue("department",employee.getDepartment())
                                    .addValue("salary", employee.getSalary())
                                    .addValue("id",id);
        return namedParameterJdbcTemplate.update("update employee set name=:name, department=:department, salary=:salary where id=:id",m);
    }
    public int updatePatchEmployee(Employee employee, Long id){
        StringBuilder sql=new StringBuilder("update employee set ");
        MapSqlParameterSource m=new MapSqlParameterSource();
        if(employee.getName()!=null){
            sql.append("name=:name,");
            m.addValue("name", employee.getName());
        }
        if(employee.getDepartment()!=null){
            sql.append("department=:department,");
            m.addValue("department", employee.getDepartment());
        }
        if(employee.getSalary()!=null){
            sql.append("salary=:salary,");
            m.addValue("salary", employee.getSalary());
        }
        sql.setLength(sql.length()-1);
        sql.append(" where id=:id");
        m.addValue("id", id);

        return namedParameterJdbcTemplate.update(sql.toString(),m);
    }

    public int deleteEmployee(Long id){
        return namedParameterJdbcTemplate.update("delete from employee where id=:id", new MapSqlParameterSource("id",id));
    }
}

package com.myproject.crudapp.DAO;

import com.myproject.crudapp.Model.Employee;

import java.util.List;

public interface EmployeeDAO {

    void insertEmployee(Employee e);
    void updateEmployee(Employee e);
    void deleteEmployee(int id);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();


}

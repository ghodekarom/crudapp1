package com.myproject.crudapp.DAO;

import com.myproject.crudapp.Model.Employee;
import com.myproject.crudapp.Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO{

    private static final String INSERT_SQL = "insert into employee(name,dept,salary) values(?,?,?)";
    private static final String UPDATE_SQL = "update employee set name=?,dept=?,salary=? where id=?";
    private static final String DELETE_SQL = "delete from employee where id=?";
    private static final String SEARCH_SQL = "select * from employee where id=?";
    private static final String DISPLAY_SQL = "select * from employee";


    @Override
    public void insertEmployee(Employee e) {

        try(Connection c = DBConnect.fetchConnection();
        PreparedStatement psinsert = c.prepareStatement(INSERT_SQL)){


            psinsert.setString(1,e.getName());
            psinsert.setString(2,e.getDept());
            psinsert.setDouble(3,e.getSalary());

            psinsert.executeUpdate();

        }catch(SQLException se){
            throw new EmployeeDAOException("error during inserting record"+se.getMessage(),se);
        }
    }

    @Override
    public void updateEmployee(Employee e) {

        try(Connection c = DBConnect.fetchConnection();
        PreparedStatement psupdate = c.prepareStatement("update employee set name=?,dept=?,salary=? where id=?")){

           psupdate.setString(1,e.getName());
           psupdate.setString(2,e.getDept());
           psupdate.setDouble(3,e.getSalary());
           psupdate.setInt(4,e.getId());

            psupdate.executeUpdate();

        }catch(SQLException se){
            throw new EmployeeDAOException("error during updating record"+se.getMessage(),se);
        }

    }

    @Override
    public void deleteEmployee(int id) {

        try(Connection c = DBConnect.fetchConnection();
        PreparedStatement psdelete = c.prepareStatement(DELETE_SQL)){

            psdelete.setInt(1,id);
            psdelete.executeUpdate();


        }catch(SQLException se){
            throw new EmployeeDAOException("error during deleting record"+se.getMessage(),se);
        }

    }

    @Override
    public Employee getEmployeeById(int id) {

        Employee e = null;
        ResultSet rs = null;
        try(Connection c = DBConnect.fetchConnection();
            PreparedStatement pssearch = c.prepareStatement(SEARCH_SQL)){

            pssearch.setInt(1,id);
            rs = pssearch.executeQuery();
            if(rs.isBeforeFirst()){
                rs.next();
                e = new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("dept"),rs.getDouble("salary"));
            }

        }catch(SQLException se){
            throw new EmployeeDAOException("error during searching record"+se.getMessage(),se);
        }finally{
            if(rs!=null){
                try
                {
                    rs.close();
                }catch(SQLException se){
                    System.err.println("error during closing ResultSet"+se.getMessage());
                }
            }
        }
        return e;
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<Employee>();
        ResultSet rs = null;

        try(Connection c = DBConnect.fetchConnection();
        PreparedStatement psdisplay = c.prepareStatement(DISPLAY_SQL)){

            rs = psdisplay.executeQuery();
            while(rs.next()){
                list.add(new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("dept"),rs.getDouble("salary")));
            }

        }catch(SQLException se){
            throw new EmployeeDAOException("error during displaying records"+se.getMessage(),se);

        }
        return list;
    }
}

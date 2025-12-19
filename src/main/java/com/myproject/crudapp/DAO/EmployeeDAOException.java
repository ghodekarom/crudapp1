package com.myproject.crudapp.DAO;

public class EmployeeDAOException extends RuntimeException {
    public EmployeeDAOException(String message) {
        super(message);
    }
    public EmployeeDAOException(String message,Throwable cause){
        super(message,cause);
    }
}

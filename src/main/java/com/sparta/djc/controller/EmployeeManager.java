package com.sparta.djc.controller;

import com.sparta.djc.model.Employee;

import java.util.Map;

public class EmployeeManager {

    public void readWriteEmployees(){
        EmployeeFileReader employeeFileReader = new EmployeeFileReader();
        DAO dao = new DAO();
        Map<String, Employee> employees=employeeFileReader.readEmployees("resources/EmployeeRecords1.csv");
        dao.addEmployeesToDatabase(employees);
    }

}

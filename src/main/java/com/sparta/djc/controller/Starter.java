package com.sparta.djc.controller;

import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;


import java.util.Map;

/**
 * Hello world!
 *
 */
public class Starter {

    public static void main( String[] args ) {

        EmployeeFileReader employeeFileReader = new EmployeeFileReader();
        DAO dao = new DAO();
        Map<String, Employee> employees=employeeFileReader.readEmployees("resources/EmployeeRecords.csv");
        dao.addEmployeesToDatabase(employees);
    }
}

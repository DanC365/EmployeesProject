package com.sparta.djc.controller;

import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Starter
{
    public static void main( String[] args )
    {
        EmployeeFileReader employeeFileReader = new EmployeeFileReader();
        DAO dao = new DAO();
        Map<Long, Employee> employees=employeeFileReader.readEmployees("resources/EmployeeRecords1.csv");
        dao.runSQLQuery(employees);


    }
}

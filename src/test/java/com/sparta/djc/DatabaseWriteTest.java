package com.sparta.djc;

import com.sparta.djc.controller.DAO;
import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseWriteTest {

//    static ResultSet resultSet;

    @BeforeClass
    public static void setup(){
        EmployeeFileReader employeeReader = new EmployeeFileReader();
        Map<String, Employee> employees = employeeReader.readEmployees("resources/EmployeeRecords1.csv");
        DAO dao = new DAO();
        dao.addEmployeesToDatabase(employees);
//        resultSet = dao.employeeDatabaseQueryID("260736");
    }


    @Test
    public void testAddedID() throws SQLException {

    }


}

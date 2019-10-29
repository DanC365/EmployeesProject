package com.sparta.djc;

import com.sparta.djc.controller.DAO;
import com.sparta.djc.model.Employee;
import com.sparta.djc.controller.EmployeeFileReader;
import org.junit.BeforeClass;
import org.junit.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.Assert.*;

public class DatabaseWriteTest {
    static String[] employeeRecord;
    static Employee testEmployee;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

    @BeforeClass
    public static void setup(){
        EmployeeFileReader employeeReader = new EmployeeFileReader();
        Map<String, Employee> employees = employeeReader.readEmployees("resources/EmployeeRecords.csv");
        DAO dao = new DAO();
        //dao.addEmployeesToDatabase(employees);
        testEmployee = employees.get("784160");
        employeeRecord = dao.employeeDatabaseQueryID(testEmployee.getEmployeeID());
    }


    @Test
    public void testAddedID(){
        if(employeeRecord == null){
            fail();
        }else{
            assertEquals(testEmployee.getEmployeeID(),employeeRecord[0]);
        }
    }
    @Test
    public void testAddedPrefix(){
        if(employeeRecord == null){
            fail();
        }else{
            assertEquals(testEmployee.getNamePrefix(),employeeRecord[1]);
        }
    }
    @Test
    public void testAddedFirstName(){
        if(employeeRecord == null){
            fail();
        }else{
            assertEquals(testEmployee.getFirstName(),employeeRecord[2]);
        }
    }
    @Test
    public void testAddedMiddleInitial(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getMiddleInitial(),employeeRecord[3].charAt(0));
        }
    }
    @Test
    public void testAddedLastName(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getLastName(),employeeRecord[4]);
        }
    }
    @Test
    public void testAddedGender(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getGender(),employeeRecord[5].charAt(0));
        }
    }
    @Test
    public void testAddedEmail(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getEmail(),employeeRecord[6]);
        }
    }
    @Test
    public void testAddedDateOfBirth(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getDob(),LocalDate.parse(employeeRecord[7], formatter));
        }
    }
    @Test
    public void testAddedJoinDate(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getJoinDate(),LocalDate.parse(employeeRecord[8], formatter));
        }
    }



    @Test
    public void testAddedSalary(){
        if(employeeRecord == null){
            assertTrue(false);
        }else{
            assertEquals(testEmployee.getSalary(),Integer.parseInt(employeeRecord[9]));
        }
    }


}

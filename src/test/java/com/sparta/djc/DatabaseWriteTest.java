package com.sparta.djc;

import com.sparta.djc.model.dataAccessObjects.DAO;
import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;
import com.sparta.djc.model.dataAccessObjects.DAOSubArrays;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.*;
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


        final String QUERY = "TRUNCATE TABLE employeesproject.employee";
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Sakila?user=root&password=S417pqR5!")){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


        EmployeeFileReader employeeReader = new EmployeeFileReader();
        DAO dao = new DAO();
//        DAOSubArrays dao = new DAOSubArrays();
        Map<String, Employee> employees = employeeReader.readEmployees("resources/EmployeeRecordsLarge.csv");
        long start = System.nanoTime();
        dao.addEmployeesToDatabase(employees);
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end-start)/1000000000.0 + " Seconds");
        testEmployee = employees.get("40269"); //for the large csvs
//        testEmployee = employees.get("647173"); //for the smaller csvs
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

    @Test //for large file only
    public void databaseSizeTest(){
        int actualSize=0;

        final String QUERY = "SELECT COUNT(*) FROM employeesproject.employee";
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Sakila?user=root&password=S417pqR5!")){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                actualSize = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(65499,actualSize);

    }
}

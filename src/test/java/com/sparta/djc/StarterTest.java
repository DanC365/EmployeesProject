package com.sparta.djc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class StarterTest
{
    public static Map<String, Employee> employees;


    @BeforeClass
    public static void setup(){
        EmployeeFileReader efr = new EmployeeFileReader();
        employees = efr.readEmployees("resources/EmployeeRecords.csv");
    }

//    @Test
//    public void testRecordsRead(){
//        assertEquals(2,employees.size());
//    }

    @Test
    public void testFirstEmployeeID(){
        assertEquals("198429", employees.get("198429").getEmployeeID());
    }

    @Test
    public void testFirstEmployeePrefix(){
        assertEquals("Mrs.", employees.get("198429").getNamePrefix());
    }

    @Test
    public void testFirstEmployeeFirstName(){
        assertEquals("Serafina", employees.get("198429").getFirstName());
    }

    @Test
    public void testFirstEmployeeMiddleInitial(){
        assertEquals('I', employees.get("198429").getMiddleInitial());
    }

    @Test
    public void testFirstEmployeeLastName(){
        assertEquals("Bumgarner", employees.get("198429").getLastName());
    }

    @Test
    public void testFirstEmployeeGender(){
        assertEquals('F', employees.get("198429").getGender());
    }

    @Test
    public void testFirstEmployeeEmail(){
        assertEquals("serafina.bumgarner@exxonmobil.com", employees.get("198429").getEmail());
    }

    @Test
    public void testFirstEmployeeDOB()  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        assertEquals(LocalDate.parse("9/21/1982",formatter), employees.get("198429").getDob());
    }

    @Test
    public void testFirstEmployeeJoinDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        assertEquals(LocalDate.parse("2/1/2008",formatter), employees.get("198429").getJoinDate());
    }

    @Test
    public void testFirstEmployeeSalary(){
        assertEquals(69294, employees.get("198429").getSalary());
    }


//    @Test
//    public void testEmployeeCreator(){
//        EmployeeFileReader efr = new EmployeeFileReader();
//        Employee testEmployee = efr.createEmployee("1675,Mr.,Daniel,J,Curtis,M,dcurtis@spartaglobal.com,3/3/1998,30/9/2019,100");
//        assertEquals(1675,testEmployee.getEmployeeID());
//        assertEquals("Mr.",testEmployee.getNamePrefix());
//        assertEquals("Daniel",testEmployee.getFirstName());
//        assertEquals('J',testEmployee.getMiddleInitial());
//        assertEquals("Curtis",testEmployee.getLastName());
//        assertEquals('M',testEmployee.getGender());
//        assertEquals("dcurtis@spartaglobal.com", testEmployee.getEmail());
//        assertEquals("3/3/1998",testEmployee.getDob());
//        assertEquals("30/9/2019",testEmployee.getJoinDate());
//        assertEquals(100,testEmployee.getSalary());
//    }


}

package com.sparta.djc.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileReader {
    private final String LOG_PROPERTIES_FILE ="resources/log4j.properties";
    private Logger log = Logger.getLogger(EmployeeFileReader.class.getName());


    public List<Employee> readEmployees(String documentName){
        initialiseLogging();
        List<Employee> employees = new ArrayList<>();


        try (BufferedReader reader= new BufferedReader(new FileReader(documentName))) {
            String employeeRecord=reader.readLine();
            while((employeeRecord=reader.readLine())!=null){
                Employee newEmployee = createEmployee(employeeRecord);
                if(newEmployee!=null){
                    employees.add(newEmployee);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }


    //log whenever it returns null
    private Employee createEmployee(String employeeDetails){
        String[] attributes = employeeDetails.split(",");

        //employee ID
        long employeeID=0;
        try{
            employeeID= Long.parseLong(attributes[0]);
        }catch(NumberFormatException e){
            log.warn("Invalid employee id format " + attributes[0] + " in " + employeeDetails);
            return null;
        }


        //Name Prefix
        if(!(attributes[1].matches("^[A-Z][a-z]{1,4}[.]$"))){
            log.warn("Invalid input " + attributes[1] + " for name prefix in " +employeeDetails);
            return null;
        }
        String namePrefix = attributes[1];

        //First name
        if(!(attributes[2].matches("^[A-Z][a-z]+$"))){
            log.warn("Invalid input " + attributes[2] + " for first name in " +employeeDetails);
            return null;
        }
        String firstName = attributes[2];

        //Middle Initial
        if(!(attributes[3].matches("^[A-Z]$"))){
            log.warn("Invalid input " + attributes[3] + " for middle initial in "+employeeDetails);
            return null;
        }
        char middleInitial = attributes[3].charAt(0);

        //Last name
        // TODO: 25/10/2019  make more specific
        if(!(attributes[4].matches("^[A-Z][a-z -]+$"))){
            log.warn("Invalid input " + attributes[4] + " for last name in "+employeeDetails);
            return null;
        }
        String lastName = attributes[4];


        //Gender
        if(!(attributes[5].matches("^[MF]$"))){
            log.warn("Invalid input " + attributes[5] + " for Gender in "+employeeDetails);
            return null;
        }
        char gender = attributes[5].charAt(0);

        //Email
        if(!(attributes[6].matches("^[a-z.[-]_]+@[a-z]+([.][a-z]+|[.][a-z]+[.][a-z]+)$"))){
            log.warn("Invalid input " + attributes[6] + " for Email in "+employeeDetails);
            return null;
        }
        String email = attributes[6];


        //setting up the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate dob = LocalDate.parse(attributes[7],formatter);
        LocalDate joinDate = LocalDate.parse(attributes[8],formatter);
        LocalDate todaysDate = LocalDate.now();

        //date of birth
        if(todaysDate.getYear()-dob.getYear()<16||todaysDate.getYear()-dob.getYear()>130){
            log.warn("Invalid input, date of birth " + attributes[7] + " must be between 16 and 130 years ago in "+employeeDetails);
            return null;
        }
        //join date
        if(todaysDate.compareTo(joinDate)<=0){
            log.warn("Invalid input " + attributes[8] + " future start date in "+employeeDetails);
            return null;
        }
        if(joinDate.getYear()-dob.getYear()<16){
            log.warn("Invalid dates, employee was under 16 when starting. " + employeeDetails);
        }

        //Salary
        int salary=0;
        try{
            salary = Integer.parseInt(attributes[9]);
        }catch(NumberFormatException e){
            log.warn("Invalid format for employee salary in " + employeeDetails);
            return null;
        }

        return new Employee(employeeID,namePrefix,firstName,middleInitial,lastName, gender, email, dob, joinDate, salary);
    }

    private void initialiseLogging(){
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        log.trace("Logging initialised");
    }

}

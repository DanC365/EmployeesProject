package com.sparta.djc.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EmployeeFileReader {
    private final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    private Logger log = Logger.getLogger(EmployeeFileReader.class.getName());
    private Map<String, Employee> employeeList;

    public Map<String, Employee> readEmployees(String documentName) {
        employeeList = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(documentName))) {
            reader.readLine();
            Stream<String> records = reader.lines();
            records.forEach(employeeRecord -> createEmployee(employeeRecord));
        }catch(IOException e){
            e.printStackTrace();
        }

        return employeeList;

    }


    private void createEmployee(String employeeDetails) {
        String[] attributes = employeeDetails.split(",");

        //employee ID
        String employeeID = attributes[0];
        if (!(attributes[0].matches("^[0-9]+$"))) {
            log.warn("Invalid employee id format " + attributes[0] + " in " + employeeDetails);
        }

        //Name Prefix
        if (!(attributes[1].matches("^[A-Z][a-z]{1,4}[.]$"))) {
            log.warn("Invalid input " + attributes[1] + " for name prefix in " + employeeDetails);
            return;
        }
        String namePrefix = attributes[1];

        //First name
        if (!(attributes[2].matches("^[A-Z][a-z]+$"))) {
            log.warn("Invalid input " + attributes[2] + " for first name in " + employeeDetails);
            return;
        }
        String firstName = attributes[2];

        //Middle Initial
        if (!(attributes[3].matches("^[A-Z]$"))) {
            log.warn("Invalid input " + attributes[3] + " for middle initial in " + employeeDetails);
            return;
        }
        char middleInitial = attributes[3].charAt(0);

        //Last name - last names have a lot of variation with capitals, spaces and hyphons so Regex can't be specific
        if (!(attributes[4].matches("^[A-Za-z -]+$"))) {
            log.warn("Invalid input " + attributes[4] + " for last name in " + employeeDetails);
            return;
        }
        String lastName = attributes[4];

        //Gender
        // TODO: 31/10/2019 make enum
        if (!(attributes[5].matches("^[MF]$"))) {
            log.warn("Invalid input " + attributes[5] + " for Gender in " + employeeDetails);
            return;
        }
        char gender = attributes[5].charAt(0);

        //Email
        if (!(attributes[6].matches("^[a-z.[-]_]+@[a-z]+([.][a-z]+|[.][a-z]+[.][a-z]+)$"))) {
//        if(!(attributes[6].matches("^" + firstName.toLowerCase() + "[.]" +lastName.toLowerCase()+"@[a-z]+([.][a-z]+|[.][a-z]+[.][a-z]+)$"))){
            log.warn("Invalid input " + attributes[6] + " for Email in " + employeeDetails);
            return;
        }
        String email = attributes[6];

        //setting up the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate dob = LocalDate.parse(attributes[7], formatter);
        LocalDate joinDate = LocalDate.parse(attributes[8], formatter);
        LocalDate todaysDate = LocalDate.now();

        //date of birth
        if (todaysDate.getYear() - dob.getYear() < 16 || todaysDate.getYear() - dob.getYear() > 130) {
            log.warn("Invalid input, date of birth " + attributes[7] + " must be between 16 and 130 years ago in " + employeeDetails);
            return;
        }
        //join date
        if (todaysDate.compareTo(joinDate) <= 0) {
            log.warn("Invalid input " + attributes[8] + " future start date in " + employeeDetails);
            return;
        }
        if (joinDate.getYear() - dob.getYear() < 16) {
            log.warn("Invalid dates, employee was under 16 when starting. " + employeeDetails);
        }

        //Salary
        int salary = 0;
        try {
            salary = Integer.parseInt(attributes[9]);
        } catch (NumberFormatException e) {
            log.warn("Invalid format for employee salary in " + employeeDetails);
            return;
        }
        addToMap(new Employee(employeeID, namePrefix, firstName, middleInitial, lastName, gender, email, dob, joinDate, salary));
    }


    private void addToMap(Employee toAdd){
        if(employeeList.putIfAbsent(toAdd.getEmployeeID(),toAdd)!=null){
            log.warn("Employee ID " + toAdd.getEmployeeID() + " for employee " + toAdd.toString() + " already exists for employee " + employeeList.get(toAdd.getEmployeeID()).toString());
        }
    }

}

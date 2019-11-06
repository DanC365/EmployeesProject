package com.sparta.djc.controller;

import com.sparta.djc.model.DAO;
import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Map;

public class EmployeeManager {

    private final String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    private Logger log = Logger.getLogger(EmployeeFileReader.class.getName());


    public void readWriteEmployees() {
        initialiseLogging();
        EmployeeFileReader employeeFileReader = new EmployeeFileReader();
        DAO dao = new DAO();
        Map<String, Employee> employees = employeeFileReader.readEmployees("resources/EmployeeRecordsLarge.csv");
        dao.addEmployeesToDatabase(employees);
    }


    private void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        log.trace("Logging initialised");
    }
}

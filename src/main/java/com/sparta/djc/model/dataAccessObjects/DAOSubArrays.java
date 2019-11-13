package com.sparta.djc.model.dataAccessObjects;


import com.sparta.djc.model.Employee;
import com.sparta.djc.model.EmployeeFileReader;
import org.apache.log4j.Logger;

import java.sql.*;

import java.util.Arrays;
import java.util.Map;

//this class was used to practise another method of using multiple threads
public class DAOSubArrays {

    private final String URL = "jdbc:mysql://localhost/Sakila?user=root&password=S417pqR5!";
    private Logger log = Logger.getLogger(EmployeeFileReader.class.getName());

    public void addEmployeesToDatabase(Map<String, Employee> employees) {

        Employee[] employeeList = employees.values().toArray(new Employee[employees.size()]);
        Thread[] threads = new Thread[100];
        final int count = employeeList.length;
        final int threadCount = threads.length;
        for (int i = 0; i < threads.length; i++) {
            Runnable databaseAddition;
            final int j = i;
            if((count*(j+1))/threadCount>count){
                databaseAddition = () -> addRecordThread(Arrays.copyOfRange(employeeList,((count*(j))/threadCount),count));
            }else{
                databaseAddition = () -> addRecordThread(Arrays.copyOfRange(employeeList,((count*(j))/threadCount),(count*(j+1))/threadCount));
            }


            threads[i] = new Thread(databaseAddition, "Thread " + i);
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    private void addRecordThread(Employee[] employees) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            final String QUERY = "INSERT INTO employeesproject.employee VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(QUERY);
            for (Employee employee: employees) {
                statement.setString(1, employee.getEmployeeID());
                statement.setString(2, employee.getNamePrefix());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, Character.toString(employee.getMiddleInitial()));
                statement.setString(5, employee.getLastName());
                statement.setString(6, Character.toString(employee.getGender()));
                statement.setString(7, employee.getEmail());
                statement.setDate(8, java.sql.Date.valueOf(employee.getDob()));
                statement.setDate(9, java.sql.Date.valueOf(employee.getJoinDate()));
                statement.setInt(10, employee.getSalary());
                try {
                    statement.executeUpdate();
                } catch (SQLIntegrityConstraintViolationException e) {
                    log.warn("Primary key clash found for employee " + employee.toString() + ". Employee not added to database");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String[] employeeDatabaseQueryID(String id) {
        final String QUERY = "SELECT * FROM employeesproject.employee WHERE employee_id=?";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            String[] employeeRecord = new String[10];
            if (resultSet.next()) {
                employeeRecord[0] = resultSet.getString(1);
                employeeRecord[1] = resultSet.getString(2);
                employeeRecord[2] = resultSet.getString(3);
                employeeRecord[3] = resultSet.getString(4);
                employeeRecord[4] = resultSet.getString(5);
                employeeRecord[5] = resultSet.getString(6);
                employeeRecord[6] = resultSet.getString(7);
                employeeRecord[7] = resultSet.getDate(8).toString();
                employeeRecord[8] = resultSet.getDate(9).toString();
                employeeRecord[9] = Integer.toString(resultSet.getInt(10));
            }
            return employeeRecord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}


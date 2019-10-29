package com.sparta.djc.controller;

import com.sparta.djc.model.Employee;

import java.sql.*;
import java.util.Map;

public class DAO {

    private final String URL="jdbc:mysql://localhost/Sakila?user=root&password=S417pqR5!";

    public void addEmployeesToDatabase(Map<String, Employee> employees){
        final String QUERY ="INSERT INTO employeesproject.employee VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement(QUERY);
            for(Employee employee : employees.values()){
                statement.setString(1,employee.getEmployeeID());
                statement.setString(2,employee.getNamePrefix());
                statement.setString(3,employee.getFirstName());
                statement.setString(4,Character.toString(employee.getMiddleInitial()));
                statement.setString(5,employee.getLastName());
                statement.setString(6,Character.toString(employee.getGender()));
                statement.setString(7,employee.getEmail());
                statement.setDate(8,java.sql.Date.valueOf(employee.getDob()));
                statement.setDate(9,java.sql.Date.valueOf(employee.getJoinDate()));
                statement.setInt(10,employee.getSalary());

                statement.executeUpdate();
            }


        }catch(SQLException e){
            e.printStackTrace();
        }



    }


    public String[] employeeDatabaseQueryID(String id){
        final String QUERY ="SELECT * FROM employeesproject.employee WHERE employee_id=?";
        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            String[] employeeRecord = new String[10];
            if(resultSet.next()){
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
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}


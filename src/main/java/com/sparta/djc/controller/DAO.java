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


    public ResultSet employeeDatabaseQueryID(String id){
        final String QUERY ="SELECT * FROM employeesproject.employee WHERE employee_id=?";
        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1,id);
            return statement.executeQuery();


        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}


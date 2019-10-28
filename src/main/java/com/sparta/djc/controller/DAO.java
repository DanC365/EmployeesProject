package com.sparta.djc.controller;

import com.sparta.djc.model.Employee;

import java.sql.*;
import java.time.ZoneId;
import java.util.Map;

public class DAO {
    private final String QUERY ="INSERT INTO employeesproject.employee VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String URL="jdbc:mysql://localhost/Sakila?user=root&password=S417pqR5!";

    public void runSQLQuery(Map<Long,Employee> employees){
        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement(QUERY);

            for(Employee employee : employees.values()){
                statement.setLong(1,employee.getEmployeeID());
                statement.setString(2,employee.getNamePrefix());
                statement.setString(3,employee.getFirstName());
                statement.setString(4,Character.toString(employee.getMiddleInitial()));
                statement.setString(5,employee.getLastName());
                statement.setString(6,employee.getNamePrefix());
                statement.setString(7,Character.toString(employee.getGender()));
                statement.setString(8,employee.getEmail());

                statement.setDate(9,java.sql.Date.valueOf(employee.getDob()));

                statement.setDate(10,java.sql.Date.valueOf(employee.getJoinDate()));
                statement.setInt(11,employee.getSalary());

                statement.executeQuery();
            }


        }catch(SQLException e){

        }



    }





}


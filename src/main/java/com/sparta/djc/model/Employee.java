package com.sparta.djc.model;

import java.time.LocalDate;

public class Employee {
    private final long employeeID;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private final LocalDate dob;
    private final LocalDate joinDate;
    private int salary;


    public Employee(long employeeID, String namePrefix, String firstName, char middleInitial, String lastName, char gender, String email, LocalDate dob, LocalDate joinDate, int salary) {
        this.employeeID = employeeID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.joinDate = joinDate;
        this.salary = salary;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge(){
        LocalDate todayDate = LocalDate.now();

        return 0;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }



    public String toString(){
        return (employeeID + ": " + namePrefix +" "+ firstName + " " + middleInitial + " " + lastName);


    }
}

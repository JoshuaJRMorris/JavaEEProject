/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NBCC;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jacob
 */
public class Team {
    private int id;
    private Date dateOfCreation = new Date();
    private String name = "";
    private boolean onCall = false;
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<String> errors = new ArrayList<String>();
    
        public boolean validCheck(){
        errors.clear();
        if (name == null || name.equals("")){
            errors.add("Please enter a Name");
        }
        if (employees.isEmpty()){
            errors.add("Please select atleast one Employee");
        }
        if (errors.isEmpty()) {
            createTeam();
            return true;
        }
       
        return false;
    }
        public boolean addEmployee(Employee curr){
            for (int i = 0; i < employees.size(); ++i){
                if (employees.get(i).equals(curr)){
                    return false;
                }
            }
            employees.add(curr);
            return true;
        }
        public boolean equals(Team check){
            if (this.name.equals(check.getName())) {
                return true;
            }
            return false;
        }
    public void createTeam(){
        for (int i = 0; i < employees.size(); ++i){
            employees.get(i).setInTeam(true);
        }
    }
    public boolean employeeIsInTeam(Employee check){
        for (Employee tmp : employees){
            if (tmp.equals(check)){
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnCall() {
        return onCall;
    }

    public void setOnCall(boolean onCall) {
        this.onCall = onCall;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }
    
}

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
public class Employee {
    private int id;
    private Date dateOfCreation = new Date();
    private String firstName = "";
    private String lastName = "";
    private String SIN = "";
    private String fullName;
    private double payRate = 0;
    private boolean inTeam = false;
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    private ArrayList<String> errors = new ArrayList<String>();
    
    public boolean validCheck(){
        errors.clear();
        if (firstName == null || firstName.equals("")){
            errors.add("Please enter a First Name");
        }
        if (lastName == null || lastName.equals("")){
            errors.add("Please enter a Last Name");
        }
        if (SIN == null || SIN == ""){
            errors.add("Please enter a SIN");
        }
        else if (!(SIN.length() == 9)){
            errors.add("The SIN must contain 9 digits");
        }
        else if (!Utility.isNumber(SIN)){
            errors.add("SIN must be a 9 digit number");
        }
        if (payRate <= 11.70){
            errors.add("Pay Rate must be atleast minimum wage($11.70)");
        }
        if (errors.isEmpty()) {
            fullName = firstName + " " + lastName;
            return true;
        }
       
        return false;
    }
    public boolean addSkill(Skill curr){
            for (int i = 0; i < skills.size(); ++i){
                if (skills.get(i).equals(curr)){
                    return false;
                }
            }
            skills.add(curr);
            return true;
        }
    public boolean hasSkill(Skill check)
    {
         for (Skill tmp : skills){
            if (tmp.equals(check)){
                return true;
            }
        }
        return false;
    }
    public boolean equals(Employee check){
        if (this.fullName.equals(check.getFullName())){
            return true;
        }
        return false;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSIN() {
        return SIN;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
    
    public void setSIN(String SIN) {
        this.SIN = SIN;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public boolean isInTeam() {
        return inTeam;
    }

    public void setInTeam(boolean inTeam) {
        this.inTeam = inTeam;
    }
    
    
}

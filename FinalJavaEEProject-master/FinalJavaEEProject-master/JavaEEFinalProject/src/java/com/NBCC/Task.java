/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NBCC;

import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class Task {
    private int id;
    private String name = "";
    private String description = "";
    private String length = "";
    private int intLength = 0;
    private ArrayList<String> errors = new ArrayList<String>();
    
    public boolean validCheck(){
        errors.clear();
        if (name == null || name.equals("")){
            errors.add("Please enter a Name");
        }
        if (Utility.isNumber(length) && !length.isEmpty()){
            length = "0";
            if (intLength <= 0) {
                errors.add("Please enter a number greater then 0");
            } else if (intLength > 90) {
                errors.add("Length must be smaller the 90");
            }
        }else {
            errors.add("Please enter a valid number");
        }
        if (errors.isEmpty()){
           // startTask();
            return true;
        }
        return false;
    }
    public void setLength(String length) {
        if (Utility.isNumber(length)){
        this.length = length;
        this.intLength = Integer.parseInt(length);
        }
    }
    /*
    public boolean addTeam(Team curr){
        for (int i = 0; i < teams.size(); ++i){
            if (teams.get(i).equals(curr)){
                return false;
            }
        }
        teams.add(curr);
        return true;
    }
    public void startTask(){
        for (int i = 0; i < teams.size(); ++i){
            teams.get(i).setOnCall(true);
        }
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }


    
}

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
public class Job {
    private int id;
    private String clientName = "";
    private String description = "";
    private Team team;
    private ArrayList<String> errors = new ArrayList<String>();
    private ArrayList<Task> tasks = new ArrayList<Task>();
    
    public boolean addTask(Task curr){
        for (int i = 0; i < tasks.size(); ++i){
            if (tasks.get(i).equals(curr)){
                return false;
            }
        }
        tasks.add(curr);
        return true;
    }
    public boolean taskIsInJob(Task check){
        for (Task tmp : tasks){
            if (tmp.equals(check)){
                return true;
            }
        }
        return false;
    }
    public boolean validCheck(){
        errors.clear();
        if (clientName == null || clientName.equals("")){
            clientName = "";
            errors.add("Please enter a Client Name");
        }
        if (tasks.isEmpty()){
            errors.add("Please choose a task");
        }
        if (team == null){
            errors.add("Please choose a team");
        }
        if (errors.isEmpty()){
            return true;
        }
        return false;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
}

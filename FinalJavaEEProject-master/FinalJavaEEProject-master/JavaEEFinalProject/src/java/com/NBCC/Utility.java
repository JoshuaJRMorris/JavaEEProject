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
public class Utility {
    
    public static boolean isNumber(String number){
        boolean numeric = true;
        if (number.isEmpty()){
            return false;
        }
        try{
            double tmp = Double.parseDouble(number);
        }catch (NumberFormatException e){
            numeric = false;
        }
        return numeric;
    }
    public static int findTask(ArrayList<Task> list, Task curr){
        for (int i = 0; i < list.size(); ++i){
            //change to id when sql is added
            if (list.get(i).getName().equals(curr.getName())){
                return i;
            }
        }
        return -1;
    }
    public static int findJob(ArrayList<Job> list, Job curr){
        for (int i = 0; i < list.size(); ++i){
            //change to id when sql is added
            if (list.get(i).getClientName().equals(curr.getClientName())){
                return i;
            }
        }
        return -1;
    }
    public static int findTeam(ArrayList<Team> list, Team curr){
        for (int i = 0; i < list.size(); ++i){
            //change to id when sql is added
            if (list.get(i).getName().equals(curr.getName())){
                return i;
            }
        }
        return -1;
    }
    public static int findEmployee(ArrayList<Employee> list, Employee curr){
        for (int i = 0; i < list.size(); ++i){
            //change to id when sql is added
            if (list.get(i).getFullName().equals(curr.getFullName())){
                return i;
            }
        }
        return -1;
    }
    //tmp function until sql is built for skill
    public static ArrayList<Skill> loadTmpSkills(){
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add((new Skill("Java")));
        skills.add((new Skill("C++")));
        skills.add((new Skill("C#")));
        skills.add((new Skill("SQL")));
        skills.add((new Skill("HTML")));
        skills.add((new Skill("CSS")));
        skills.add((new Skill("JavaScript")));
        return skills;
    }
}

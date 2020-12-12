/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NBCC;

/**
 *
 * @author Jacob
 */
public class Skill {
    private String name;
    public Skill(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean equals(Skill check) {
        if (check.name.equals(this.name)){
            return true;
        }
        return false;
    }
    
}

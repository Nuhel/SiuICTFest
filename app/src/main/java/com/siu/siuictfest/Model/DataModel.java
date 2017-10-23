package com.siu.siuictfest.Model;

/**
 * Created by Nuhel on 10/24/2017.
 */

public class DataModel {

    private String teamName;

    private String m1name;
    private String m2name;
    private String m3name;

    private String m1roll;
    private String m2roll;

    public String getTeamName() {
        return teamName;
    }

    public String getM1name() {
        return m1name;
    }

    public String getM2name() {
        return m2name;
    }

    public String getM3name() {
        return m3name;
    }

    public String getM1roll() {
        return m1roll;
    }

    public String getM2roll() {
        return m2roll;
    }

    public String getM3roll() {
        return m3roll;
    }

    public String getM1dept() {
        return m1dept;
    }

    public String getM2dept() {
        return m2dept;
    }

    public String getM3dept() {
        return m3dept;
    }

    private String m3roll;

    private String m1dept;
    private String m2dept;
    private String m3dept;


    public DataModel (String teamName){
        this.teamName = teamName;
    }

    public void setM1info(String name, String roll, String dept){
        this.m1name = name;
        this.m1roll = roll;
        this.m1dept = dept;
    }


    public void setM2info(String name, String roll, String dept){
        this.m2name = name;
        this.m2roll = roll;
        this.m2dept = dept;
    }

    public void setM3info(String name, String roll, String dept){
        this.m3name = name;
        this.m3roll = roll;
        this.m3dept = dept;
    }

}

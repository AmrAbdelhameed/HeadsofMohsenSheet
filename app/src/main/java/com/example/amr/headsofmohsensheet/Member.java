package com.example.amr.headsofmohsensheet;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Member {

    String name;
    String numberoftasks;
    String numberofmeetings;
    String task_mo7sens;
    String meetings_mo7sens;

    public Member() {}

    public String getName() {
        return name;
    }

    public String getNumberoftasks() {
        return numberoftasks;
    }

    public String getNumberofmeetings() {
        return numberofmeetings;
    }

    public String getTask_mo7sens() {
        return task_mo7sens;
    }

    public String getMeetings_mo7sens() {
        return meetings_mo7sens;
    }

    public Member(String name, String numberoftasks, String numberofmeetings, String task_mo7sens, String meetings_mo7sens) {
        this.name = name;
        this.numberoftasks = numberoftasks;
        this.numberofmeetings = numberofmeetings;
        this.task_mo7sens = task_mo7sens;
        this.meetings_mo7sens = meetings_mo7sens;
    }

}
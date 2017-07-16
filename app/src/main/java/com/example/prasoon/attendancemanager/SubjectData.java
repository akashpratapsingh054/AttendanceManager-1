package com.example.prasoon.attendancemanager;

/**
 * Created by md on 15/7/17.
 */

public class SubjectData {
    String name;
    String wdays;
    String bdays;
    String percent;
    String minper;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWdays() {
        return wdays;
    }

    public void setWdays(String wdays) {
        this.wdays = wdays;
    }

    public String getBdays() {
        return bdays;
    }

    public void setBdays(String bdays) {
        this.bdays = bdays;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getMinper() {
        return minper;
    }

    public void setMinper(String minper) {
        this.minper = minper;
    }

    public SubjectData(String a, String b, String c
            , String d, String e) {
        name = a;
        wdays = b;
        bdays = c;
        percent = d;
        minper = e;
    }

    public SubjectData() {

    }
}

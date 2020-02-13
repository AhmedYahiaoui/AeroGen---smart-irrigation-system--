package com.example.blackhummer.testslide.model;


import java.util.ArrayList;
public class Logfile {
    private String done ,date_log ;


    public Logfile(String done, String date_log) {
        this.done = done;
        this.date_log = date_log;
    }

    public Logfile() {
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getDate_log() {
        return date_log;
    }

    public void setDate_log(String date_log) {
        this.date_log = date_log;
    }
}

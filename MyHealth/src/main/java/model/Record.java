package model;

import java.util.Date;

/** This class is where the records setters and getters are in place
 /*
 * Classname : Record
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */

public class Record {
    private String date;

    private String weight;

    private String temperature;
    private String lowbp;
    private String highbp;
    private String notes;


    public Record(String date, String weight, String temperature, String lowbp, String highbp, String notes) {
        this.date = date;
        this.weight = weight;
        this.temperature = temperature;
        this.lowbp = lowbp;
        this.highbp = highbp;
        this.notes = notes;
    }

    public Record() {

    }

    public Record(String s, double weight, double temperature, int lowbp, int highbp, String notes) {
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLowbp() {
        return lowbp;
    }

    public void setLowbp(String lowbp) {
        this.lowbp = lowbp;
    }

    public String getHighbp() {
        return highbp;
    }

    public void setHighbp(String highbp) {
        this.highbp = highbp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

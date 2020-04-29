package com.dbmi.seeds.model;

import com.sun.jdi.StringReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Crop {

    private @Id @GeneratedValue int  cropId;
    private String cropName;
    private String cropDescription;

    public Crop() {}

    public Crop(int id, String name, String desc) {
        this.cropId          = id;
        this.cropName        = name;
        this.cropDescription = desc;
    } // CONSTRUCTOR(INT,STRING,STRING)

    public int getCropId() {
        return cropId;
    } // GETCROPID()

    public void setCropId(int cropId) {
        this.cropId = cropId;
    } // SETCROPID(INT)

    public String getCropName() {
        return cropName;
    } // GETCROPNAME()

    public void setCropName(String cropName) {
        this.cropName = cropName;
    } // SETCROPNAME(STRING)

    public String getCropDescription() {
        return cropDescription;
    } // GETCROPDESCRIPTION()

    public void setCropDescription(String cropDescription) {
        this.cropDescription = cropDescription;
    } // SETCODEDESCRIPTION(STRING)
} // CLASS

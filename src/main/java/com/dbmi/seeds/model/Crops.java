package com.dbmi.seeds.model;

import com.sun.jdi.StringReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Crops {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  cropId;
    private String cropName;
    private String cropDescription;

    public Crops() {}

    public Crops(long id, String name, String desc) {
        this.cropId          = id;
        this.cropName        = name;
        this.cropDescription = desc;
    } // CONSTRUCTOR(INT,STRING,STRING)

    public long getCropId() {
        return cropId;
    } // GETCROPID()

    public void setCropId(long cropId) {
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

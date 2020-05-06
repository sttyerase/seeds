package com.dbmi.seeds.model;

import com.sun.jdi.StringReference;

import javax.persistence.*;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crop_id")
    private long     cropId;
    @Column(name = "crop_name")
    private String   cropName;
    @Column(name = "crop_description")
    private String   cropDescription;
    @Column(name = "crop_icc_code")
    private int      cropICCCode;
    // TODO: Problems dealing with null value in ICC code column.
    // TODO: How does Hibernate map Entity properties to database columns?
    // TODO: Make all Entity primary keys LONG/BIGINT.

    public Crop() {}

    public Crop(long id, String name, String desc) {
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

    public int getCropICCCode() {
        return cropICCCode;
    } // GETCROPICCCODE()

    public void setCropICCCode(int cropICCCode) {
        this.cropICCCode = cropICCCode;
    } // SETCROPICCCODE(INT)

} // CLASS

package com.dbmi.seeds.model;

import jakarta.persistence.*;

@Entity
@Table(name = "varieties")
public class Variety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variety_id")
    private long     varietyId;
    @Column(name = "variety_name")
    private String   varietyName;
    @Column(name = "variety_description")
    private String   varietyDescription;
    @Column(name = "variety_crop_id")
    private long      varietyCropId;

    public Variety() {}

    public Variety(long id, String name, String desc, long cropId) {
        this.varietyId          = id;
        this.varietyName        = name;
        this.varietyDescription = desc;
        this.varietyCropId      = cropId;
    } // CONSTRUCTOR(INT,STRING,STRING)

    public long getVarietyId() {
        return varietyId;
    } // GETVARIETYID()

    public void setVarietyId(long varietyId) {
        this.varietyId = varietyId;
    } // SETVARIETYID(INT)

    public String getVarietyName() {
        return varietyName;
    } // GETVARIETYNAME()

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    } // SETVARIETYNAME(STRING)

    public String getVarietyDescription() {
        return varietyDescription;
    } // GETVARIETYDESCRIPTION()

    public void setVarietyDescription(String varietyDescription) {
        this.varietyDescription = varietyDescription;
    } // SETCODEDESCRIPTION(STRING)

    public long getVarietyCropId() {
        return varietyCropId;
    }

    public void setVarietyCropId(long varietyCropId) {
        this.varietyCropId = varietyCropId;
    }

} // CLASS

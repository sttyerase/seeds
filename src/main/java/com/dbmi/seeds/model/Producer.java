package com.dbmi.seeds.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private long     producerId;
    @Column(name = "producer_short_name")
    private String   producerShortName;
    @Column(name = "producer_name")
    private String   producerName;
    @Column(name = "producer_address1")
    private String   producerAddress1;
    @Column(name = "producer_address2")
    private String   producerAddress2;
    @Column(name = "producer_city")
    private String   producerCity;
    @Column(name = "producer_state")
    private String   producerState;
    @Column(name = "producer_zip")
    private String   producerZip;

    public Producer() {}

    public Producer(long id, String name) {
        this.producerId          = id;
        this.producerName        = name;
    } // CONSTRUCTOR(INT,STRING,STRING)

    public long getProducerId() {
        return producerId;
    } // GETPRODUCERID()

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    } // SETPRODUCERID(INT)

    public String getProducerName() {
        return producerName;
    } // GETPRODUCERNAME()

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    } // SETPRODUCERNAME(STRING)

    public String getProducerShortName() {
        return producerShortName;
    }

    public void setProducerShortName(String producerShortName) {
        this.producerShortName = producerShortName;
    }

    public String getProducerAddress1() {
        return producerAddress1;
    }

    public void setProducerAddress1(String producerAddress1) {
        this.producerAddress1 = producerAddress1;
    }

    public String getProducerAddress2() {
        return producerAddress2;
    }

    public void setProducerAddress2(String producerAddress2) {
        this.producerAddress2 = producerAddress2;
    }

    public String getProducerCity() {
        return producerCity;
    }

    public void setProducerCity(String producerCity) {
        this.producerCity = producerCity;
    }

    public String getProducerState() {
        return producerState;
    }

    public void setProducerState(String producerState) {
        this.producerState = producerState;
    }

    public String getProducerZip() {
        return producerZip;
    }

    public void setProducerZip(String producerZip) {
        this.producerZip = producerZip;
    }

} // CLASS

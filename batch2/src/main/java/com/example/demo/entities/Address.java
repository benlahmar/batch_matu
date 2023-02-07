package com.example.demo.entities;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="address")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {  "zipCode", "stateCode", "country", "streetAddress", "city"})
@Embeddable
public class Address {
 
    //private Long id;
 
    @XmlElement(name = "street-address")
    private String streetAddress;
 
    private String city;
 
    @XmlElement(name = "state-code")
    private String stateCode;
 
    private  String country;
 
    @XmlElement(name = "zip-code")
    private String zipCode;
     
    
 
    public String getStreetAddress() {
        return streetAddress;
    }
 
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getStateCode() {
        return stateCode;
    }
 
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getZipCode() {
        return zipCode;
    }
 
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
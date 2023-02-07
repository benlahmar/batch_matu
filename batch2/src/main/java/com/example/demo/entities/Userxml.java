package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class Userxml {
 
	
    private Long id;
    private String nom;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
 
	//@XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    private Address address;
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
//	public Address getAddresses() {
//		return addresses;
//	}
//	public void setAddresses(List<Address> addresses) {
//		if (this.addresses == null) {
//            this.addresses = new ArrayList<>();
//        } else {
//            this.addresses.clear();
//        }
//        this.addresses.addAll(addresses);
//	}
    
    
    
    
	
	
}

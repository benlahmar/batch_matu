package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Produit {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	String desg;
	double prix;
	
	double discount;
	double finalprix;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getFinalprix() {
		return finalprix;
	}
	public void setFinalprix(double finalprix) {
		this.finalprix = finalprix;
	}
	
	
	
}

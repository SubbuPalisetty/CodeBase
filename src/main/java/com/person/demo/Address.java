package com.person.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	@Id	    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aid;
	// Defined as NOT NULL
	@Column(nullable=false) 
	private String street;
	private String city;	
	//Default value is TELANGANA
	private String state = "TELANGANA";
	private String postalCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")	
    private Person person;
	
	
	public Address() {
		super();		
	}
	
	public Address(int aid, String street, String city, String state, String postalCode) {
		this.aid = aid;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Override
	public String toString() {
		return "Address [id=" + aid + ", street=" + street + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + "]";
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
	
}

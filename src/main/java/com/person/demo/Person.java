package com.person.demo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity	
public class Person {	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String firstName;
	private String lastName;		
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "pid")
	private Set<Address> addresses;
	
	public Person() {
		super();		
	}
	
	public Person(Integer pid, String firstName, String lastName, Set<Address> addresses) {
		super();
		this.pid = pid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = addresses;
	}

	public Person(int pid, String firstName, String lastName) {	
		this.pid = pid;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	@Override
	public String toString() {
		return "Person [id=" + pid + ", firstName=" + firstName + ", lastName=" + lastName + ", addresses=" + addresses
				+ "]";
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	
	 

}

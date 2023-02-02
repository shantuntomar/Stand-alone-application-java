package in.shantun.dto;

import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer sid;
	private String name;
	private String email;
	private String city;
	private String country;
	
	public void setId(Integer id) {
		this.sid = id;
	}
	
	public Integer getId() {
		return this.sid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	
	public String toString() {
		return "[NAME = " + name + " , EMAIL = " + email + " , CITY = " + city + " , COUNTRY = " + country + " ] ";
	}

}

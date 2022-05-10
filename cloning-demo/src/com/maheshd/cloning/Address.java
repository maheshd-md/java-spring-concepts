/**
 * 
 */
package com.maheshd.cloning;

/**
 * @author Mahesh
 *
 */
public class Address {

	private String area;
	private String city;
	private Integer pincode;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getArea() {
		return area;
	}
	public String getCity() {
		return city;
	}
	public Integer getPincode() {
		return pincode;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
}

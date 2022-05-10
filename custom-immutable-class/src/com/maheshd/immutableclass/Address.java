/**
 * 
 */
package com.maheshd.immutableclass;

/**
 * @author Mahesh
 *
 */
public final class Address {

	private final String area;
	private final String city;
	private final Integer pincode;
	
	
	public Address(String area, String city, Integer pincode) {
		super();
		this.area = area;
		this.city = city;
		this.pincode = pincode;
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
}

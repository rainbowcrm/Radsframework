package com.techtrade.rads.framework.test;

import java.util.Date;

import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UIText;
import com.techtrade.rads.framework.ui.controls.UITextArea;

public class Customer extends ModelObject{
	
	private int id;
	private String firstName;
	private String lastName;
	private int age ;
	private String address;
	private String state;
	private Date birthday;
	private String sex;
	private String salesMan;
	private  String email ;
	private String phone ;
	
	public Boolean isEligible () {
		return false;
	}
	
	@Override
	public String toString() {
		
		return "id=" + id  + "\nName=" + firstName + "\nlastName=" + lastName + "\nage=" +age +
				"address="+address + "\nstate=" + state + "\nbirthday="+birthday + "\nsex="+ sex;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}

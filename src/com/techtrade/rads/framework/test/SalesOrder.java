package com.techtrade.rads.framework.test;

import java.util.Date;
import java.util.List;

import com.techtrade.rads.framework.model.abstracts.ModelObject;

public class SalesOrder extends ModelObject{

	public enum DiscountType {
		PERCENTDIS,  AMOUNTDISC ;
	}
	
	int id ;
	
	int company ;
	
	Customer cust ; 

	
	boolean discType ;
	
	String invoiceNo;
	
	Date saleDate;
	
	String salesMan ;
	
	List <SalesLineItem> lineItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}



	public boolean isDiscType() {
		return discType;
	}

	public void setDiscType(boolean discType) {
		this.discType = discType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public List<SalesLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<SalesLineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	
	
	
	

}

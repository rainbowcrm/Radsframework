package com.techtrade.rads.framework.test;

import com.techtrade.rads.framework.model.abstracts.ModelObject;

public class LineItem extends ModelObject{
	
	public enum DiscountType {
		PERCENTDIS,  AMOUNTDISC ;
	}
	int id ;
	
	int sequence ;
	
	Item item ;	
	
	double qty ;
	
	double price ;
	
	double discount;
	
	DiscountType dicsType ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public DiscountType getDicsType() {
		return dicsType;
	}

	public void setDicsType(DiscountType dicsType) {
		this.dicsType = dicsType;
	};
	
	
	
	
	
	
	

}

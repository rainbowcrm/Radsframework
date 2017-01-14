package com.techtrade.rads.framework.test;

import com.techtrade.rads.framework.model.abstracts.ModelObject;

public class Item extends ModelObject {

	int id ;
	
	String skuName;
	
	String barcode ;
	
	double width;
	
	double height ;
	
	double length ;
	
	String UOM;
	
	String ManufacturName ;
	
	int manufacturerId;
	
	int productId;
	
	String Product  ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return skuName;
	}

	public void setItemName(String skuName) {
		this.skuName = skuName;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getUOM() {
		return UOM;
	}

	public void setUOM(String uOM) {
		UOM = uOM;
	}

	public String getManufacturName() {
		return ManufacturName;
	}

	public void setManufacturName(String manufacturName) {
		ManufacturName = manufacturName;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}	
	
	
	
	
	
	


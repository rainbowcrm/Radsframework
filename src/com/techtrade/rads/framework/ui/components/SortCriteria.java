package com.techtrade.rads.framework.ui.components;

public class SortCriteria {

	String fieldName;
	
	public enum DIRECTION  {
		
		ASCENDING, DESCENDING ;
		
	}  
	DIRECTION direction ;
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public DIRECTION getDirection() {
		return direction;
	}
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	public  SortCriteria() {
	}
	
	public SortCriteria(String fieldName, DIRECTION direction) {
		super();
		this.fieldName = fieldName;
		this.direction = direction;
	}


	
	
	
	
	
	
	
	
	
}

package com.techtrade.rads.framework.filter;

public class FilterNode {
	String field;
	Object value ;
	Operator operater ;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Operator getOperater() {
		return operater;
	}
	public void setOperater(Operator operater) {
		this.operater = operater;
	}
	
	public enum Operator { 
		EQUALS, LESS_THAN,GREATER_THAN,IN, NOT_IN, CONTAINS , LESS_THAN_EQUAL, GREATER_THAN_EQUAL;
		
	}
	

}



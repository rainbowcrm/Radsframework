package com.techtrade.rads.framework.model.abstracts;

public enum DataType {
	INTEGER , STRING, DATE, DOUBLE;
	
	public static Class getClass(DataType type){
		if (type == INTEGER) {
			return new Integer(1).getClass();
		}else if (type == DATE) {
			return  new java.util.Date().getClass();
		}else if (type == DOUBLE) {
			return new Double(1).getClass();
		} else
			return new String("").getClass() ;
		
	}
}

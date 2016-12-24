package com.techtrade.rads.framework.model.graphdata;

import java.util.HashMap;
import java.util.Map;

public class LineChartEntryData {

	Map<String, Double> valueMap ;
	String text; 
	String color;

	public Map<String, Double> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Double> values) {
		this.valueMap = values;
	}
	
	public void addToValueMap(String key, Double value) {
		if (valueMap == null)
			valueMap = new HashMap<String, Double>();
		valueMap.put(key, value);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}

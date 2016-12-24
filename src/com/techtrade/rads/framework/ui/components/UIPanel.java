package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

public class UIPanel {
	
	String key ; 
	List <UIElement> elements;
	String style ;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<UIElement> getElements() {
		return elements;
	}
	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	} 
	
	
	public void  addElement(UIElement element) {
		if (elements == null) {
			elements = new ArrayList<UIElement>();
		}
		elements.add(element);
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	

}

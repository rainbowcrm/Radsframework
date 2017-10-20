package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;

public class UITile extends UIControl{
	
	List<UIElement> headerElements = null;
	List<UIElement> detailElements = null;
	
	String headerStyle;
	String detailStyle;

	
	

	public List<UIElement> getHeaderElements() {
		return headerElements;
	}

	public void setHeaderElements(List<UIElement> headerElements) {
		this.headerElements = headerElements;
	}
	
	public void addHeaderElement(UIElement headerElement) {
		if(headerElements == null)
			headerElements = new ArrayList<>();
		headerElements.add( headerElement);
	}

	public List<UIElement> getDetailElements() {
		return detailElements;
	}

	public void setDetailElements(List<UIElement> detailElements) {
		this.detailElements = detailElements;
	}
	
	public void addDetailElement(UIElement detailElement) {
		if(detailElements == null)
			detailElements = new ArrayList<>();
		detailElements.add( detailElement);
	}

	public String getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(String headerStyle) {
		this.headerStyle = headerStyle;
	}

	public String getDetailStyle() {
		return detailStyle;
	}

	public void setDetailStyle(String detailsStyle) {
		this.detailStyle = detailsStyle;
	}


	
	
}
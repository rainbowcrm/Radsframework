package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;

public class UIIFrame extends UIControl{

	List<UIElement> elements = null;
	String src;

	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}
	
	public void addElement (UIElement element) {
		if (elements == null) 
			elements = new ArrayList<UIElement>();
		elements.add(element);
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public UIIFrame() {
		super();
	}

	public UIIFrame(String id) {
		super(id);
	}
	
	
	
}

package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.UIElement;

public class UIDiv extends UIControl{
	
	List<UIElement> elements = null;

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
	
	public void insertAt(UIElement presentElement, List<UIElement> newElements ) {
		List<UIElement> newList = new ArrayList<UIElement>();
		for(UIElement element : elements) {
			if(element!= null && element.equals(presentElement)) {
				for (UIElement newElement : newElements) {
					newList.add(newElement);
				}
			}else {
				newList.add(element);
			}
		}
		elements = newList;
			
	}
	
	
}

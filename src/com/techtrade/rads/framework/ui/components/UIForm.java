package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;


public class UIForm extends UIControl {
	
	List<UIElement> elements;
	String encType ;
	
	public enum  METHOD { GET ,  POST } ;
	METHOD method;
	
	String action ;
	
	
		
	public String getEncType() {
		return encType;
	}

	public void setEncType(String encType) {
		this.encType = encType;
	}

	public METHOD getMethod() {
		return method;
	}

	public void setMethod(METHOD method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public UIForm(String id, METHOD  method, String  action ) {
		setId(id);
		this.method = method;
		this.action = action ;
		
	}
	
	public UIForm(String id, METHOD  method ) {
		setId(id);
		this.method = method;
		
	}
	
	
	
	
		

	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}

	public void addElement(UIElement element ) {
		if (elements == null ) {
			elements = new ArrayList<UIElement> ();
		}
		elements.add(element);
	}
	
	public UIForm() {
		
	}
	
}

package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.controls.UINote;
import com.techtrade.rads.framework.utils.Utils;

public class UITableCol extends UIControl{
	
	List<UIElement> elements = null;
	String width;
	String colSpan ;
	String align;
	
	String sortField;


	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}
	
	public UITableCol() {
		
	}
	public UITableCol(UIElement element) {
		addElement(element);
	}
	
	
	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public UITableCol(UIElement element,String width) {
		addElement(element);
		this.width = width;
	}
	
	
	
	public UITableCol(String message) {
		addElement(new UIElement (new  UINote(message)));
	}
	public UITableCol(UIElement element,String width, String colSpan) {
		addElement(element);
		this.width = width;
		this.colSpan =colSpan;
		
	}
	public void addElement(UIElement element ) {
		if (elements ==null) {
			elements = new ArrayList<UIElement>();
		}
		elements.add(element);
	}
	
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		UITableCol col = new UITableCol();
		col.setStyle(this.getStyle());
		col.setAlign(this.getAlign());
		col.setDataProperty(this.getDataProperty());
		col.setColSpan(this.getDataProperty());
		if (!Utils.isNullList(this.getElements())) {
			for (UIElement element : this.getElements()) {
				UIElement newElement = (UIElement)element.clone();
				col.addElement(newElement);
			}
		}
		return col ;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getColSpan() {
		return colSpan;
	}

	public void setColSpan(String colSpan) {
		this.colSpan = colSpan;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
}

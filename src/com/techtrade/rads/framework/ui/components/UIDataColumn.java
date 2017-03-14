package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

public class UIDataColumn  {
	String title;
	String width;
	List<UIElement> elementImages;
	
	SortCriteria sortCriteria ;
	

	public UIDataColumn(){
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}

	public UIDataColumn(String title,String width) {
		this.title = title;
		this.width=width;
	}
	public List<UIElement> getElementImages() {
		return elementImages;
	}
	public void setElementImages(List<UIElement> elementImages) {
		this.elementImages = elementImages;
	}
	public void addElementImage(UIElement elementImage) {
		if (elementImages == null)
			elementImages = new ArrayList<UIElement>();
		elementImages.add(elementImage);
	}
	public SortCriteria getSortCriteria() {
		return sortCriteria;
	}
	public void setSortCriteria(SortCriteria sortCriteria) {
		this.sortCriteria = sortCriteria;
	}
	
	
	
	
  }
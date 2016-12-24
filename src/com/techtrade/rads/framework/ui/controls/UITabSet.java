package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UITabSet extends UIControl{

	List<UITab> tabs = null;
	String width ;
	
	
	String selectedTabStyle ;
	String unSelectedTabStyle ;
	

	public  UITabSet(String id) {
		setId(id);
	}
	public List<UITab> getTabs() {
		return tabs;
	}

	public void setTabs(List<UITab> tabs) {
		this.tabs = tabs;
	} 
	
	public void addTab(UITab tab) {
		if (tabs == null)
			tabs = new ArrayList<UITab>();
		tabs.add(tab);
	}
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getSelectedTabStyle() {
		return selectedTabStyle;
	}
	public void setSelectedTabStyle(String selectedTabStyle) {
		this.selectedTabStyle = selectedTabStyle;
	}
	public String getUnSelectedTabStyle() {
		return unSelectedTabStyle;
	}
	public void setUnSelectedTabStyle(String unSelectedTabStyle) {
		this.unSelectedTabStyle = unSelectedTabStyle;
	}
	
	

	
}

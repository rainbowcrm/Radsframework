package com.techtrade.rads.framework.ui.components;

public class PanelDef {
	String key;
	String style;
	String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public PanelDef(String key, String style) {
		super();
		this.key = key;
		this.style = style;
	} 
	
	public PanelDef(String key, String style, String id) {
		super();
		this.key = key;
		this.style = style;
		this.id= id ;
	} 
	
	
	public PanelDef() {
		
	}
	
	
	
	

}

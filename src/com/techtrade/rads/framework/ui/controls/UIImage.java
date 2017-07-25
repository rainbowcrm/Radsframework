package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIImage extends UIControl{
	private String width ;
	private String height ;
	private String src;
	private String onClick ;
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public UIImage(String id) {
		super(id);
	}
	public String getOnClick() {
		return onClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
	

}

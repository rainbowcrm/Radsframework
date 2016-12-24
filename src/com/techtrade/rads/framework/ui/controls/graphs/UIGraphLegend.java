package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.LinkedHashMap;
import java.util.Map;

public class UIGraphLegend {
	Map<String,UIGraphText> colorDescriptions;
	int x, y ;
	int height  ,width ;
	int xWidthPerEntries , yHeightPerEntries ;
	String borderColor;
	int constWidth , constHeight ;

	public Map<String, UIGraphText> getColorDescriptions() {
		return colorDescriptions;
	}

	public void setColorDescriptions(Map<String, UIGraphText> colorDescriptions) {
		this.colorDescriptions = colorDescriptions;
	}
	
	public void addColorDescription(String color, UIGraphText description){
		if (colorDescriptions  == null)
			colorDescriptions = new LinkedHashMap<String,UIGraphText> ();
		colorDescriptions.put(color, description);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public int getxWidthPerEntries() {
		return xWidthPerEntries;
	}

	public void setxWidthPerEntries(int xWidthPerEntries) {
		this.xWidthPerEntries = xWidthPerEntries;
	}

	public int getyHeightPerEntries() {
		return yHeightPerEntries;
	}

	public void setyHeightPerEntries(int yHeightPerEntries) {
		this.yHeightPerEntries = yHeightPerEntries;
	}

	public int getConstWidth() {
		return constWidth;
	}

	public void setConstWidth(int constWidth) {
		this.constWidth = constWidth;
	}

	public int getConstHeight() {
		return constHeight;
	}

	public void setConstHeight(int constHeight) {
		this.constHeight = constHeight;
	}
	
	
	
	
}

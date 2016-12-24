package com.techtrade.rads.framework.ui.controls.graphs;

import com.techtrade.rads.framework.ui.controls.graphs.UIBarChart.Direction;

public class UIGraphText {
	
	public enum Direction {
		HORIZONTAL , VERTICAL 
	} 
	Direction direction= Direction.HORIZONTAL ;

	int x,y;
	String fill;
	String transform ;
	String text ;
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
	public String getFill() {
		return fill;
	}
	public void setFill(String fill) {
		this.fill = fill;
	}
	public String getTransform() {
		return transform;
	}
	public void setTransform(String transform) {
		this.transform = transform;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UIGraphText(String text) {
		super();
		this.text = text;
	}
	public UIGraphText() {
		super();
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
}

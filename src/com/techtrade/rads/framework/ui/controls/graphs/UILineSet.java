package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.List;

public class UILineSet {

	List<UIGraphLine> lines;
	UIGraphText text;
	String lineStyle; 
	

	public List<UIGraphLine> getLines() {
		return lines;
	}

	public void setLines(List<UIGraphLine> lines) {
		this.lines = lines;
	}
	
	public void addLine(UIGraphLine line) {
		if  (lines ==null)
			 lines = new ArrayList<UIGraphLine>();
		lines.add(line);
	}

	public UIGraphText getText() {
		return text;
	}

	public void setText(UIGraphText text) {
		this.text = text;
	}

	public String getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(String lineStyle) {
		this.lineStyle = lineStyle;
	}
	
	
	
	

}

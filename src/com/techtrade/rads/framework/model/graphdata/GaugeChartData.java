package com.techtrade.rads.framework.model.graphdata;

import java.util.ArrayList;
import java.util.List;


public class GaugeChartData {

	public class ColorRange {
		int from , to ;
		String color;
		public int getFrom() {
			return from;
		}
		public void setFrom(int from) {
			this.from = from;
		}
		public int getTo() {
			return to;
		}
		public void setTo(int to) {
			this.to = to;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		
	}
	
	List<ColorRange> colorRanges ;
	int height,  width ;
	int minorTicks;
	int graphValue ;
	String label ;
	String title; 
	int maxValue ;

	public List<ColorRange> getColorRanges() {
		return colorRanges;
	}

	public void setColorRanges(List<ColorRange> colorRanges) {
		this.colorRanges = colorRanges;
	}
	public void addColorRange(ColorRange colorRange ) {
		if(colorRanges == null)
			 colorRanges = new ArrayList<ColorRange> ();
		colorRanges.add(colorRange);
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

	public int getMinorTicks() {
		return minorTicks;
	}

	public void setMinorTicks(int minorTicks) {
		this.minorTicks = minorTicks;
	}

	

	public int getGraphValue() {
		return graphValue;
	}

	public void setGraphValue(int graphValue) {
		this.graphValue = graphValue;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	
	
}

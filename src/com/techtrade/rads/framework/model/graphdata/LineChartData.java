package com.techtrade.rads.framework.model.graphdata;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.model.graphdata.BarChartData.Range;

public class LineChartData {

	List<String> intervals ;
	
	List <LineChartEntryData> entries;
	
	String borderColor; 

	String startingPoint  ;
	
	String title;
	String subTitle;
	
	public class Range {
		int xMin ,xMax;
		int yMin, yMax;
		public int getxMin() {
			return xMin;
		}
		public void setxMin(int xMin) {
			this.xMin = xMin;
		}
		public int getxMax() {
			return xMax;
		}
		public void setxMax(int xMax) {
			this.xMax = xMax;
		}
		public int getyMin() {
			return yMin;
		}
		public void setyMin(int yMin) {
			this.yMin = yMin;
		}
		public int getyMax() {
			return yMax;
		}
		public void setyMax(int yMax) {
			this.yMax = yMax;
		}
	}
	
	Range range ;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public List<String> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<String> intervals) {
		this.intervals = intervals;
	}
	public void addInterval(String interval) {
		if (intervals == null)
			 intervals = new ArrayList<String>();
		this.intervals.add(interval);
	}
	public List<LineChartEntryData> getEntries() {
		return entries;
	}

	public void setEntries(List<LineChartEntryData> entries) {
		this.entries = entries;
	} 
	
	public void addEntry(LineChartEntryData entry) {
		if (entries == null)
			entries = new ArrayList<LineChartEntryData>();
		
		this.entries.add(entry);
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	} 
	
	
	
	
}

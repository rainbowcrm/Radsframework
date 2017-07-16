package com.techtrade.rads.framework.model.graphdata;

import java.util.ArrayList;
import java.util.List;

public class BarChartData {

	String title;
	String subTitle;
	boolean useCoreChart = false; 
	
	public class Division {
		List <BarData> barDatas ;
		String divisionTitle;

		public List<BarData> getBarDatas() {
			return barDatas;
		}

		public void setBarData(List<BarData> barData) {
			this.barDatas = barData;
		}
		public void addBarData(BarData barData) {
			if (barDatas == null) {
				barDatas = new ArrayList();
			}
			barDatas.add(barData);
		}

		public String getDivisionTitle() {
			return divisionTitle;
		}

		public void setDivisionTitle(String divisionTitle) {
			this.divisionTitle = divisionTitle;
		}
		
		
		
	}
	
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
	List<Division> divisions;
	
	public Range getRange() {
		return range;
	}
	public void setRange(Range range) {
		this.range = range;
	}
	public List<Division> getDivisions() {
		return divisions;
	}
	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}
	public void addDivision(Division division) {
		if (divisions == null) {
			divisions = new ArrayList<> ();
		}
		divisions.add(division);
	}
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
	public boolean isUseCoreChart() {
		return useCoreChart;
	}
	public void setUseCoreChart(boolean useCoreChart) {
		this.useCoreChart = useCoreChart;
	}
	
	
}

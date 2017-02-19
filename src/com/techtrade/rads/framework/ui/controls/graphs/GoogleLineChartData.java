package com.techtrade.rads.framework.ui.controls.graphs;

import com.techtrade.rads.framework.model.graphdata.LineChartData;
import com.techtrade.rads.framework.model.graphdata.PieChartData;

public class GoogleLineChartData extends LineChartData {
	
	String title;
	String subTitle;
	
	int height;
	int width ;
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
	
	public static GoogleLineChartData makeGoogleChart(LineChartData lineChartData,int width, int height ) {
		GoogleLineChartData googleLineChart = new GoogleLineChartData();
		googleLineChart.setTitle(lineChartData.getTitle());
		googleLineChart.setSubTitle(lineChartData.getSubTitle());
		googleLineChart.setEntries(lineChartData.getEntries());
		googleLineChart.setIntervals(lineChartData.getIntervals());
		googleLineChart.setWidth(width);
		googleLineChart.setHeight(height);
		return  googleLineChart;
	}

}

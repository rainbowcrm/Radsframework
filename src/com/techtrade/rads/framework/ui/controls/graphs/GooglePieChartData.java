package com.techtrade.rads.framework.ui.controls.graphs;

import com.techtrade.rads.framework.model.graphdata.BarChartData;
import com.techtrade.rads.framework.model.graphdata.PieChartData;

public class GooglePieChartData extends PieChartData {
	
	String title;
	String subTitle;
	
	int height;
	int width;
	
	boolean ThreeD =true;
	
	
	public boolean isThreeD() {
		return ThreeD;
	}
	public void setThreeD(boolean threeD) {
		ThreeD = threeD;
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
	
	
	public static GooglePieChartData makeGoogleChart(PieChartData pieChartData,int width, int height ) {
		GooglePieChartData googlePieChart = new GooglePieChartData();
		googlePieChart.setTitle(pieChartData.getTitle());
		googlePieChart.setSubTitle(pieChartData.getSubTitle());
		googlePieChart.setPieSlices(pieChartData.getPieSlices());
		googlePieChart.setWidth(width);
		googlePieChart.setHeight(height);
		return googlePieChart;
	}

}

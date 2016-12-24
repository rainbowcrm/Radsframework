package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.model.graphdata.PieChartData;
import com.techtrade.rads.framework.model.graphdata.PieSliceData;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.utils.Utils;

public class UIPieChart extends UIControl{
	
	UIGraphCircle circle; 
	List<UIGraphPath> paths;
	String dataProvider;
	int width, height;
	UIGraphLegend legend ;
	int centerX, centerY, radius;
	UIGraphText footerNote ;
	
	public UIGraphCircle getCircle() {
		return circle;
	}
	public void setCircle(UIGraphCircle circle) {
		this.circle = circle;
	}
	public List<UIGraphPath> getPaths() {
		return paths;
	}
	public void setPaths(List<UIGraphPath> paths) {
		this.paths = paths;
	}
	public void addPath(UIGraphPath path) {
		if (paths == null)
			 paths = new ArrayList<UIGraphPath> ();
		paths.add(path);
	}
	
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public int getCenterX() {
		return centerX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public static UIPieChart makeUIPieChart(PieChartData pieCharData, int height, int width ,int centerX, int centerY , int radius) {
		UIPieChart pieChart = new UIPieChart();
		double total = 0;
		UIGraphCircle circle= new UIGraphCircle();
		UIGraphLegend legend = new UIGraphLegend();
		circle.setCX(centerX);
		circle.setCY(centerY);
		circle.setRadius(radius);
		circle.setFill("grey");
        double startAngle = 0;
        double endAngle = 0;
		int x1,x2,y1,y2 = 0;
		double sectorAngleArr[] =new double[pieCharData.getPieSlices().size()];
		int index = 0 ;
		pieChart.setCircle(circle);
		pieChart.setHeight(height);
		pieChart.setWidth(width);

		for (PieSliceData slice : pieCharData.getPieSlices() ) {
			total += slice.getVolume();
		}
		for (PieSliceData slice : pieCharData.getPieSlices() ) {
			double angle = Math.ceil(360 * slice.getVolume()/total);
			sectorAngleArr[index ++]=angle;
		}
		for (int i = 0 ; i < index ; i ++ ) {
			startAngle = endAngle; //1135388781
            endAngle = startAngle + sectorAngleArr[i];
            x1 =  (int)Math.round(centerX + radius*Math.cos(Math.PI*startAngle/180));
            y1 = (int)Math.round(centerY + radius*Math.sin(Math.PI*startAngle/180));
            x2 = (int)Math.round(centerX + radius*Math.cos(Math.PI*endAngle/180));
            y2 = (int)Math.round(centerY + radius*Math.sin(Math.PI*endAngle/180)); 
            int sweep = 0; 
            if (sectorAngleArr[i] >= 180)
            	 sweep =1; 
            String d = "M"+centerX +","+centerY+"  L" + x1 + "," + y1 + "  A"+radius+","+radius+" 0 "+ sweep+",1 " + x2 + "," + y2 + " z";
			UIGraphPath path = new UIGraphPath();
			String color = pieCharData.getPieSlices().get(i).getColor();
			String msg = pieCharData.getPieSlices().get(i).getText() ;
			path.setdParam(d);
			path.setFill(color);
			pieChart.addPath(path);
			UIGraphText graphText = new UIGraphText(msg);
			legend.addColorDescription(color, graphText);
		}
		legend.setBorderColor("blue");
		legend.setConstWidth(20);
		legend.setConstHeight(10);
		legend.setHeight(radius *2);
		legend.setWidth(radius * 2);
		int topleftX = centerX + radius + 20;
		int topLeftY = centerY  - ((radius-10));
		legend.setX(topleftX);
		legend.setY(topLeftY);
		legend.setxWidthPerEntries(0);
		legend.setyHeightPerEntries(((radius-10)*2)/(index-1));
		pieChart.setLegend(legend);
		if (!Utils.isNullString(pieCharData.getFooterNote()))  {
			UIGraphText graphText = new UIGraphText(pieCharData.getFooterNote());
			graphText.setX(centerX - (radius/2));
			graphText.setY(centerY +  radius + 30);
			pieChart.setFooterNote(graphText);
		}
		return pieChart;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public UIGraphLegend getLegend() {
		return legend;
	}
	public void setLegend(UIGraphLegend legend) {
		this.legend = legend;
	}
	public UIGraphText getFooterNote() {
		return footerNote;
	}
	public void setFooterNote(UIGraphText footerNote) {
		this.footerNote = footerNote;
	}
	
	
	

}

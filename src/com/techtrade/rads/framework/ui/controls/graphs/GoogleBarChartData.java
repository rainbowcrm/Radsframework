package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.techtrade.rads.framework.model.graphdata.BarChartData;
import com.techtrade.rads.framework.model.graphdata.BarData;

public class GoogleBarChartData {

	List<String> divisionTitles = new ArrayList<> ();;
	List<String> componentBarTitles  = new ArrayList<> ();
	
	Map <String , List<Double> > values = new HashMap<> ();;
	
	String title;
	String subTitle;
	
	int width,height;
	
	public List<String> getDivisionTitles() {
		return divisionTitles;
	}
	public void setDivisionTitles(List<String> divisionTitles) {
		this.divisionTitles = divisionTitles;
	}
	public List<String> getComponentBarTitles() {
		return componentBarTitles;
	}
	public void setComponentBarTitles(List<String> componentBarTitles) {
		this.componentBarTitles = componentBarTitles;
	}
		
	public Map<String, List<Double>> getValues() {
		return values;
	}
	public void setValues(Map<String, List<Double>> values) {
		this.values = values;
	}
	public static GoogleBarChartData makeGoogleChart(BarChartData barChartData,int width, int height ) {
		GoogleBarChartData chartData = new GoogleBarChartData();
		chartData.setTitle(barChartData.getTitle());
		chartData.setSubTitle(barChartData.getSubTitle());
		chartData.setHeight(height);
		chartData.setWidth(width);
		barChartData.getDivisions().forEach( division -> { 
			 division.getBarDatas().forEach( barData ->  { 
				 String text = barData.getText() ;
				 if(!chartData.getDivisionTitles().contains(text))
					 chartData.getDivisionTitles().add(text);
				 String legend = barData.getLegend();
				 if(!chartData.getComponentBarTitles().contains(legend))
					 chartData.getComponentBarTitles().add(legend);
				List<Double>  values = chartData.getValues().get(text);
				if(values == null)
					 values = new ArrayList<>();
				values.add(barData.getValue());
				chartData.getValues().put(text, values);
			 } );
		});
		return chartData;
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
	
	
	
}

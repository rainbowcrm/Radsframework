package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.model.graphdata.GaugeChartData;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.model.graphdata.GaugeChartData.ColorRange;

public class UIGaugeChart extends UIControl{

	List<ColorRange> colorRanges ;
	int height,  width ;
	int minorTicks;
	int graphValue ;
	String label ;
	int maxValue ;
	String dataProvider;
	
	GaugeChartData gaugeChartData ;

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

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public static UIGaugeChart makeUIGaugeChart (GaugeChartData data, int width, int height )
	{
		UIGaugeChart uiChart = new UIGaugeChart();
		data.setHeight(height);
		data.setWidth(width);
		uiChart.setWidth(width);
		uiChart.setHeight(height);
		uiChart.setMinorTicks(data.getMinorTicks());
		uiChart.setMaxValue(data.getMaxValue());
		uiChart.setColorRanges(data.getColorRanges());
		uiChart.setLabel(data.getLabel());
		uiChart.setGraphValue(data.getGraphValue());
		uiChart.setGaugeChartData(data);
		return uiChart ;
	}

	public GaugeChartData getGaugeChartData() {
		return gaugeChartData;
	}

	public void setGaugeChartData(GaugeChartData gaugeChartData) {
		this.gaugeChartData = gaugeChartData;
	}
	
	
}

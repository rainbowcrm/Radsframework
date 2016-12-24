package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.techtrade.rads.framework.model.graphdata.BarChartData;
import com.techtrade.rads.framework.model.graphdata.BarData;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.utils.Utils;

public class UIBarChart extends UIControl{
	
	public enum Direction {
		HORIZONTAL , VERTICAL 
	} 
	
	Direction direction= Direction.VERTICAL ;
	

	int width, height;
	int barSectionHeight ;
	int barWidth ;
	int noYAxisDivisions;
	int startX, startY ;
	int marginWidth ;
	String title;
	String desc ;
	String dataProvider;
	List<UIGraphBar> bars;
	SortedSet<Integer> valueRanges; 

	public List<UIGraphBar> getBars() {
		return bars;
	}

	public void setBars(List<UIGraphBar> bars) {
		this.bars = bars;
	} 
	
	public void addBar(UIGraphBar bar) {
		if (bars == null) {
			bars= new ArrayList<UIGraphBar> ();
		}
		bars.add(bar);
	}
	
	

	public int getNoYAxisDivisions() {
		return noYAxisDivisions;
	}

	public void setNoYAxisDivisions(int noYAxisDivisions) {
		this.noYAxisDivisions = noYAxisDivisions;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
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
	

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}
	

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	/*
	 *  rangeHeight = 100
	 *  val = 2000 
	 *  maxVal = 3000
	 *  
	 *  2000 * 100 / 3000 = 200/3 = 66
	 *  val  * rangeHeight / maxVal
	 *  
	 *  rangeWidth / noDivision * 
	 */

	public int getBarWidth() {
		return barWidth;
	}

	public void setBarWidth(int barWidth) {
		this.barWidth = barWidth;
	}

	public int getBarSectionHeight() {
		return barSectionHeight;
	}

	public void setBarSectionHeight(int barSectionHeight) {
		this.barSectionHeight = barSectionHeight;
	}

	public int getMarginWidth() {
		return marginWidth;
	}

	public void setMarginWidth(int marginWidth) {
		this.marginWidth = marginWidth;
	}
	public SortedSet<Integer> getValueRanges() {
		return valueRanges;
	}
	public void setValueRanges(SortedSet<Integer> valueRanges) {
		this.valueRanges = valueRanges;
	}
	public void addValueRange(Integer valueRange) {
		if (valueRanges == null) {
			valueRanges = new TreeSet<Integer> ();
		}
		valueRanges.add(valueRange);
	}

	public static UIBarChart makeUIBarChart(BarChartData barChartData , int rangeWidth, int rangeHeight ,int xStart , int yStart ,
			int marginWidth ,int barWidth, int noYAxisDivisions ) {
		UIBarChart uiBarChart = new  UIBarChart();
		uiBarChart.setWidth(rangeWidth);
		uiBarChart.setHeight(rangeHeight);
		uiBarChart.setStartX(xStart);
		uiBarChart.setStartY(yStart);
		uiBarChart.setBarWidth(barWidth);
		uiBarChart.setMarginWidth(marginWidth);
		uiBarChart.setNoYAxisDivisions(noYAxisDivisions);
		int xMin= barChartData.getRange().getxMin();
		int yMin =barChartData.getRange().getyMin();
		int xMax = barChartData.getRange().getxMax();
		int yMax = barChartData.getRange().getyMax();
		int barRange = (rangeHeight  * 2 /3 );
		uiBarChart.setBarSectionHeight(barRange);
		int noDivisions = barChartData.getDivisions().size() ;
		int widthDivision = rangeWidth / noDivisions; 
		
		int curX = xStart + (marginWidth * 3);
		for (BarChartData.Division division : barChartData.getDivisions() ) {
			for (BarData data : division.getBarDatas() ) {
				UIGraphBar bar  = new UIGraphBar();
				bar.setX(curX);
				double val = data.getValue();
				uiBarChart.addValueRange((Integer.valueOf((int)val)));
				int propVal = (int)((val  * barRange )/  yMax );
			//	int propWidth = divisionWidth /  ((division.getBarDatas().size()  *  3 ));
				//int propWidth =rangeWidth / (noDivisions * division.getBarDatas().size() * 2);
				int propWidth = barWidth;
				bar.setHeight(propVal );
				bar.setY((barRange-propVal)  + yStart);
				bar.setFill(data.getColor());
				UIGraphText graphText= new UIGraphText ();
				if (!Utils.isNullString(data.getText())) {
					graphText.setText(data.getText());
					graphText.setX(curX + (propWidth * division.getBarDatas().size()) /2);
					graphText.setY(barRange+2+yStart);
					graphText.setFill(data.getTextColor());
					graphText.setTransform("rotate(90 " + curX + ","+ curX + ")");
					graphText.setDirection(UIGraphText.Direction.VERTICAL);
					bar.setText(graphText);
				}
				bar.setWidth(propWidth);
				uiBarChart.addBar(bar);
				curX+=barWidth;
				
			}
			curX += marginWidth;
		}
		
		return uiBarChart;
	}
	
	
	
	
}

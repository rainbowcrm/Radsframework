package com.techtrade.rads.framework.ui.controls.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.techtrade.rads.framework.model.graphdata.LineChartData;
import com.techtrade.rads.framework.model.graphdata.LineChartEntryData;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.utils.Utils;

public class UILineChart  extends UIControl{

	int startX, startY ;
	int width, height;
	int canvasHeight, canvasWidth ;
	String title;
	String dataProvider;
	String desc ;
	
	SortedSet<Integer> valueRanges;
	int marginWidth ;
	int marginHeight ;
	
	int noYAxisDivisions,noXAxisDivisions;
	List<Division> divisions ;
	List<UILineSet> lineSets ;
	String borderColor ; 
	UIGraphLegend legend; 
		
	public class Division {
		int x, y ;
		UIGraphText text;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public UIGraphText getText() {
			return text;
		}
		public void setText(UIGraphText text) {
			this.text = text;
		}
		
	}
	
	public List<Division> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}
	
	public void addDivision(Division division) {
		if (divisions == null)
			divisions = new ArrayList<Division>();
		this.divisions.add(division);
	}



	public List<UILineSet> getLineSets() {
		return lineSets;
	}

	public void setLineSets(List<UILineSet> lineSets) {
		this.lineSets = lineSets;
	}

	public void addLineSet(UILineSet lineSet) {
		if (lineSets == null)
			lineSets = new ArrayList<UILineSet>();
		this.lineSets.add(lineSet);
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

	public SortedSet<Integer> getValueRanges() {
		return valueRanges;
	}

	public void setValueRanges(SortedSet<Integer> valueRanges) {
		this.valueRanges = valueRanges;
	}
	
	public static UILineChart makeUILineChart(LineChartData chartData,int rangeWidth, int rangeHeight ,int xStart , int yStart,
			int marginWidth,int marginHeight,int canvasWidth , int canvasHeight) {
		UILineChart chart = new UILineChart();
		UIGraphLegend legend = new UIGraphLegend();
		chart.setHeight(rangeHeight);
		chart.setWidth(rangeWidth);
		chart.setStartX(xStart + marginWidth);
		chart.setStartY(rangeHeight - yStart - marginHeight);
		chart.setMarginWidth(marginWidth);
		chart.setMarginHeight(marginHeight);
		chart.setBorderColor(chartData.getBorderColor());
		chart.setCanvasHeight(canvasHeight);
		chart.setCanvasWidth(canvasWidth);
		int noIntervals = chartData.getIntervals().size() ;
		int noDataMembers = chartData.getEntries().size();
		int intervalWidth = (rangeWidth- marginWidth)/ noIntervals;
		/*int xMin= chartData.getRange().getxMin();
		int yMin =chartData.getRange().getyMin();
		int xMax = chartData.getRange().getxMax();*/
		int yMax = chartData.getRange().getyMax();
		int barRange = (rangeHeight  * 2 /3 );
		boolean divisionsSet= false; 
		boolean startintPointSet = false ;
		for (LineChartEntryData entry : chartData.getEntries()) {
			UILineSet lineSet = new  UILineSet();
			lineSet.setLineStyle("stroke:"+entry.getColor() +";stroke-width:1");
			UIGraphText graphText = new UIGraphText(entry.getText());
			legend.addColorDescription(entry.getColor(), graphText);
			int curX = xStart + marginWidth ;
			int curY= rangeHeight - yStart - marginHeight;
			if(!Utils.isNullString(chartData.getStartingPoint()) &&  !startintPointSet) {
				UILineChart.Division division  =chart.new Division();
				division.setX(curX);
				division.setY(rangeHeight-(marginHeight/2));
				division.setText(new UIGraphText(chartData.getStartingPoint()));
				chart.addDivision(division);
			}
			startintPointSet = true;
			curX += intervalWidth;
			for (String interval : chartData.getIntervals()) {
				Map valueMap = entry.getValueMap();
				Double value = (Double)valueMap.get(interval);
				int propVal = 0;
				if (value != null)
					propVal = (int)((value  * barRange )/  yMax );
				UIGraphLine line = new UIGraphLine();
				line.setX1(curX-intervalWidth);
				line.setY1(curY);
				int x2=curX;
				line.setX2(x2);
				int y2= rangeHeight - propVal - marginHeight;
				line.setY2(y2);
				lineSet.addLine(line);
				if (!divisionsSet) {
					UILineChart.Division division  =chart.new Division();
					division.setX(curX);
					division.setY(rangeHeight-(marginHeight/2));
					division.setText(new UIGraphText(interval));
					chart.addDivision(division);
				}
				curX=x2+intervalWidth;
				curY=y2;
			}

			divisionsSet =true;
			lineSet.setText(new UIGraphText(entry.getText()));
			chart.addLineSet(lineSet);
		}
		
		legend.setBorderColor("blue");
		legend.setConstWidth(20);
		legend.setConstHeight(10);
		int topleftX = chart.getWidth() + (chart.getMarginWidth() );
		int topLeftY = chart.getMarginHeight();
		legend.setX(topleftX);
		legend.setY(topLeftY);
		legend.setxWidthPerEntries(0);
		legend.setyHeightPerEntries((int)(chart.getHeight() / (chartData.getEntries().size() + 1)));
		chart.setLegend(legend);
		
		return chart;
	}
	
	
	public void addValueRange(Integer valueRange) {
		if (valueRanges == null)
			valueRanges = new TreeSet<Integer>();
		this.valueRanges.add( valueRange);
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

	public int getMarginWidth() {
		return marginWidth;
	}

	public void setMarginWidth(int marginWidth) {
		this.marginWidth = marginWidth;
	}

	
	public int getMarginHeight() {
		return marginHeight;
	}

	public void setMarginHeight(int marginHeight) {
		this.marginHeight = marginHeight;
	}

	public int getNoYAxisDivisions() {
		return noYAxisDivisions;
	}

	public void setNoYAxisDivisions(int noYAxisDivisions) {
		this.noYAxisDivisions = noYAxisDivisions;
	}

	public int getNoXAxisDivisions() {
		return noXAxisDivisions;
	}

	public void setNoXAxisDivisions(int noXAxisDivisions) {
		this.noXAxisDivisions = noXAxisDivisions;
	}

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public UIGraphLegend getLegend() {
		return legend;
	}

	public void setLegend(UIGraphLegend legend) {
		this.legend = legend;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}
	
	
	
	
}

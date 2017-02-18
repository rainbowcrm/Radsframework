package com.techtrade.rads.framework.ui.writers;

import java.io.IOException;
import java.io.PrintWriter;



import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.ui.controls.graphs.GoogleBarChartData;
import com.techtrade.rads.framework.ui.controls.graphs.UIBarChart;
import com.techtrade.rads.framework.utils.Utils;

public class GoogleChartWriter {

	public static void writeBarChart(PrintWriter out, GoogleBarChartData chart, Object value,ViewController controller) throws IOException {
		out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		out.println(" <script type=\"text/javascript\">");
		out.println(" google.charts.load('current', {'packages':['bar']});");
		out.println("  google.charts.setOnLoadCallback(drawChart); ");
		out.println(" function drawChart() { ");
		out.println(" var data = google.visualization.arrayToDataTable([ ");
		out.println( getTitles(chart) + "," );
		for ( int  i = 0 ; i < chart.getDivisionTitles().size() ; i ++  ) {
			String comma = (i < chart.getDivisionTitles().size()-1)?",":"";
			out.println( getData(chart, i) + comma);
		}
		out.println("]);");
		
		out.println("var options = {");
				out.println(" 	chart: {");
						out.println("  	title: '" +chart.getTitle() + "',");
								out.println(" subtitle: '" + chart.getSubTitle() + "',");
						out.println(" },");
				out.println("bars: 'vertical' ");
				out.println("};");
				
		out.println("var chart = new google.charts.Bar(document.getElementById('barchart_material'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		out.println("<div id=\"barchart_material\" style=\"width: " + chart.getWidth()+ "px; height: "+ chart.getHeight()+ "px;\"></div>");
		  
		 
	}
	private static String getData (GoogleBarChartData chart, int index)
	{
		String title = chart.getDivisionTitles().get(index) ;
		StringBuffer ret = new StringBuffer(" [' " +title +"'," ) ;
		String data   =Utils.getCommaSeperatedArray(chart.getValues().get(title)," ");
		ret.append(data);
		ret.append(" ] ");
		return ret.toString();
	}
	
	private static String getTitles (GoogleBarChartData chart)
	{
		StringBuffer ret = new StringBuffer(" [ 'Options',") ;
		String values = Utils.getCommaSeperatedArray(chart.getComponentBarTitles(), "'");
		ret.append(values);
		ret.append("]");
		return ret.toString();
		
	}
}

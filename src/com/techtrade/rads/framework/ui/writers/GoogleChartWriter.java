package com.techtrade.rads.framework.ui.writers;

import java.io.IOException;
import java.io.PrintWriter;







import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.model.graphdata.LineChartEntryData;
import com.techtrade.rads.framework.model.graphdata.PieSliceData;
import com.techtrade.rads.framework.ui.controls.graphs.GoogleBarChartData;
import com.techtrade.rads.framework.ui.controls.graphs.GoogleLineChartData;
import com.techtrade.rads.framework.ui.controls.graphs.GooglePieChartData;
import com.techtrade.rads.framework.ui.controls.graphs.UIBarChart;
import com.techtrade.rads.framework.utils.Utils;

public class GoogleChartWriter {

	public static void writeBarChart(PrintWriter out, GoogleBarChartData chart, Object value,ViewController controller, String id) throws IOException {
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
				
		out.println("var chart = new google.charts.Bar(document.getElementById('"+ id +"'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		out.println("<div id=\""+id+ "\" style=\"width: " + chart.getWidth()+ "px; height: "+ chart.getHeight()+ "px;\"></div>");
		  
	}
	
	
	public static void writeLineChart(PrintWriter out, GoogleLineChartData chart, Object value,ViewController controller,String id) throws IOException {
		out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		out.println(" <script type=\"text/javascript\">");
		out.println(" google.charts.load('current', {'packages':['line']});");
		out.println("  google.charts.setOnLoadCallback(drawChart); ");
		out.println(" function drawChart() { ");
		out.println(" var data = new google.visualization.DataTable();");
		out.println(" data.addColumn('string','entries');");
		for ( int  i = 0 ; i < chart.getEntries().size() ; i ++  ) {
			LineChartEntryData lineChartEntry = chart.getEntries().get(i) ;
			out.println(" data.addColumn('number','"+lineChartEntry.getText() +"');"); 
		}
		out.println("  data.addRows([ ");

		for (int j =0 ; j < chart.getIntervals().size() ; j ++ ) {
			String outercomma = (j < chart.getIntervals().size()-1)?",":"";
			String interval = chart.getIntervals().get(j);
			out.print("['" + interval  +"',");
			for ( int  i = 0 ; i < chart.getEntries().size() ; i ++  ) {
				String comma = (i < chart.getEntries().size()-1)?",":"";
				LineChartEntryData lineChartEntry = chart.getEntries().get(i) ;
				Double plottedValue = lineChartEntry.getValueMap().get(interval);
				double pltVal = 0;
				if(plottedValue != null)
					pltVal =  plottedValue.doubleValue();
				out.print(pltVal  + comma);
			}
			out.println("]" + outercomma);
		}
		out.println("]);");
		out.println("var options = {");
		out.println(" 	chart: {");
				out.println("  	title: '" +chart.getTitle() + "',");
						out.println(" subtitle: '" + chart.getSubTitle() + "',");
				out.println(" },");
		out.println("width:"  + chart.getWidth() + ",");
		out.println("height:"  + chart.getHeight() + ",");
		out.println("axes: {" );
		out.println("x: {" );
		out.println("0: {side: 'top'}" );
		out.println(" }" );
		out.println(" }" );
		out.println("};");
		
		out.println("var chart = new google.charts.Line(document.getElementById('"+ id +"'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		out.println("<div id=\""+id+ "\" style=\"width: " + chart.getWidth()+ "px; height: "+ chart.getHeight()+ "px;\"></div>");
	}
	
	public static void writePieChart(PrintWriter out, GooglePieChartData chart, Object value,ViewController controller,String id) throws IOException {
		out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		out.println(" <script type=\"text/javascript\">");
		out.println(" google.charts.load('current', {'packages':['corechart']});");
		out.println("  google.charts.setOnLoadCallback(drawChart); ");
		out.println(" function drawChart() { ");
		out.println(" var data = google.visualization.arrayToDataTable([ ");
		out.println( "['Options','Values']," );
		for ( int  i = 0 ; i < chart.getPieSlices().size() ; i ++  ) {
			String comma = (i < chart.getPieSlices().size()-1)?",":"";
			PieSliceData pieSlicdeData  =(PieSliceData)chart.getPieSlices().get(i); 
			out.println( "['"  + pieSlicdeData.getText() + "',"  + pieSlicdeData.getVolume() + "]" + comma);
			
		}
		out.println("]);");
		
		out.println("var options = {");
		out.println("  	title: '" +chart.getTitle() + "'" + (chart.isThreeD()?",":""));
		if(chart.isThreeD())
			out.println("is3D: true");
		out.println("};");
				
		out.println("var chart = new google.visualization.PieChart(document.getElementById('"+id+"'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		out.println("<div id=\""+id+"\" style=\"width: " + chart.getWidth() + "px; height: "+ chart.getHeight()+ "px;\"></div>");
		  
	}
	
	public static void writeDonutChart(PrintWriter out, GooglePieChartData chart, Object value,ViewController controller,String id) throws IOException {
		out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		out.println(" <script type=\"text/javascript\">");
		out.println(" google.charts.load('current', {'packages':['corechart']});");
		out.println("  google.charts.setOnLoadCallback(drawChart); ");
		out.println(" function drawChart() { ");
		out.println(" var data = google.visualization.arrayToDataTable([ ");
		out.println( "['Options','Values']," );
		for ( int  i = 0 ; i < chart.getPieSlices().size() ; i ++  ) {
			String comma = (i < chart.getPieSlices().size()-1)?",":"";
			PieSliceData pieSlicdeData  =(PieSliceData)chart.getPieSlices().get(i); 
			out.println( "['"  + pieSlicdeData.getText() + "',"  + pieSlicdeData.getVolume() + "]" + comma);
			
		}
		out.println("]);");
		
		out.println("var options = {");
		out.println("  	title: '" +chart.getTitle() + "',");
		out.println("pieHole: 0.4");
		out.println("};");
				
		out.println("var chart = new google.visualization.PieChart(document.getElementById('"+id+"'));");
		out.println("chart.draw(data, options);");
		out.println("}");
		out.println("</script>");
		
		out.println("<div id=\""+id+"\" style=\"width: " + chart.getWidth() + "px; height: "+ chart.getHeight()+ "px;\"></div>");
		  
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

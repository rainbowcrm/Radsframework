package com.techtrade.rads.framework.ui.writers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import com.techtrade.rads.framework.controller.abstracts.IExternalizeFacade;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UILookupPage;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UILookupText;
import com.techtrade.rads.framework.ui.controls.UIMenu;
import com.techtrade.rads.framework.ui.controls.UITab;
import com.techtrade.rads.framework.ui.controls.UITabSet;
import com.techtrade.rads.framework.ui.controls.graphs.UIBarChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIGaugeChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphPath;
import com.techtrade.rads.framework.ui.controls.graphs.UILineChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIPieChart;
import com.techtrade.rads.framework.utils.Utils;

public class BootstrapWriter  extends  HTMLWriter{

	String bootstrapPath ;
	
	public BootstrapWriter(Object board) {
		super(board);
	}

	public String getBootstrapPath() {
		return bootstrapPath;
	}

	public void setBootstrapPath(String bootstrapPath) {
		this.bootstrapPath = bootstrapPath;
	}


	protected void writeTable(PrintWriter out, UITable table, Object value,ViewController controller) throws IOException,CloneNotSupportedException  {
		out.println("<table id=\"" + table.getId() + "\" class=\"table table-condensed " +table.getStyle() + "\">");
		if (! Utils.isNullList(table.getHeaders())){
			for (UITableHead head : table.getHeaders()) {
				writeElement(new UIElement(head), value,controller);
			}
		}
		if (! Utils.isNullList(table.getInnerRows())){
			for (UITableRow row : table.getInnerRows() ) {
					if (value!= null &&  List.class.isAssignableFrom(value.getClass())   )  {
						if (((List)value).size() > 0 ) {
							for (Object ob : (List)value ) {
								UIElement newRow = new UIElement((UITableRow)row.clone());
								newRow.setValue(ob);
								newRow.setRendered(row.getRendered());
								writeElement(newRow, ob,controller);
							}
						}else {
							writeElement(new UIElement(row), null,controller);
						}
						
					}else if (value!= null &&  Set.class.isAssignableFrom(value.getClass())   )  { 
						if (((Set)value).size() > 0 ) {
							for (Object ob : (Set)value ) {
								UIElement newRow = new UIElement((UITableRow)row.clone());
								newRow.setValue(ob);
								newRow.setRendered(row.getRendered());
								writeElement(newRow, ob,controller);
							}
						}else {
							UIElement newRow=new UIElement(row) ;
							newRow.setRendered(row.getRendered());
							writeElement(newRow, null,controller);
						}
					}else  {
						writeElement(new UIElement(row), value,controller);
					}
				
			}
		}
		if (!Utils.isNullList(table.getFooters())){
			for (UITableFooter footer: table.getFooters()) {
				writeElement(new UIElement(footer), value,controller);
			}
		}
		out.println("</table>");
	}

	
	public void writeStyleSheet(PrintWriter out,UIPage page) {
		writeMetaData(out, page);
		page.getStyleSheets().forEach( styleSheet -> { 
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + styleSheet + "\">");	
		});		
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"); 
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap-flex.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap-grid.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap-reboot.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap-datepicker.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/font-awesome-4.7.0/css/font-awesome.min.css\">");
		
		out.println("<script src=\"https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js\"></script>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js\"></script>");
			/*out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");*/
		
	}
	
	
	
	@Override
	protected void writeLineChart(PrintWriter out, UILineChart chart,Object value, ViewController controller) throws IOException {
		if (!chart.isUseGoogleChart() )
			super.writeLineChart(out, chart, value, controller);
		else
			GoogleChartWriter.writeLineChart(out, chart.getGoogleLineChartData(), value, controller,chart.getId());
	}

	@Override
	protected void writeGaugeChart(PrintWriter out, UIGaugeChart chart,
			Object value, ViewController controller) throws IOException {
		GoogleChartWriter.writeGaugeChart(out, chart.getGaugeChartData(), value, controller, chart.getId());
	}

	protected void writePieChart(PrintWriter out, UIPieChart chart, Object value,ViewController controller) throws IOException {
		if (!chart.isUseGoogleChart() )
			super.writePieChart(out, chart, value, controller);
		else if (!chart.isDonutChart()) {
			GoogleChartWriter.writePieChart(out, chart.getGooglePieChartData(), value, controller,chart.getId());
		}else 
			GoogleChartWriter.writeDonutChart(out, chart.getGooglePieChartData(), value, controller,chart.getId());
	}
	

	protected void writeBarChart(PrintWriter out, UIBarChart chart, Object value,ViewController controller) throws IOException {
		if (!chart.isUseGoogleChart() )
			super.writeBarChart(out, chart, value, controller);
		else  {
		
			if (!chart.isUseCoreChart())
				GoogleChartWriter.writeBarChart(out, chart.getGoogleBarChartData(), value, controller,chart.getId());
			else
				GoogleChartWriter.writeCoreChart(out, chart.getGoogleBarChartData(), value, controller,chart.getId());
		}
		
		
	}
	
	protected void writeDate(PrintWriter out, UIDate dateC) throws ParseException, IOException {
		
		String dataProp = "data-property=\"" + dateC.getDataProperty() + "\""; 
		/*out.println("<input type =\"date\" id=\"" + dateC.getId() + "\"  name =\"" +dateC.getId()  + "\"  "  + dataProp +  " value=\""+ 
				Utils.dateToString((java.util.Date)dateC.getValue(),"yyyy-MM-dd") +"\"/>");*/
		
	/*String dtControl = " <div class=\"container\">" + 
	   " <div class=\"row\">" +
	    "    <div class='col-sm-6'>" + 
	           " <div class=\"form-group\"> " +
	               " <div class='input-group date' width=\"5\" id='"+dateC.getId()+"'"  + dataProp + " value=\" " + Utils.dateToString((java.util.Date)dateC.getValue(),"mm-dd-yyyy") + " \"> " +
	                    " <input type='text' class=\"form-control\" /> " +
	                   " <span class=\"input-group-addon\"> " +
	                   "      <span class=\"glyphicon glyphicon-calendar\"></span> " +
	                   "   </span> " + 
	               "  </div> " +
	         "   </div> " +
	         " </div> " + 
	        "  <script type=\"text/javascript\"> " +
	         "   $(function () { " + 
	               "  $('#datetimepicker1').datetimepicker(); " +
	           " }); " +
	       " </script> " +
	  "  </div> " +
	" </div> ";*/
	
	/*String dtControl =  //" <div class=\"form-group\"> " +
			                    " <input type='text' width=\"5\" id='"+dateC.getId()+"'"  + dataProp + " value=\" " + Utils.dateToString((java.util.Date)dateC.getValue(),"mm-dd-yyyy") + " \"  /> " +
			                    		   " <span class=\"input-group-addon\"> " +
			                   "      <span class=\"glyphicon glyphicon-calendar\"></span> " +
			                   "   </span> " + 
			           //    " </div> " + 
			        "  <script type=\"text/javascript\"> " +
			         "   $(function () { " + 
			               "  $('#"+ dateC.getId() +"').datetimepicker(); " +
			           " }); " +
			       " </script> " ;

	
		
	out.println(dtControl);*/
		
		super.writeDate(out, dateC);
	}

	/*protected void writeLookupText(PrintWriter out, UILookupText textLookup) throws IOException {
		String sizeStr = textLookup.getSize()>0?"size=\"" + textLookup.getSize()+"\"":"" ; 
		String dataProp = "data-property=\"" + textLookup.getDataProperty() +  "\"";
		out.println("<input type =\"text\" id=\"" + textLookup.getId() + "\" name =\"" + textLookup.getId()  + "\" "  + dataProp  +  " value=\""+ 
				Utils.getFormattedValue(textLookup.getValue()) +"\" " + sizeStr +" />");
	//String img = "<img src=\"" + textLookup.getImgSrc() + "\" width =\"30\" height =\"30\" >";
		
		String height = textLookup.getWindowHeight() ;
		String width =textLookup.getWindowWidth() ;
		String title= textLookup.getLookupWindowTitle();
		String urlText = textLookup.getUrl() + "&lookupType=" + textLookup.getLookupType() + "&parentControl=" +  textLookup.getId();
		//out.println("<button type =\"submit\"  src =\"" + textLookup.getImgSrc() + "\" onClick=\"showLookupWindow('"+textLookup.getId()+"');\" > </button>");
		if (textLookup.isShowLookupAsDialog()) {
			writeLookupDialog(out, textLookup);
			out.println("<span   name=\"btn+"+ textLookup.getId() + "\"  class=\"glyphicon glyphicon-search\" onClick=\"showLookupDialog('" + textLookup.getDialogId()  + "',this);\" >" +" </span>");
		}else {
			out.println("<span name=\"btn+"+ textLookup.getId() + "\"  class=\"glyphicon glyphicon-search\" onClick=\"showLookupWindow('"+ urlText +"','"+title+"','"+textLookup.getId()+"','"+height+"','"+ width+"');\" >"  +" </span>");
		}
		
		
	
	}*/

protected void writeTabSet(PrintWriter out, UITabSet tabSet,Object value,ViewController controller) throws IOException {
	String width = !Utils.isNullString(tabSet.getWidth())?"width =\""+tabSet.getWidth() +"\"":"";
	String style = (!Utils.isNullString(tabSet.getStyle()) ? tabSet.getStyle() : "");
	String titleStyleUnSelected  = tabSet.getUnSelectedTabStyle() ;
	String titleStyleSelected =  tabSet.getSelectedTabStyle() ;
	out.println("<div class='panel with-nav-tabs panel-default' " + width + "  >");
	//	out.println("<div id= \""+ tabSet.getId() + "\"" +  style + ">");
	out.println("<div class='panel-heading'>");
	out.println("<ul class='nav nav-tabs'>");
	int count = 0 ;
	for(UITab tab :  tabSet.getTabs()) {

		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		String labelValue =  facade.externalize(context, tab.getLabel().getLabel());
		String active = "";
		if (count == 0 )
			active = "class =\"active\"";
		out.println("<li " + active +" >");
		out.println("<a href=\"#"+tab.getId()+"\" data-toggle=\"tab\">" + labelValue + "</a>"  );
		out.println("</li>");
		/*out.println("<span id=\"SP" + tab.getId()+ "\"  class = \""+ ((count==0)?titleStyleSelected:titleStyleUnSelected) + 
				"\" onClick =\"toggleTabVisbility('"+ tab.getId()+"','SP"+ tab.getId() +"','"+  titleStyleSelected+"','" + titleStyleUnSelected +"');\"> " 
				+ labelValue + "</span>");
		*/count ++;
	}
	out.println("</ul></div>");
	int index = 0;
	out.println("<div class='panel-body'> ");
	out.println("<div class='tab-content'> ");
	for(UITab tab :  tabSet.getTabs()) {
		/*tab.setWidth(width);
		//tab.setStyle(tabSet.getStyle());
		if ( index == 0 )
			tab.setVisible(true);
		index ++;
		writeElement(new UIElement(tab),value,controller);*/
		tab.setIndex(index ++ );
		writeTab(out, tab, value, controller);
	}

	out.println("</div></div></div>");
	//out.println("</div>");
}

protected void writeTab(PrintWriter out, UITab tab,Object value,ViewController controller) throws IOException {
	
	String vb =  (tab.isVisible())?"":"none" ;
	String width = tab.getWidth() ;
	String style = (!Utils.isNullString(tab.getStyle()) ? "class=\"" + tab.getStyle() + "\"" : "");
	String classStyle = "class=\"tab-pane fade\"";
	if(tab.getIndex() == 0)
		classStyle = "class=\"tab-pane fade in active\"";
	out.println("<div id= \""+ tab.getId() + "\" " + classStyle  + "  >\n");
	out.println("<div id= \"inner"+ tab.getId() + "\" " + style  + "  >\n");
	for (UIElement element : tab.getElements() ) {
		writeElement(element,value,controller);
	}
	out.println("</div>");
	out.println("</div>");
	
}


protected void writeMenu(PrintWriter out, UIMenu menu) throws IOException {
	String menuText = menu.getMenuText();
	if(menu.isExternalize()) {
		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		menuText =  facade.externalize(context, menuText);
	}
	String style = (!Utils.isNullString(menu.getStyle()) ? "class=\"" + menu.getStyle() + "\"" : "");
	//String textStyle = (!Utils.isNullString(menu.getTextStyle()) ? "class=\"" + menu.getTextStyle() + "\"" : "");
	String menuClick = "\"window.location.href ='"+ menu.getMenuLink() +"'\"" ;
	String groupId= menu.getGroupId() ;
	if (!Utils.isNullList(menu.getChildMenus())) {
		menuClick = "toggleMenuVisibility('" + groupId +"')  ;" ;
	}
	out.println("<li id=\"" + menu.getId()+ "\" "+ style +" onClick=" + menuClick +">" +  menuText);
	if (Utils.isNullList(menu.getChildMenus())) {
		out.println("</li>");
		return ;
	}
	
	out.println("<ul id = \""+ groupId +"\"" +  " style=\"display:none\">") ;
	for (UIMenu childMenu :  menu.getChildMenus()) {
		writeMenu(out,childMenu) ;
	}
	out.println("</ul>") ;
	out.println("</li>");

}
	
}

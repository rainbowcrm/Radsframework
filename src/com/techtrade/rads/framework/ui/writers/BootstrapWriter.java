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
import com.techtrade.rads.framework.ui.constants.RadsControlConstants;
import com.techtrade.rads.framework.ui.controls.*;
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
		out.println("<table id=\"" + table.getId() + "\" class=\"table table-bordered table-hover table-striped " +table.getStyle() + "\">");
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
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/css/bootstrap.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" +bootstrapPath  + "/font-awesome/css/font-awesome.min.css\">");
		out.println("<script src=\"" +bootstrapPath  + "/js/jquery.js\"></script>");
		out.println("<script src=\"" +bootstrapPath  + "/js/bootstrap.min.js\"></script>");

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

	protected void writeMenuSet(PrintWriter out, UIMenuSet menuSet) throws IOException {


		out.println("<div class=\"nav-side-menu\">") ;  //fa-bars
		out.println("<i class=\"fa  fa-2x toggle-btn\" data-toggle=\"collapse\" data-target=\"#menu-content\"></i>") ;
		out.println("<div class=\"menu-list\">") ;
		out.println("<ul id=\"menu-content\" class=\"menu-content collapse out\">") ;
		if(!Utils.isNullList(menuSet.getMenus()))  {
			for (UIMenu  uiMenu :  menuSet.getMenus()) {
				writeMenu(out,uiMenu);
			}
		}

		out.println("</ul>") ;
		out.println("</div>") ;
		out.println("</div>") ;
	}
	protected void writeParentMenu(PrintWriter out, UIMenu menu) throws IOException {

		String menuText = menu.getMenuText();
		if(menu.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			menuText =  facade.externalize(context, menuText);
		}
		String iconList = menu.getIconStyle();
		out.println("<li  data-toggle=\"collapse\" data-target=\"#" + menu.getGroupId() + "\" class=\"collapsed active\">");
		out.println("<a href=\"#\"><i class=\"" + iconList + "\"></i>" +  "<span class=\""+ menu.getStyle() +"\"> "  + menuText + "</span></a> ");
		if (!Utils.isNullCollection(menu.getChildMenus())) {
			out.println("<ul class=\"sub-menu collapse\" id=\""+ menu.getGroupId() + "\">");
			for (UIMenu childMenu : menu.getChildMenus()) {
				writeChildMenu(out,childMenu);
			}
			out.println("</ul>");
		}
		out.println("</li>");

	}

	protected void writeChildMenu(PrintWriter out, UIMenu menu) throws IOException {
		String menuText = menu.getMenuText();
		if(menu.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			menuText =  facade.externalize(context, menuText);
		}
		String menuClick = "\"window.location.href ='"+ menu.getMenuLink() +"'\"" ;
		String groupId= menu.getGroupId() ;
		if (!Utils.isNullList(menu.getChildMenus())) {
			menuClick = "toggleMenuVisibility('" + groupId +"')  ;" ;
		} else if (Utils.isNullString(menu.getMenuLink())  && !Utils.isNullString(menu.getIframeSrc()) ) {
			String iframeId = menu.getIframeId() ;
			String iframeSrc = menu.getIframeSrc() ;
			menuClick = "refreshIFrameSrc('"+ iframeId + "','"+ iframeSrc+"')" ;
		}
 		out.println("<li " +" onClick=" + menuClick +">") ;
		out.println("<span class=\""+ menu.getStyle() +"\"> " + menuText + "</span>");
		out.println("</li>") ;

	}
	protected void writeMenu(PrintWriter out, UIMenu menu) throws IOException {
		if(!Utils.isNullCollection(menu.getChildMenus()))
			 writeParentMenu(out,menu);
		else
			writeChildMenu(out,menu);
		/*String menuText = menu.getMenuText();
		String iconList = menu.getIconStyle();
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
		} else if (Utils.isNullString(menu.getMenuLink())  && !Utils.isNullString(menu.getIframeSrc()) ) {
			String iframeId = menu.getIframeId() ;
			String iframeSrc = menu.getIframeSrc() ;
			menuClick = "refreshIFrameSrc('"+ iframeId + "','"+ iframeSrc+"')" ;
		}


		out.println("<li id=\"" + menu.getId()+ "\" "+ style +" onClick=" + menuClick +">");
		if(null != iconList){
			//add icon here
			out.println("<i class='"+iconList+"'></i>");		
		}
		out.println(menuText);
		if (Utils.isNullList(menu.getChildMenus())) {
			out.println("</li>");
			return ;
		}

		out.println("<ul id = \""+ groupId +"\"" +  " style=\"display:none\">") ;
		for (UIMenu childMenu :  menu.getChildMenus()) {
			writeMenu(out,childMenu) ;
		}
		out.println("</ul>") ;
		out.println("</li>");*/

	}

	protected void writeLookupPageButton(PrintWriter out, UIButton button,FixedAction action, UILookupPage page,Object value, ViewController controller) throws IOException {
		String caption = button.getCaption();
		if(button.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			caption =  facade.externalize(context, caption);
		}
		String style = (!Utils.isNullString(button.getStyle()) ? "class=\"" + button.getStyle() + "\"" : "");
		if (action == FixedAction.CLOSELOOKUPWINDOW ) {
			button.setOnClickJS("closeLookupWindow('" + page.getParentControl() + "','"+ page.getListControl()  +"');");
		} else if (action == FixedAction.CANCELWINDOW ) {
			button.setOnClickJS("cancelWindow();");
		}else if (action == FixedAction.ACTION_PLAINSUBMIT ) {
			button.setOnClickJS("submit();");
		}else if (action == FixedAction.CLOSELOOKUPDIALOG ) {
			button.setOnClickJS("closeLookupDialog('"+ page.getDialogId() +"','" + page.getParentControl() + "','"+ page.getListControl()  +"');");
		} else if (action == FixedAction.CANCELDIALOG ) {
			button.setOnClickJS("cancelDialog('"+ page.getDialogId() +"');"); 
		}
		out.println(this.getCustomButton(out,button, button.getOnClickJS(),value,controller));
	}

	protected void writeButton(PrintWriter out, UIButton button,Object value,ViewController controller) throws IOException {
		String caption = button.getCaption() ;
		if(button.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			caption =  facade.externalize(context, caption);
		}

		if (button.getFixedAction() != null && !isJSFunction(out, button, button.getFixedAction())) {
			String param = button.getFixedActionParam() ;
			FixedAction action = button.getFixedAction() ;
			if (currentPage instanceof UILookupPage && 
					( action == FixedAction.CLOSELOOKUPWINDOW || action == FixedAction.CANCELWINDOW  || action == FixedAction.ACTION_PLAINSUBMIT ||
					action == FixedAction.CLOSELOOKUPDIALOG || action == FixedAction.CANCELDIALOG	 )){
				writeLookupPageButton(out, button, action,(UILookupPage) currentPage,value,controller);
				return ;
			} else if (action == FixedAction.CANCELDIALOG	) {
				button.setOnClickJS("cancelDialog('"+ param +"');"); 
				out.println(this.getCustomButton(out,button, button.getOnClickJS(),value,controller));
				return ;
			}else  if (action == FixedAction.CANCELWINDOW	) {
				button.setOnClickJS("cancelWindow();");
				out.println(this.getCustomButton(out,button, button.getOnClickJS(),value,controller));
				return ;
			}

			String validateFunc = button.getFixedAction().getValidateFunc() ;
			String act = FixedAction.getFixedActionString(button.getFixedAction()) ;
			String actionFunction = "applyFixedActionServer('"+ act +"','"+ param +"',"+ validateFunc + ")";
			//out.println(this.getCustomButtonFoServer(button, act, param, validateFunc,value,controller));
			this.getCustomButtonFoServer(out,button, act, param, validateFunc,value,controller);

		}else {
		//	out.println(this.getCustomButton(button, button.getOnClickJS(),value,controller));
			this.getCustomButton(out,button, button.getOnClickJS(),value,controller);
		}

	}
	
	private String getCustomButton(PrintWriter out, UIButton button,String event,Object value,ViewController controller) throws IOException {
		String text = button.getCaption();
		if(button.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			text =  facade.externalize(context, text);
		}
		String iconStyle = button.getIconStyle();
		String style = button.getStyle();
		String name = button.getId();
		String id = button.getId();
		StringBuilder buttonHtml=new StringBuilder("");  
		if((text != null && text.length() > 0) && (iconStyle != null && iconStyle.length()>0)){
			String baseStyle = (!Utils.isNullString(style) ? "class='" + style + "'" : "");
			out.println("<button type =\"button\" id='" + id + "' "+ baseStyle + "  name ='" + name  + "' onClick=\""+ event +"\">");
			out.println("<i class='"+iconStyle+"'></i>");
			if(!Utils.isNullList(button.getElements())) {
				for (UIElement element : button.getElements() ) {
					writeElement(element,value,controller);
				}
			}
			out.println(text+"</button>");
		}
		else{
			String _iconStyle = (!Utils.isNullString(iconStyle) ? " "+iconStyle : "");
			String baseStyle = (!Utils.isNullString(style) ? "class='" + style+_iconStyle + "'" : "");
			out.println("<button type =\"button\" id='" + id + "'  "+ baseStyle + " name ='" + name  + "' onClick=\""+ event +"\">");
			if(!Utils.isNullList(button.getElements())) {
				for (UIElement element : button.getElements() ) {
					writeElement(element,value,controller);
				}
			}
			out.println(text+"</button>");
		}
		return "";
	}
	
	//I dont know but setting the variable and calling action event isn't working with previous function
	//May be because of too many escape charatcers
	private String getCustomButtonFoServer(PrintWriter out,UIButton button,String act,String param,String validateFunc,Object value,ViewController controller) throws IOException {
		String text = button.getCaption();
		if(button.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			text =  facade.externalize(context, text);
		}
		String iconStyle = button.getIconStyle();
		String style = button.getStyle();
		String name = button.getId();
		String id = button.getId();
		StringBuilder buttonHtml=new StringBuilder("");  
		if((text != null && text.length() > 0) && (iconStyle != null && iconStyle.length()>0)){
			String baseStyle = (!Utils.isNullString(style) ? "class='" + style + "'" : "");
			out.println("<button type =\"button\" id='" + id + "' "+ baseStyle + "  name ='" + name  + "' onClick=\"applyFixedActionServer('"+ act +"','"+ param +"',"+ validateFunc + ")\">");
			out.println("<i class='"+iconStyle+"'></i>");
			if(!Utils.isNullList(button.getElements())) {
				for (UIElement element : button.getElements() ) {
					writeElement(element,value,controller);
				}
			}
			out.println(text+"</button>");
		}
		else{
			String _iconStyle = (!Utils.isNullString(iconStyle) ? " "+iconStyle : "");
			String baseStyle = (!Utils.isNullString(style) ? "class='" + style+_iconStyle + "'" : "");
			buttonHtml.append("<button type =\"button\" id='" + id + "'  "+ baseStyle + " name ='" + name  + "' onClick=\"applyFixedActionServer('"+ act +"','"+ param +"',"+ validateFunc + ")\">");
			if(!Utils.isNullList(button.getElements())) {
				for (UIElement element : button.getElements() ) {
					writeElement(element,value,controller);
				}
			}
			out.println(text+"</button>");
		}
		return buttonHtml.toString();
	}
	
protected void writeDate(PrintWriter out, UIDate dateC) throws ParseException, IOException {
		
	String dataProp = "data-property=\"" + dateC.getDataProperty() + "\"";
	String style = (!Utils.isNullString(dateC.getStyle()) ? "class=\"form-control " + dateC.getStyle() + "\"" : "class=\"form-control\"");
	out.println("<input type =\"date\" " + style + " id=\"" + dateC.getId() + "\"  name =\"" +dateC.getId()  + "\"  "  + dataProp +  " value=\""+
				Utils.dateToString((java.util.Date)dateC.getValue(),"yyyy-MM-dd") +"\"/>");
	
	}

protected void writeTile(PrintWriter out, UITile tile,Object value,ViewController controller) throws IOException {
	String style = (!Utils.isNullString(tile.getStyle()) ? "class=\" panel " + tile.getStyle() + "\"" : "");
	String headerStyle = "class=\" panel-heading " + (!Utils.isNullString(tile.getHeaderStyle()) ?  tile.getHeaderStyle() + "\"" : "\"  ");
	String detailStyle = "class=\" panel-body " + (!Utils.isNullString(tile.getDetailStyle()) ?  tile.getDetailStyle() + "\"" : "\"  ");
	
	out.println("<div id= \""+ tile.getId() +   "\" " + style + " " +  " >");
	out.println("<div id= \"rdsHdr_"+ tile.getId() +   "\" " + headerStyle + " " +  " >");
	if(!Utils.isNullList(tile.getHeaderElements())) {
		for (UIElement element : tile.getHeaderElements() ) {
			writeElement(element,value,controller);
		}
	}
	out.println("</div>");
	out.println("<div id= \"rdsDtl_"+ tile.getId() +   "\" " + detailStyle + " " +  " >");
	if(!Utils.isNullList(tile.getDetailElements())) {
		for (UIElement element : tile.getDetailElements() ) {
			writeElement(element,value,controller);
		}
	}
	out.println("</div>");
	out.println("</div>");
}
	
	protected void writeTextArea(PrintWriter out, UITextArea textArea) throws IOException {
		String dataProp = "data-property=\"" + textArea.getDataProperty() + "\"";  
		String str = Utils.isNullString(String.valueOf(textArea.getValue()))?"":String.valueOf(textArea.getValue());
		String style = (!Utils.isNullString(textArea.getStyle()) ? "class=\"form-control " + textArea.getStyle() + "\"" : "class=\"form-control\"");
		out.println("<textarea  " + style + " id=\"" + textArea.getId() + "\" name =\"" +textArea.getId()  + "\" "  + dataProp + " rows=\""+ textArea.getRows() +"\" cols=\"" + textArea.getCols()
		+ "\">" + str + "</textarea>");
		
	
	}
	
	protected void writeFileUpload(PrintWriter out, UIFileUpload fileUpload) throws Exception {
		String dataProp = "data-property=\"" + fileUpload.getDataProperty() + "\"";
		String accept = !Utils.isNull(fileUpload.getAccept())?"accept=\""+fileUpload.getAccept() + "\"":"";
		String onchange = !Utils.isNull(fileUpload.getOnChangeJS())?"onchange=\"" + fileUpload.getOnChangeJS() +"\"":"";  
		out.println("<input class=\"form-control\" type =\"file\" id=\"" + fileUpload.getId() + "\"  " +  dataProp +  " " + accept + " " + onchange  + " name =\"" + fileUpload.getId()  + "\"/>");

	
	}
	
	protected void writeBooleanCheckBox(PrintWriter out, UIBooleanCheckBox checkBox) throws IOException {
		IExternalizeFacade facade = null;
		if(checkBox.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dataProp = "data-property=\"" + checkBox.getDataProperty() + "\""; 
		
		String st = checkBox.getDisplayText();
		String style = (!Utils.isNullString(checkBox.getStyle()) ? "class=\"form-control " + checkBox.getStyle() + "\"" : "class=\"form-control\"");
		Boolean selectedVal = Utils.getBooleanValue(String.valueOf(checkBox.getValue()));
		String selected = selectedVal?"checked":"";
		String hiddenVal =selectedVal?"true":"false";
		out.println("<input type =\"hidden\" id=\"" +  checkBox.getHiddenControlId() + "\"  name =\"" + checkBox.getHiddenControlId()  + "\" value =\""+
		 hiddenVal + "\"> ");
		out.println("<input type =\"checkbox\" id=\"" +  checkBox.getId() + "\"  name =\"" +checkBox.getId()  + "\" " + dataProp + " " + style + 
				" value =\""+ st + "\"  onclick=\"workBooleanCheckBoxControl(this,'"+ checkBox.getHiddenControlId() +"')\" " + selected + " /><span>"	  +"</span>"  ) ;
		
		
	}
	
	protected void writeText(PrintWriter out, UIText text) throws IOException {
		String onlyNum = text.isOnlyNumbers()?"onkeypress=\"return isNumberKey(event)\" ":"";
		String sizeStr = text.getSize()>0?"size=\"" + text.getSize()+"\"":"" ;  
		String required = text.isRequired()?"required":"";
		String autoFocus = text.isAutofocus()?"autoFocus":"";
		
		String readOnly = text.isReadOnly()?"readonly":"" ;
		String style = (!Utils.isNullString(text.getStyle()) ? "class=\"form-control " + text.getStyle() + "\"" : "class=\"form-control\"");
		String focusin = !Utils.isNull(text.getOnfocusin())?"onfocusin=\""+text.getOnfocusin()+";\"":"";
		String focusout = !Utils.isNull(text.getOnfocusout())?"onfocusout=\""+text.getOnfocusout()+";\"":"";
		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		String placeHolder = Utils.isNull(text.getPlaceHolder())?"":"placeholder=\"" + facade.externalize(context,text.getPlaceHolder())  + "\""; 
		String dataProp = "data-property=\"" + text.getDataProperty() + "\""; 
		out.println("<input type =\"text\" id=\"" + text.getId() + "\"  "  +" name =\"" + text.getId()  + "\" " + onlyNum + " value=\""+ 
				Utils.getFormattedValue(text.getValue()) +"\" "+ sizeStr + " "  + readOnly  + " " + placeHolder + " " + style + " " + dataProp + " " + required + " " + autoFocus +
				" " +  focusin + " " + focusout + " "+ " />");
	
	}
	
	protected void writePassword(PrintWriter out, UIPassword
			text) throws IOException {
		String sizeStr = text.getSize()>0?"size=\"" + text.getSize()+"\"":"" ;  
		String style = (!Utils.isNullString(text.getStyle()) ? "class=\"form-control " + text.getStyle() + "\"" : "class=\"form-control\"");
		String required = text.isRequired()?"required":"";
		String autoFocus = text.isAutofocus()?"autoFocus":"";
		
		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		String placeHolder = Utils.isNull(text.getPlaceHolder())?"":"placeholder=\"" + facade.externalize(context,text.getPlaceHolder())  + "\"";
		String dataProp = "data-property=\"" + text.getDataProperty() + "\""; 
		out.println("<input type =\"Password\" id=\"" + text.getId() + "\"  "  +" name =\"" + text.getId()  + "\" value=\""+ 
				Utils.getFormattedValue(text.getValue()) +"\""+ sizeStr + " " + placeHolder  + " " + style  + " " + required + " " + autoFocus + " " +  dataProp + " />");
	
	}
	
	protected void writeEmailControl(PrintWriter out, UIEmail emailControl) throws IOException {
		String sizeStr = emailControl.getSize()>0?"size=\"" + emailControl.getSize()+"\"":"" ;  
		String style = (!Utils.isNullString(emailControl.getStyle()) ? "class=\"form-control " + emailControl.getStyle() + "\"" : "class=\"form-control\"");
		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		String placeHolder = Utils.isNull(emailControl.getPlaceHolder())?"":"placeholder=\"" + facade.externalize(context,emailControl.getPlaceHolder())  + "\"";
		String dataProp = "data-property=\"" + emailControl.getDataProperty() + "\""; 
		String required = emailControl.isRequired()?"required":"";
		String autoFocus = emailControl.isAutofocus()?"autoFocus":"";
		
		out.println("<input type =\"Password\" id=\"" + emailControl.getId() + "\"  "  +" name =\"" + emailControl.getId()  + "\" value=\""+ 
				Utils.getFormattedValue(emailControl.getValue()) +"\""+ sizeStr + " " + placeHolder  + " " + style  + " " + required + " " + autoFocus + " " +  dataProp + " />");
	
	}
	
	/*protected void writeBooleanCheckBox(PrintWriter out, UIBooleanCheckBox checkBox) throws IOException {
		IExternalizeFacade facade = null;
		if(checkBox.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dataProp = "data-property=\"" + checkBox.getDataProperty() + "\""; 
		
		String st = checkBox.getDisplayText();
		Boolean selectedVal = Utils.getBooleanValue(String.valueOf(checkBox.getValue()));
		String selected = selectedVal?"checked":"";
		String hiddenVal =selectedVal?"true":"false";
		out.println("<input type =\"hidden\" id=\"" +  checkBox.getHiddenControlId() + "\"  name =\"" + checkBox.getHiddenControlId()  + "\" value =\""+
		 hiddenVal + "\"> ");
		out.println("<input class=\"form-control\" type =\"checkbox\" id=\"" +  checkBox.getId() + "\"  name =\"" +checkBox.getId()  + "\" " + dataProp + 
				" value =\""+ st + "\"  onclick=\"workBooleanCheckBoxControl(this,'"+ checkBox.getHiddenControlId() +"')\" " + selected + " /><span>"	  +"</span>"  ) ;
		
		
	}*/
	
	
	
	/*protected void writeCheckBox(PrintWriter out, UICheckBox checkBox) throws IOException {
		IExternalizeFacade facade = null;
		if(checkBox.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dataProp = "data-property=\"" + checkBox.getDataProperty() + "\""; 
		for ( String st: checkBox.getOptions().keySet() ){
			if(!Utils.isNullString(st)) {
				String displayVal = checkBox.getOptions().get(st);
				if ( facade != null ) {
					displayVal = facade.externalize(context, displayVal.trim());
				}
				String selected = st.equals(String.valueOf(checkBox.getValue()))?"checked=\"checked\"":"";
				out.println("<input class=\"form-control\" type =\"checkbox\" id=\"" +  checkBox.getId() + "\"  name =\"" +checkBox.getId()  + "\" " + dataProp + 
					" value =\""+ st + "\"" + selected + "/><span>"	+ displayVal  +"</span>"  ) ;
			}
		}
	}
	
	protected void writeRadioBox(PrintWriter out, UIRadioBox radioBox) throws IOException {
		IExternalizeFacade facade = null;
		if(radioBox.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dataProp = "data-property=\"" + radioBox.getDataProperty() + "\""; 
		for ( String st: radioBox.getOptions().keySet() ){
			String displayVal = radioBox.getOptions().get(st);
			if ( facade != null ) {
				displayVal = facade.externalize(context, displayVal);
			}
			String selected = st.equals(radioBox.getValue())?"checked=\"checked\"":"";
			out.println("<input class=\"form-control\" type =\"radio\" id=\"" +  radioBox.getId() + "\" name = \""+ radioBox.getId() 
					+"\"  "  +  dataProp +  " value =\""+ st + "\" " + selected + " />"  + displayVal  + "&nbsp;") ;
		}
	}*/
	
	protected void writeLookupDataList(PrintWriter out, UILookupDataList textLookup) throws IOException {
		String lookupType = textLookup.getLookupType();
		
		String additionalLookupCtrls = declareArrayforAdditionalControls(textLookup.getSupplimentaryFields(), textLookup.getId());
		out.println(additionalLookupCtrls);
		String additionalLookupFields= declareArrayforAdditionalFields(textLookup.getSupplimentaryFields(), textLookup.getId());
		out.println(additionalLookupFields);
		String variableCtrlName = (textLookup.getSupplimentaryFields()!=null&&textLookup.getSupplimentaryFields().size()>0)?RadsControlConstants.ADDITIONALOOKUPCTRLS +  textLookup.getId():"";
		String variableFieldsName =(textLookup.getSupplimentaryFields()!=null&&textLookup.getSupplimentaryFields().size()>0)?RadsControlConstants.ADDITIONALOOKUPFIELDS +  textLookup.getId():"null";
		String popSupplimentEvent =(!Utils.isNullString(variableCtrlName))?" onchange=\"populatesupplimentary('" + lookupType +"' ,this,'"+textLookup.getListId()+"'," + variableCtrlName + "  )\"":" " ;
		String style = (!Utils.isNullString(textLookup.getStyle()) ? "class=\"form-control " + textLookup.getStyle() + "\"" : "class=\"form-control\"");
		out.println("<input type =\"text\" autocomplete =\"off\"  id=\"" + textLookup.getId() + "\"  "  +" name =\"" + textLookup.getId()  + "\"  value=\""+ 
				Utils.getFormattedValue(textLookup.getValue()) +"\" list = \"" + textLookup.getListId() + "\"  "+  style  + 
				" onkeyup=\"getLookupWithAjax('" + lookupType +"' ,this,'"+textLookup.getListId()+"'," + variableFieldsName + " ,'"+ textLookup.getAdditionalInputControl() +"' )\""+
				popSupplimentEvent + 
				" />");
		out.println("<datalist id=\"" + textLookup.getListId() + "\" name =\"" + textLookup.getListId()  +  "\">");
		
		out.println("</datalist>");
	}
	
	protected void writeDataList(PrintWriter out, UIDataList list) throws IOException {
		String style = (!Utils.isNullString(list.getStyle()) ? "class=\"form-control " + list.getStyle() + "\"" : "class=\"form-control\"");
		out.println("<input type =\"text\" id=\"" + list.getId() + "\"  "  +" name =\"" + list.getId()  + "\"  value=\""+ 
				Utils.getFormattedValue(list.getValue()) +"\" list = \"" + list.getListId() + "\"  "+  style  + " />");
		out.println("<datalist id=\"" + list.getListId() + "\">");
		if (!Utils.isNullList(list.getOptions()))
			for (String str : list.getOptions()) {
				out.println("<option value =\""+ str +   "\">");
			}
		out.println("</datalist>");
	}
	
	protected void writeList(PrintWriter out, UIList list) throws IOException {
		 if(list.isMultiSelect()) {
			 super.writeList(out,list);
			 return;
		 }
		IExternalizeFacade facade = null;
		if(list.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dblClick = Utils.isNullString(list.getDblClickJS())?"":"ondblclick="+list.getDblClickJS() ;
		String onChangeJS = Utils.isNullString(list.getOnChangeJS())?"":"onchange=\""+list.getOnChangeJS() + "\"" ;
		String dataProp = "data-property=\"" + list.getDataProperty() + "\"";  
 		String sizeStr = Utils.isNullString(list.getSize()) ?"":" size= " +  list.getSize() ;
		String style = (!Utils.isNullString(list.getStyle()) ? "class=\"form-control " + list.getStyle() + "\"" : "class=\"form-control\"");
 		String multiSelect = list.isMultiSelect()?" multiple ":"";
		out.println("<select id=\"" + list.getId()+ "\" name =\"" +list.getId()  + "\" " + multiSelect + " " + sizeStr +" "  + style + " " + dataProp + " " + " " + 
 		onChangeJS + " " + dblClick + " >");
		if (!Utils.isNullMap(list.getOptions())) {
			for ( String st: list.getOptions().keySet() ){
				String selected = st.equals(String.valueOf(list.getValue()))?"selected":"";
				if(list.isMultiSelect()  &&  list.getValue() instanceof String[]) {
					String [] vals = (String  [])list.getValue();
					for (int i =0 ; i < vals.length ; i ++ ) {
						if(vals[i].equals(st)) {
							selected ="selected";
							break;
						}
					}
				}
				String opt = list.getOptions().get(st);
				if ( facade != null ) {
					opt = facade.externalize(context, opt);
				}
				out.println("<option value =\""+ st +   "\"" + selected + ">" + opt +    "</option> ") ;
			}
		}
		out.println("</select>");
		
	}
	
	
	
	protected void writeLookupText(PrintWriter out, UILookupText textLookup) throws IOException {
		String sizeStr = textLookup.getSize()>0?"size=\"" + textLookup.getSize()+"\"":"" ; 
		out.println("<div class=\"input-append\">");
		String dataProp = "data-property=\"" + textLookup.getDataProperty() +  "\"";
		out.println("<input class=\"input-medium search-query\" type =\"text\" id=\"" + textLookup.getId() + "\" name =\"" + textLookup.getId()  + "\" "  + dataProp  +  " value=\""+ 
				Utils.getFormattedValue(textLookup.getValue()) +"\" " + sizeStr +" />");
	//String img = "<img src=\"" + textLookup.getImgSrc() + "\" width =\"30\" height =\"30\" >";
		
		String height = textLookup.getWindowHeight() ;
		String width =textLookup.getWindowWidth() ;
		String title= textLookup.getLookupWindowTitle();
		String additionalControl = textLookup.getAdditionalInputControl() ;
		String urlText = textLookup.getUrl(portalPrefix) + "&lookupType=" + textLookup.getLookupType() + "&parentControl=" +  textLookup.getId();
		//out.println("<button type =\"submit\"  src =\"" + textLookup.getImgSrc() + "\" onClick=\"showLookupWindow('"+textLookup.getId()+"');\" > </button>");
		if (textLookup.isShowLookupAsDialog()) {
			writeLookupDialog(out, textLookup);
			if (Utils.isNullMap(textLookup.getSupplimentaryFields()))
				out.println("<button class=\"add-on\"  type =\"button\"   name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupDialog('" + textLookup.getDialogId()  + "',this,'"+additionalControl+"');\" >" + "..." +" </button>");
			else {
				String additionalLookupCtrls = declareArrayforAdditionalControls(textLookup.getSupplimentaryFields(), textLookup.getId());
				out.println(additionalLookupCtrls);
				String additionalLookupFields= declareArrayforAdditionalFields(textLookup.getSupplimentaryFields(), textLookup.getId());
				out.println(additionalLookupFields);
				String variableCtrlName = RadsControlConstants.ADDITIONALOOKUPCTRLS +  textLookup.getId();
				String variableFieldsName = RadsControlConstants.ADDITIONALOOKUPFIELDS +  textLookup.getId();
				out.println("<button class=\"add-on\"  type =\"button\"   name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupDialogWithAdditionalFields('" + textLookup.getDialogId()
						+ "',this,'"+additionalControl+"',"+variableCtrlName+"," + variableFieldsName + ");\" >" + "..." +" </button>");
				
			}
				
		}else {
			out.println("<button  type =\"button\" class=\"add-on\"   name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupWindow('"+ urlText +"','"+title+"','"+textLookup.getId()+"','"+height+"','"+ width+"','"+additionalControl+"');\" >" + "..." +" </button>");
		}
		
		
	
	}
	

}

package com.techtrade.rads.framework.ui.writers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.io.PrintWriter;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.controller.abstracts.IExternalizeFacade;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.filter.FilterNode;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.graphdata.BarChartData;
import com.techtrade.rads.framework.model.graphdata.GaugeChartData;
import com.techtrade.rads.framework.model.graphdata.LineChartData;
import com.techtrade.rads.framework.model.graphdata.PieChartData;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.AjaxGroup;
import com.techtrade.rads.framework.ui.components.SortCriteria;
import com.techtrade.rads.framework.ui.components.UICRUDPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIFixedPanel;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UIGeneralPage;
import com.techtrade.rads.framework.ui.components.UILeftNav;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UILookupPage;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.components.UITransactionPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsConstants;
import com.techtrade.rads.framework.ui.constants.RadsControlConstants;
import com.techtrade.rads.framework.ui.controls.UIBooleanCheckBox;
import com.techtrade.rads.framework.ui.controls.UIBreak;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDataList;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UIDialog;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIElementCollection;
import com.techtrade.rads.framework.ui.controls.UIEmail;
import com.techtrade.rads.framework.ui.controls.UIErrorList;
import com.techtrade.rads.framework.ui.controls.UIErrorObject;
import com.techtrade.rads.framework.ui.controls.UIFileUpload;
import com.techtrade.rads.framework.ui.controls.UIFilterSet;
import com.techtrade.rads.framework.ui.controls.UIHeader;
import com.techtrade.rads.framework.ui.controls.UIHidden;
import com.techtrade.rads.framework.ui.controls.UIHyperLink;
import com.techtrade.rads.framework.ui.controls.UIIFrame;
import com.techtrade.rads.framework.ui.controls.UIImage;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UIList;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UILookupText;
import com.techtrade.rads.framework.ui.controls.UIMenu;
import com.techtrade.rads.framework.ui.controls.UINote;
import com.techtrade.rads.framework.ui.controls.UIParagraph;
import com.techtrade.rads.framework.ui.controls.UIPassword;
import com.techtrade.rads.framework.ui.controls.UIRadioBox;
import com.techtrade.rads.framework.ui.controls.UIStaticText;
import com.techtrade.rads.framework.ui.controls.UITab;
import com.techtrade.rads.framework.ui.controls.UITabSet;
import com.techtrade.rads.framework.ui.controls.UIText;
import com.techtrade.rads.framework.ui.controls.UITextArea;
import com.techtrade.rads.framework.ui.controls.graphs.UIBarChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIGaugeChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphBar;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphCircle;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphLegend;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphLine;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphPath;
import com.techtrade.rads.framework.ui.controls.graphs.UIGraphText;
import com.techtrade.rads.framework.ui.controls.graphs.UILineChart;
import com.techtrade.rads.framework.ui.controls.graphs.UILineSet;
import com.techtrade.rads.framework.ui.controls.graphs.UIPieChart;
import com.techtrade.rads.framework.ui.templates.TemplateType;
import com.techtrade.rads.framework.utils.Utils;

public class HTMLWriter extends Writer{

	UIPage currentPage;
	String appURL;
	IRadsContext context ;
	String portalPrefix;
	protected boolean useGoogleforGraphs;
	
	// " span.style.backgroundColor  = \"#53868B\"; \n"+  
	 //" span.style.fontColor  = \"black\"; \n"+
	
	
	
	String jsToggle = " <script language =\"javascript\"> " +
	 " function toggleTabVisbility(objId, spanId,selectedStyle,unselectedStyle) {\n " +
	 " var elem = document.getElementById(objId);\n " +
	 " var span = document.getElementById(spanId);\n " +
	 " var parentElem = elem.parentElement;\n " +
	 " var childNodes = parentElem.childNodes ;\n" +
	 " for  ( var i = 0 ; i < childNodes.length ; i ++) { \n  " +
	 "   if ('DIV' == childNodes[i].tagName) {" +
	 "     childNodes[i].style.display= 'none'  ; \n"  +
	 "   } \n" +
	 " } \n" +
	 " elem.style.display= '' ; \n"+
	 " var parentSPElem = span.parentElement;\n " +
	 " var childSPNodes = parentSPElem.childNodes ;\n" +
	 " for  ( var i = 0 ; i < childSPNodes.length ; i ++) {  \n  " +
	 "   if ('SPAN' == childSPNodes[i].tagName) { " +
	 "    // childSPNodes[i].style.border = \"2px solid\"; \n"  +
	 "  // childSPNodes[i].style.backgroundColor = \"#6C7B8B\";  \n" +
	 "   childSPNodes[i].className =unselectedStyle; \n"  +
	 "   //childSPNodes[i].setAttribute('class',unselectedStyle) \n"  +
	 "   } \n" +
	 " } \n" +
	 " span.className= selectedStyle ; \n"+
	  " // span.setAttribute('class',selectedStyle) ; \n"+
	 " } \n" + 
	 " </script>" ;
	
//	String jsGetIndex = "function getCurrentObjectIndex(currentCtrl)  {" +
//				"var objName = currentCtrl.name;" +
//				"var elemCount = document.getElementsByName(objName).length;" +
//				"console.log('getCurrentObjectIndex:elemCount= ' + elemCount  + ':objName='  + objName);"+
//				"for (var i = 0 ; i < elemCount ; i ++ ) {" +
//				"if (document.getElementsByName(objName)[i]  == 	currentCtrl)  {" +
//				"return i ;" +
//		"}" +
//	"}" +
//	"return  0 ;"+
//	"}";
	
	String jsLookupDialog = " <script language =\"javascript\"> \n " +
			" function showLookupDialog(id,curControl) {\n " +
			" var index  = getCurrentObjectIndex(curControl); " + 
			" console.log('index'= + index); " + 
 			" document.getElementById(id).showModal(); \n" +
			" } \n" + 
			 " </script>" ;
	
	
	String jsLookupWindow = " <script language =\"javascript\"> \n " +
			" function showLookupWindow(url,title,returntxt,height,width,additionalControl) {\n " +
			" window.open(url,title,'resizable=0,toolbar=0,status=0,titlebar=0,height=' + height + ',width=' + width  );\n" +
			" } \n" + 
			 " </script>" ;
	String jsCloseLookupWindow = " <script language =\"javascript\"> \n " +
			" function closeLookupWindow(parentControl, valueControl) {\n " +
			" if (document.getElementById(valueControl).value != '' ){ \n" +
			 " window.opener.document.getElementById(parentControl).value = document.getElementById(valueControl).value \n" +
			" window.opener.document.getElementById(parentControl).focus(); \n" +
			"  window.close();  \n" +
			 " }\n" +
			"  } \n" + 
			 " </script>" ;
	String jsCloseLookupDialog = " <script> \n var clickedCellIndex =-1;  </script>\n" + 
			" <script language =\"javascript\"> \n " +
			"function closeLookupDialog(dialogId,parentControl, valueControl) { \n" +
			 " var clkCell = window.parent.clickedCellIndex;\n" +
			 " var additional = window.parent.clickedCellIndex;\n" + 
			" console.log('clkCell =' + clkCell);\n"  + 
		 " if (document.getElementById(valueControl).value != '' ){ \n" +
		  "if (clkCell <= 0) { \n" +
			 " window.parent.document.getElementById(parentControl).value = document.getElementById(valueControl).value; \n" + 
			 " window.parent.document.getElementById(dialogId).close(); \n" +
			"  window.parent.document.getElementById(parentControl).focus(); \n" +
		 " }else { \n" +
			 " window.parent.document.getElementsByName(parentControl)[clkCell].value = document.getElementById(valueControl).value; \n" + 
			 " window.parent.document.getElementById(dialogId).close(); \n" +
			"  window.parent.document.getElementsByName(parentControl)[clkCell].focus(); \n" +
		 " } \n" +
		"} \n" +
	"} \n" +
	" </script>" ;
	
	String jsCloseLookupDialogWithAdditions = " <script> \n var clickedCellIndex =-1;  </script>\n" + 
			" <script language =\"javascript\"> \n " +
			"function closeLookupDialogWithAdditions(dialogId,parentControl, valueControl, additionalValues) { \n" +
			 " var clkCell = window.parent.clickedCellIndex;\n" + 
			" console.log('clkCell =' + clkCell);\n"  + 
			" console.log('additionalValues =' + additionalValues);\n"  + 
		 " if (document.getElementById(valueControl).value != '' ){ \n" +
		  "   var  selectedValue = document.getElementById(valueControl).value;\n" +
		  "   var  splittedValue = selectedValue.split('|'); \n" + 
		  "if (clkCell <= 0) { \n" +
			 " window.parent.document.getElementById(parentControl).value = splittedValue[0]; \n" + 
			 " window.parent.document.getElementById(dialogId).close(); \n" +
			"  window.parent.document.getElementById(parentControl).focus(); \n" +
		 " }else { \n" +
			 " window.parent.document.getElementsByName(parentControl)[clkCell].value = splittedValue[0] ; \n" + 
			 " window.parent.document.getElementById(dialogId).close(); \n" +
			"  window.parent.document.getElementsByName(parentControl)[clkCell].focus(); \n" +
		 " } \n" +
		"} \n" +
	"} \n" +
	" </script>" ;
	
	
	String jsCancelWindow = " <script language =\"javascript\"> \n " +
			" function cancelWindow() {\n " +
			 " window.close(); \n" + 
			" } \n" + 
			 " </script>" ;	

	String jsCancelDialog = " <script language =\"javascript\"> \n " +
			" function cancelDialog(dialogId) {\n " +
			 " window.parent.document.getElementById(dialogId).close(); \n" + 
			" } \n" + 
			 " </script>" ;	
	
	String jsapplyFixedActionServer = " <script language =\"javascript\"> \n " +
			" function applyFixedActionServer(action,param,validateFunction) {\n " +
			 " if (validateFunction != null && validateFunction != 'undefined' &&  validateFunction != 'null' ) { \n" +
			 " var retVal = validateFunction; \n" +
             "  if( retVal == false ) {\n" +
			 " return false; \n" +
			 " }\n}\n" +
			 " var control= document.getElementById('-FixedControl-'); \n" +
			 " control.value = action ; \n" + 
			 " var paramcontrol= document.getElementById('-FixedParamControl-'); \n" +
			 " paramcontrol.value = param ; \n" + 
			 " document.-FormName-.submit();" + 
			" } \n" + 
			 " </script>" ;	
	
	String jstoggleMenuVisibility = " <script language =\"javascript\"> \n " +
			" function toggleMenuVisibility(objId) {\n " +
			 " var menuList  =document.getElementById(objId );  \n" +
			 " var parentContainer  =menuList.parentElement;  \n" +
			 " if (menuList.style.display== 'none') { \n " +
			 "   menuList.style.display = '' ;\n" +
			 "   parentContainer.classList.add('open');\n" +			 
			 " }else { \n " + 
			 "  menuList.style.display= 'none'  ; \n"  +
			 "   parentContainer.classList.remove('open');\n" +	
			" }   window.event.stopPropagation() ; \n } \n" + 
			 " </script>" ;	
	
	
	public HTMLWriter(Object board) {
		super(board);
		
	}
	
	
	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
	
	

	public String getPortalPrefix() {
		return portalPrefix;
	}


	public void setPortalPrefix(String portalPrefix) {
		this.portalPrefix = portalPrefix;
	}


	public boolean isUseGoogleforGraphs() {
		return useGoogleforGraphs;
	}


	public void setUseGoogleforGraphs(boolean useGoogleforGraphs) {
		this.useGoogleforGraphs = useGoogleforGraphs;
	}


	private Object getfromSubObject(Object object , String property) throws Exception{
		if (property.contains("."))  {
			String subObjectProperty = property.substring(0, property.indexOf("."));
			String remainingProperty = property.substring(property.indexOf(".")+1,property.length());
			Method methodRead =  object.getClass().getMethod("get" + Utils.initupper(subObjectProperty));
			Object subObject = methodRead.invoke(object, new Object [] {});
			if (subObject == null)  {
				return null;
			}
			return getfromSubObject(subObject,remainingProperty);
		}else {
			try {
			Method methodRead =  object.getClass().getMethod("get" + Utils.initupper(property));
			Object ret = methodRead.invoke(object, new Object [] {});
			   return ret;
			}catch(java.lang.NoSuchMethodException nsmEX) {
			}
			Method methodRead =  object.getClass().getMethod("is" + Utils.initupper(property));
			Object ret = methodRead.invoke(object, new Object [] {});
			   return ret;
		}
	}
	
	protected Method getterMethod(Class className , String property) throws java.lang.NoSuchMethodException  {
		boolean noSuchMethod = false; 
		try {
			Method methodRead = className.getMethod("get" + Utils.initupper(property));
			noSuchMethod = true ;
			return methodRead;
		}catch (java.lang.NoSuchMethodException nsmEX) {
			noSuchMethod = false ;
		}
		Method methodRead = className.getMethod("is" + Utils.initupper(property));
		return methodRead ;
	}
	private Object callGetter (ModelObject object , String property) {
		try {             
			   if (property.contains("."))  {
				   Object ret = getfromSubObject(object,property) ;
				   return ret ;
			   }
			   Method methodRead =   getterMethod(object.getClass(),property);
			   Object ret = methodRead.invoke(object, new Object [] {});
			   return ret; 
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
		return null;
	}
	
	public IRadsContext getContext() {
		return context;
	}
	public void setContext(IRadsContext context) {
		this.context = context;
	}

	private void writeElementWithoutControl (UIElement element, Object object,ViewController controller ) throws Exception {
		PrintWriter out = (PrintWriter) board;
		 if (element instanceof UICondition) {
			writeConditonalElements(out,(UICondition)element, object, controller);
		}
	}
	
	
	protected void writeElement(UIElement element, Object object,ViewController controller ) throws IOException {
		PrintWriter out = (PrintWriter) board;
		
		try {
			UIControl control  = null;
			if (  element == null ||
					( (object instanceof ModelObject )&& !element.shouldDisplay((ModelObject)object, controller))) {
				return ;
			}
			if (element.getControl() != null ) {
				control = element.getControl();
			}else {
				writeElementWithoutControl(element, object, controller);
				return ;
			}
			String propertyName = element.getModelProperty() ;
			Object value =  null;
			Object constValue =  element.getConstantValue() ;
			String generatedValue = element.getGeneratedValue();
			Object displayValue = null;
					
			if (constValue!=null && !Utils.isNullString(constValue.toString() ))  {
				displayValue = constValue;
			}else if (!Utils.isNullString(generatedValue)) {
				value =  element.GenerateValue(controller, object, generatedValue);
				if (control instanceof UILabel && Utils.isNullString(((UILabel)control).getLabel()))
					((UILabel)control).setLabel(String.valueOf(value));
			}else if (!Utils.isNullString(propertyName) && object instanceof  ModelObject && element.getControl().getValue() == null) {
				//control.setDataProperty(propertyName);
				if ( propertyName.startsWith("Controller."))  {
					
				}else if ( !propertyName.contains("[]"))
					displayValue = callGetter((ModelObject)object, propertyName);
				else {
					String collectionProperty = propertyName.substring(0, propertyName.indexOf("["));
					value = callGetter((ModelObject)object, collectionProperty);
				}
				if (control instanceof UILabel)
					  ((UILabel) control).setLabel(String.valueOf(displayValue));
			} else if (element.getControl().getValue() != null && !(element.getControl().getValue() instanceof ModelObject) )  {
				displayValue = element.getControl().getValue() ;
			} else
				value = object;
			
			if (control instanceof UIHyperLink && displayValue instanceof String && displayValue != null) {
				((UIHyperLink)control).setHyperLink(String.valueOf(displayValue));
				((UIHyperLink)control).setInnerText(String.valueOf(displayValue));
			}else if (control instanceof UIImage && displayValue instanceof String) {
				((UIImage) control).setSrc(String.valueOf(displayValue));
			} else if (! (control instanceof UILabel))
				control.setValue(displayValue);
		
				
			if (element.getLabel() != null && !Utils.isNullString(element.getLabel().getLabel()) &&  !(control instanceof UITab)) {
				if (element.isMandatory() && !Utils.isNullString(currentPage.getTemplate().getMandatoryMarker())) 
					writeMandatoryFieldLabel(out, element.getLabel(), currentPage.getTemplate().getMandatoryMarker(),false);
				else
					writeLabel(out, element.getLabel(),false);
			}
			/*if (value instanceof ModelObject &&  displayValue == null ) {
				System.out.println("Debugg");
			}*/
			if ( control instanceof UIForm) {
				writeForm(out, (UIForm) control,value,controller);
			} else if ( control instanceof UITable) {
				writeTable(out, (UITable) control,value,controller);
			}else if ( control instanceof UITableHead) {
				writeTableHead(out,  (UITableHead) control,value,controller);
			}else if ( control instanceof UITableFooter) {
				writeTableFooter(out,  (UITableFooter) control,value,controller);
			}else if ( control instanceof UITableRow) {
				writeTableRow(out,  (UITableRow) control,value,controller);
			}else if ( control instanceof UITableCol) {
				writeTableCol(out, (UITableCol) control,value,controller);
			}else if ( control instanceof UIPassword) {
				writePassword(out, (UIPassword) control);
			}else if ( control instanceof UIEmail) {
				writeEmailControl(out, (UIEmail) control);
			}else if ( control instanceof UIText) {
				writeText(out, (UIText) control);
			}else if ( control instanceof UIFileUpload) {
				writeFileUpload(out, (UIFileUpload) control);
			}else if ( control instanceof UILookupText) {
				writeLookupText(out, (UILookupText) control);
			}else if ( control instanceof UIHyperLink) {
				writeHyperLink(out, (UIHyperLink) control);
			}else if ( control instanceof UIHidden) {
				writeHidden(out, (UIHidden) control);
			}else if ( control instanceof UIGaugeChart) {
				UIGaugeChart prop = (UIGaugeChart)control  ;
				String chartProp = prop.getDataProvider();
				GaugeChartData pieChart=(GaugeChartData) callGetter((ModelObject)object, chartProp);
				UIGaugeChart chart  =UIGaugeChart.makeUIGaugeChart(pieChart, prop.getWidth(), prop.getHeight());
				chart.setId(prop.getId());
				writeGaugeChart(out, chart, displayValue, controller);
			}else if ( control instanceof UIPieChart) {
				UIPieChart prop = (UIPieChart)control  ;
				String chartProp = prop.getDataProvider();
				PieChartData pieChart=(PieChartData) callGetter((ModelObject)object, chartProp);
				if (pieChart == null) return ;
				UIPieChart chart = UIPieChart.makeUIPieChart(pieChart,prop.getHeight(),prop.getWidth(), prop.getCenterX(), prop.getCenterY(), prop.getRadius());
				chart.setId(prop.getId());
				chart.setUseGoogleChart(prop.isUseGoogleChart());
				chart.setDonutChart(prop.isDonutChart());
				writePieChart(out, chart, displayValue, controller);
			}else if ( control instanceof UIBarChart) {
				//writeHidden(out, (UIHidden) control);
				UIBarChart prop = (UIBarChart)control  ;
				String chartProp = prop.getDataProvider();
				BarChartData barChartData =(BarChartData) callGetter((ModelObject)object, chartProp);
				if (barChartData == null)
					return ;
				UIBarChart chart = UIBarChart.makeUIBarChart(barChartData, prop.getWidth(),prop.getHeight(), prop.getStartX(), 
						prop.getStartY(), prop.getMarginWidth(),prop.getBarWidth(),prop.getNoYAxisDivisions(),prop.isUseCoreChart());
				chart.setId(prop.getId());
				chart.setUseGoogleChart(prop.isUseGoogleChart());
				writeBarChart(out, chart, null, controller);
			}else if ( control instanceof UILineChart) {
				//writeHidden(out, (UIHidden) control);
				UILineChart prop = (UILineChart)control  ;
				String chartProp = prop.getDataProvider();
				LineChartData lineChartData =(LineChartData) callGetter((ModelObject)object, chartProp);
				if (lineChartData == null)
					return ;
				UILineChart chart = UILineChart.makeUILineChart(lineChartData, prop.getWidth(),prop.getHeight(), prop.getStartX(), 
						prop.getStartY(),prop.getMarginWidth(),prop.getMarginHeight(),prop.getCanvasWidth(),prop.getCanvasHeight());
				chart.setId(prop.getId());
				chart.setUseGoogleChart(prop.isUseGoogleChart());
				writeLineChart(out, chart, null, controller);
			}else if ( control instanceof UITextArea) {
				writeTextArea(out, (UITextArea) control);
			}else if ( control instanceof UIDate) {
				writeDate(out,(UIDate) control);
			}else if ( control instanceof UIImage) {
				writeImage(out,(UIImage) control);
			}else if ( control instanceof UIButton) {
				writeButton(out, (UIButton) control);
			}else if ( control instanceof UIList) {
				UIList lst =  (UIList) control;
				if (Utils.isNullMap( lst.getOptions()) && !Utils.isNullString(element.getPopulator())) {
					lst.setOptions(element.populateOptions(controller,object,element.getPopulator()));
				}
				writeList(out,lst);
			}else if ( control instanceof UIDataList) {
				UIDataList lst =  (UIDataList) control;
				if (Utils.isNullList( lst.getOptions()) && !Utils.isNullString(element.getPopulator())) {
					Map childrenOptions = element.populateOptions(controller,object,element.getPopulator());
					if (childrenOptions  != null ) {
						List list = new ArrayList(childrenOptions.keySet());
						lst.setOptions(list);
					}
				}
				writeDataList(out,lst);
			}else if ( control instanceof UICheckBox) {
				writeCheckBox(out , (UICheckBox) control );
			}else if ( control instanceof UIBooleanCheckBox) {
				writeBooleanCheckBox(out , (UIBooleanCheckBox) control );
			}else if ( control instanceof UIRadioBox) {
				writeRadioBox(out , (UIRadioBox) control );
			}else if ( control instanceof UILabel) {
				writeLabel(out, (UILabel) control,true);
			}else if ( control instanceof UIMenu) {
				writeMenu(out, (UIMenu) control);
			}else if ( control instanceof UIBreak) {
				writeBreak(out, (UIBreak) control);
			}else if ( control instanceof UIStaticText) {
				writeStaticText(out, (UIStaticText) control);
			}else if ( control instanceof UIParagraph) {
				writeParagraph(out, (UIParagraph) control,value,controller);
			}else if ( control instanceof UIHeader) {
				writeHeader(out, (UIHeader) control);
			}else if ( control instanceof UIDiv) {
				writeDiv(out, (UIDiv) control,value,controller);
			}else if ( control instanceof UIDialog) {
				writeDialog(out, (UIDialog) control,value,controller);
			}else if ( control instanceof UIIFrame) {
				writeIFrame(out, (UIIFrame) control,value,controller);
			}else if ( control instanceof UIFilterSet) {
				writeFilterSet(out, (UIFilterSet) control,value,controller,(UIListPage)currentPage);
			}else if ( control instanceof UIErrorList) {
				writeErrorList(out, (UIErrorList) control,value,controller);
			}else if ( control instanceof UIErrorObject) {
				writeError(out, (UIErrorObject) control,value,controller);
			}else if ( control instanceof UIElementCollection) {
				writeElementCollection(out, (UIElementCollection) control,value,object,controller);
			}else if ( control instanceof UITabSet) {
				writeTabSet(out, (UITabSet) control,value,controller);
			}else if ( control instanceof UITab) {
				writeTab(out, (UITab) control,value,controller);
			}else if ( control instanceof UINote) {
				writeNote(out, (UINote) control);
			}else if ( control instanceof UIFixedPanel) {
				writeFixedPanel(out,(UIFixedPanel) control,value,controller);
			}else{
				writeElement(element,value,controller);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	protected void writeFixedPanel(PrintWriter out, UIFixedPanel panel,Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(panel.getStyle()) ? "class=\"" + panel.getStyle() + "\"" : "");
		out.println("<div id= \""+ panel.getId() +" \" " + style + " >");
		if (!Utils.isNullList(panel.getElements())) {
			for (UIElement element : panel.getElements() ) {
				writeElement(element,value,controller);
			}
		}else {
			out.println("this is left nave");
		}
		out.println("</div>");
	}

	@Override
	public void write(UIPage page, ModelObject object) throws RadsException {
		try
		{
			PrintWriter out = (PrintWriter) board;
			this.currentPage =page ;
			if (page instanceof  UITransactionPage) {
				writeTransactionPage(out, (UITransactionPage)page, object,page.getViewController());
			}else if (page instanceof  UICRUDPage) {
				writeAddEditPage(out, (UICRUDPage)page, object,page.getViewController());
			}else if (page instanceof  UIListPage ) {
				writeListPage(out, (UIListPage)page , object,page.getViewController());
			}else if (page instanceof  UILookupPage ) {
				writeLookupPage(out, (UILookupPage)page , object,page.getViewController());
			} else if(page instanceof  UIGeneralPage) {
				writeGeneralPage(out, (UIGeneralPage)page, object, page.getViewController());
			}
						
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new RadsException(ex) ;
		}
	}
	
	protected void writeTabSet(PrintWriter out, UITabSet tabSet,Object value,ViewController controller) throws IOException {
		
		String width = !Utils.isNullString(tabSet.getWidth())?"width =\""+tabSet.getWidth() +"\"":"";
		String style = (!Utils.isNullString(tabSet.getStyle()) ? "class=\"" + tabSet.getStyle() + "\"" : "");
		String titleStyleUnSelected  = tabSet.getUnSelectedTabStyle() ;
		String titleStyleSelected =  tabSet.getSelectedTabStyle() ;
		out.println("<table "+ style + width +" >");
	//	out.println("<div id= \""+ tabSet.getId() + "\"" +  style + ">");
		out.println("<tr " + style + "><th " + style + ">");
		int count = 0 ;
		for(UITab tab :  tabSet.getTabs()) {
	
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			String labelValue =  facade.externalize(context, tab.getLabel().getLabel());
			out.println("<span id=\"SP" + tab.getId()+ "\"  class = \""+ ((count==0)?titleStyleSelected:titleStyleUnSelected) + 
					"\" onClick =\"toggleTabVisbility('"+ tab.getId()+"','SP"+ tab.getId() +"','"+  titleStyleSelected+"','" + titleStyleUnSelected +"');\"> " 
			+ labelValue + "</span>");
			count ++;
		}
		out.println("</th></tr>");
		int index = 0;
		out.println("<tr><td >");
		for(UITab tab :  tabSet.getTabs()) {
			tab.setWidth(width);
			//tab.setStyle(tabSet.getStyle());
			if ( index == 0 )
				tab.setVisible(true);
			index ++;
			writeElement(new UIElement(tab),value,controller);
		}
		
		out.println("</td></tr></table>");
		//out.println("</div>");
	}
	
    protected void writeTab(PrintWriter out, UITab tab,Object value,ViewController controller) throws IOException {
    	
    	String vb =  (tab.isVisible())?"":"none" ;
    	String width = tab.getWidth() ;
    	String style = (!Utils.isNullString(tab.getStyle()) ? "class=\"" + tab.getStyle() + "\"" : "");
    	out.println("<div id= \""+ tab.getId() +"\" style=\"display:"+  vb +"\" >\n");
    	
		for (UIElement element : tab.getElements() ) {
			writeElement(element,value,controller);
		}
		
		out.println("</div>");
		
	}

    protected void writeIFrame(PrintWriter out, UIIFrame frame,Object value,ViewController controller) throws IOException {
		String src = Utils.isNull(frame.getSrc())?"":"src='" + frame.getSrc() + "'";
		String style = (!Utils.isNullString(frame.getStyle()) ? "class=\"" + frame.getStyle() + "\"" : "");
		out.println("<iframe id= \""+ frame.getId() +   "\" " + src + " " + style +">");
		if(!Utils.isNullList(frame.getElements())) {
			for (UIElement element : frame.getElements() ) {
				writeElement(element,value,controller);
			}
		}
		out.println("</iframe>");
	}
    
    protected void writeDialog(PrintWriter out, UIDialog dialog,Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(dialog.getStyle()) ? "class=\"" + dialog.getStyle() + "\"" : "");
		out.println("<Dialog id= \""+ dialog.getId() +   "\" " + style + ">");
		if(!Utils.isNullList(dialog.getElements())) {
			for (UIElement element : dialog.getElements() ) {
				writeElement(element,value,controller);
			}
		}
		out.println("</Dialog>");
		
	}
    
    protected void writeDiv(PrintWriter out, UIDiv div,Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(div.getStyle()) ? "class=\"" + div.getStyle() + "\"" : "");
		String width = (!Utils.isNullString(div.getWidth()) ? "width=" + div.getWidth() : "");
		String align = (!Utils.isNullString(div.getAlign()) ? "align=\"" + div.getAlign()+ "\"" : "");
	
		out.println("<div id= \""+ div.getId() +   "\" " + style + " " + width + " " + align + " >");
		if(!Utils.isNullList(div.getElements())) {
			for (UIElement element : div.getElements() ) {
				writeElement(element,value,controller);
			}
		}
		out.println("</div>");
	}
	
	protected void dispalyFilterValues(PrintWriter out, UIListPage page,ViewController controller) throws IOException {
		Filter filter = page.getFilter() ;
		for(FilterNode node : filter.getNodeList()) {
			
		}
		
	}

//	protected getFilterValue()
	
	protected void updateFilterSet( UIFilterSet filterSet, Filter filter) {
		List<UIElement> filterElements = filterSet.getAllFilterElements() ;
		if (filter == null ) return ;
		if(!Utils.isNullList(filter.getNodeList())) {
			for (FilterNode node : filter.getNodeList()) {
				for (UIElement element : filterElements) {
					if (node.getField().equals(element.getModelProperty())) {
						if (element.getControl() instanceof UIDate &&  !Utils.isNullString(String.valueOf(node.getValue()))) {
							try  {
							UIDate dt = (UIDate)element.getControl();
							java.util.Date dtVal = Utils.stringToDate(String.valueOf(node.getValue()),"yyyy-MM-dd");
						    element.setValue(dtVal);
						    element.setConstantValue(dtVal);
							}catch(Exception ex) {
								ex.printStackTrace();
							}
						}else { 
							element.setValue(node.getValue());
							element.setConstantValue(String.valueOf(node.getValue()));
						}
					
						break;
					}
				}
			}
		}
		
	}
	
	protected void writeFilterSet(PrintWriter out, UIFilterSet filterSet,Object value,ViewController controller, UIListPage page) throws IOException {
		if (filterSet!= null && page.getFilter() != null) {
			updateFilterSet(filterSet,page.getFilter());
		}
		if (filterSet!= null )	{
			String style = (!Utils.isNullString(filterSet.getStyle()) ? "class=\"" + filterSet.getStyle() + "\"" : "");
			out.println("<div id= \""+ filterSet.getId() +   "\" " + style + ">");
			if(!Utils.isNullList(filterSet.getElements())) {
				for (UIElement element : filterSet.getElements() ) {
					writeElement(element,value,controller);
				}
			}
			out.println("</div>");
		}
		
	}

	protected void writeError(PrintWriter out, UIErrorObject errorObject,Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(errorObject.getStyle()) ? "class=\"" + errorObject.getStyle() + "\"" : "");
		out.println("<span id=\"" + errorObject.getId()+  "\" " +  style + " >" + errorObject.getMessage()  +    "</span>");
	}
	
	protected void writeErrorList(PrintWriter out, UIErrorList errorList,Object value,ViewController controller) throws IOException {
		UIErrorList errorListfromPage = currentPage.getErrorList() ;
		if (!Utils.isNullList(errorListfromPage.getErrorObjects())) {
			out.println("<div  class = \""+ errorList.getStyle() +"\">");
			printErrors(currentPage, out);
			out.println("</div>");
			/*for (UIErrorObject errorObject : errorListfromPage.getErrorObjects()){
				errorObject.setStyle(errorList.getStyle());
				out.println("<tr><td>");
				writeError( out,  errorObject, value, controller);
				out.println("</td></tr>");
			}
			out.println("</table>");*/
		}
	}
	
	protected void writeElementCollection(PrintWriter out, UIElementCollection collection,Object value,Object object,ViewController controller) 
			throws Exception {
		if(!Utils.isNullList(collection.getElements())) {
			for (UIElement element : collection.getElements() ) {
				if (element.shouldDisplay((ModelObject)object, controller))
					writeElement(element,value,controller);
			}
		}
	}
	
	protected void writeNote(PrintWriter out, UINote note) throws IOException {
		out.println(note.getNote());
	}
	
	protected void writeBreak(PrintWriter out, UIBreak breakUI) throws IOException {
		out.println("<br>");
		
	}
	
	protected void writeStaticText(PrintWriter out, UIStaticText staticText) throws IOException {
		out.println(staticText.getValue());
	}
	
	protected void writeParagraph(PrintWriter out, UIParagraph para,Object value,ViewController controller) throws IOException {
		out.println("<p>" );
		if(!Utils.isNullList(para.getElements())) {
			for (UIElement element : para.getElements() ) {
				writeElement(element,value,controller);
			}
		}
		out.println("</p");
		
		
	}
	protected void writeHeader(PrintWriter out, UIHeader header) throws IOException {
		int level =  header.getLevel() ;
		String style = (!Utils.isNullString(header.getStyle()) ? "class=\"" + header.getStyle() + "\"" : "");
		out.println("<h" + level + " " + style + ">");
		out.println(header.getValue());
		out.println("</h" + level + ">");
	}

	
	protected void writeDataList(PrintWriter out, UIDataList list) throws IOException {
		out.println("<input type =\"text\" id=\"" + list.getTextId() + "\"  "  +" name =\"" + list.getTextId()  + "\"  value=\""+ 
				Utils.getFormattedValue(list.getValue()) +"\" list = \"" + list.getId() + "\" />");
		out.println("<datalist id=\"" + list.getId() + "\">");
		if (!Utils.isNullList(list.getOptions()))
			for (String str : list.getOptions()) {
				out.println("<option value =\""+ str +   "\">");
			}
		out.println("</datalist>");
	}
	
	protected void writeList(PrintWriter out, UIList list) throws IOException {
		IExternalizeFacade facade = null;
		if(list.isExternalize()) {
			facade  = currentPage.getExternalizeFacade() ;
		}
		String dblClick = Utils.isNullString(list.getDblClickJS())?"":"ondblclick="+list.getDblClickJS() ;
		String onChangeJS = Utils.isNullString(list.getOnChangeJS())?"":"onchange=\""+list.getOnChangeJS() + "\"" ;
		String dataProp = "data-property=\"" + list.getDataProperty() + "\"";  
 		String sizeStr = Utils.isNullString(list.getSize()) ?"":" size= " +  list.getSize() ;
 		String style = (!Utils.isNullString(list.getStyle()) ? "class=\"" + list.getStyle() + "\"" : "");
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

	

	
	protected void writeBooleanCheckBox(PrintWriter out, UIBooleanCheckBox checkBox) throws IOException {
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
		out.println("<input type =\"checkbox\" id=\"" +  checkBox.getId() + "\"  name =\"" +checkBox.getId()  + "\" " + dataProp + 
				" value =\""+ st + "\"  onclick=\"workBooleanCheckBoxControl(this,'"+ checkBox.getHiddenControlId() +"')\" " + selected + " /><span>"	  +"</span>"  ) ;
		
		
	}
	protected void writeCheckBox(PrintWriter out, UICheckBox checkBox) throws IOException {
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
				out.println("<input type =\"checkbox\" id=\"" +  checkBox.getId() + "\"  name =\"" +checkBox.getId()  + "\" " + dataProp + 
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
			out.println("<input type =\"radio\" id=\"" +  radioBox.getId() + "\" name = \""+ radioBox.getId() 
					+"\"  "  +  dataProp +  " value =\""+ st + "\" " + selected + " />"  + displayVal  + "&nbsp;") ;
		}
	}
	
	
	protected void writeLabel(PrintWriter out, UILabel label, boolean isActualValue) throws IOException {
		String crudMarker = ( !isActualValue )?currentPage.getTemplate().getCrudMarker():"";
		String style = (!Utils.isNullString(label.getStyle()) ? "class=\"" + label.getStyle() + "\"" : "");
		String sizeStr = label.getSize()>0?"size=\"" + label.getSize()+"\"":"" ;  
		if (!label.isExternalize())
			out.println("<span id=\"" + label.getId()+  "\" name=\"" + label.getId()+  "\" name =\"" + label.getId()+  "\"" +  style +   " " +  sizeStr +  " >" +
					label.getLabel() +     "</span>");
		else {
			String labelKey = label.getLabel();
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			String labelValue =  facade.externalize(context, labelKey);
			out.println("<span id=\"" + label.getId()+  "\" name =\"" + label.getId()+  "\" " +  style + " >" +
					(Utils.isNull(labelValue)?"":labelValue)   + (!Utils.isNull(crudMarker)?crudMarker:"") + "</span>");
		}
	
	}
	
	protected void writeMandatoryFieldLabel(PrintWriter out, UILabel label, String marker, boolean isActualValue) throws IOException {
		String crudMarker = ( !isActualValue )?currentPage.getTemplate().getCrudMarker():"";
		String style = (!Utils.isNullString(label.getStyle()) ? "class=\"" + label.getStyle() + "\"" : "");
		if (!label.isExternalize())
			out.println("<span id=\"" + label.getId()+  "\" " +  style + " >" +
					label.getLabel() + marker  + "</span>");
		else {
			String labelKey = label.getLabel(); 
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			String labelValue =  facade.externalize(context, labelKey);
			out.println("<span id=\"" + label.getId()+  "\" " +  style + " >" +
					labelValue + (!Utils.isNull(crudMarker)?crudMarker:"") +  marker  + "</span>");
		}
		
	
	}
	protected void writeMenu(PrintWriter out, UIMenu menu) throws IOException {
		String menuText = menu.getMenuText();
		if(menu.isExternalize()) {
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			menuText =  facade.externalize(context, menuText);
		}
		String style = (!Utils.isNullString(menu.getStyle()) ? "class=\"" + menu.getStyle() + "\"" : "");
		String menuClick = "\"window.location.href ='"+ menu.getMenuLink() +"'\"" ;
		String groupId= menu.getGroupId() ;
		if (!Utils.isNullList(menu.getChildMenus())) {
			menuClick = "toggleMenuVisibility('" + groupId +"')  ;" ;
		}
		out.println("<li id=\"" + menu.getId()+ "\" "+ style +" onClick=" + menuClick +">" + menuText );
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
	
	
	
	protected void writeLookupPageButton(PrintWriter out, UIButton button,FixedAction action, UILookupPage page) throws IOException {
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
		out.println("<input type =\"button\" id=\"" + button.getId() + "\"  "+ style + " name =\"" + button.getId()  + "\" value=\""+ 
				caption +"\" onClick=\""+ button.getOnClickJS() +"\"/>");
	}
	
	protected boolean isJSFunction(PrintWriter out, UIButton button,FixedAction action) {
		if (action == FixedAction.ACTION_PLAINSUBMIT  && Utils.isNull(button.getFixedActionParam()) ) {
			button.setOnClickJS("submit();");
			return true ;
		} else if (action == FixedAction.ACTION_RELOAD ) {
			button.setOnClickJS("location.reload();");
			return true;
		}
		return false;
	}
	
	protected void writeButton(PrintWriter out, UIButton button) throws IOException {
		String style = (!Utils.isNullString(button.getStyle()) ? "class=\"" + button.getStyle() + "\"" : "");
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
			writeLookupPageButton(out, button, action,(UILookupPage) currentPage);
			return ;
		} else if (action == FixedAction.CANCELDIALOG	) {
			button.setOnClickJS("cancelDialog('"+ param +"');"); 
			out.println("<input type =\"button\" id=\"" + button.getId() + "\"  "+ style + " name =\"" + button.getId()  + "\" value=\""+ 
					caption +"\" onClick=\""+ button.getOnClickJS() +"\"/>");
			return ;
		}else  if (action == FixedAction.CANCELWINDOW	) {
			button.setOnClickJS("cancelWindow();");
			out.println("<input type =\"button\" id=\"" + button.getId() + "\"  "+ style + " name =\"" + button.getId()  + "\" value=\""+ 
					caption +"\" onClick=\""+ button.getOnClickJS() +"\"/>");
			return ;
		}

		String validateFunc = button.getFixedAction().getValidateFunc() ;
		String act = FixedAction.getFixedActionString(button.getFixedAction()) ;
		out.println("<input type =\"button\" id=\"" + button.getId() + "\" "+ style + " name =\"" + button.getId()  + "\" value=\""+ 
				caption +"\" onClick=\"applyFixedActionServer('"+ act +"','"+ param +"',"+ validateFunc + ")\"/>");
		
	}else {
		out.println("<input type =\"button\" id=\"" + button.getId() + "\"  "+ style + " name =\"" + button.getId()  + "\" value=\""+ 
				caption +"\" onClick=\""+ button.getOnClickJS() +"\"/>");
	}
	
	}
	
	protected void writeDate(PrintWriter out, UIDate dateC) throws ParseException, IOException {
		
		String dataProp = "data-property=\"" + dateC.getDataProperty() + "\""; 
		out.println("<input type =\"date\" id=\"" + dateC.getId() + "\"  name =\"" +dateC.getId()  + "\"  "  + dataProp +  " value=\""+ 
				Utils.dateToString((java.util.Date)dateC.getValue(),"yyyy-MM-dd") +"\"/>");
	
	}
	
	protected void writeTextArea(PrintWriter out, UITextArea textArea) throws IOException {
		String dataProp = "data-property=\"" + textArea.getDataProperty() + "\"";  
		String str = Utils.isNullString(String.valueOf(textArea.getValue()))?"":String.valueOf(textArea.getValue());
		out.println("<textarea  id=\"" + textArea.getId() + "\" name =\"" +textArea.getId()  + "\" "  + dataProp + " rows=\""+ textArea.getRows() +"\" cols=\"" + textArea.getCols()
		+ "\">" + str + "</textarea>");
		
	
	}
	
	protected void writeFileUpload(PrintWriter out, UIFileUpload fileUpload) throws Exception {
		String dataProp = "data-property=\"" + fileUpload.getDataProperty() + "\"";
		String accept = !Utils.isNull(fileUpload.getAccept())?"accept=\""+fileUpload.getAccept() + "\"":"";
		String onchange = !Utils.isNull(fileUpload.getOnChangeJS())?"onchange=\"" + fileUpload.getOnChangeJS() +"\"":"";  
		out.println("<input type =\"file\" id=\"" + fileUpload.getId() + "\"  " +  dataProp +  " " + accept + " " + onchange  + " name =\"" + fileUpload.getId()  + "\"/>");

	}
	protected void writeText(PrintWriter out, UIText text) throws IOException {
		String onlyNum = text.isOnlyNumbers()?"onkeypress=\"return isNumberKey(event)\" ":"";
		String sizeStr = text.getSize()>0?"size=\"" + text.getSize()+"\"":"" ;  
		String required = text.isRequired()?"required":"";
		String autoFocus = text.isAutofocus()?"autoFocus":"";
		
		String readOnly = text.isReadOnly()?"readonly":"" ;
		String style = (!Utils.isNullString(text.getStyle()) ? "class=\"" + text.getStyle() + "\"" : "");
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
		String style = (!Utils.isNullString(text.getStyle()) ? "class=\"" + text.getStyle() + "\"" : "");
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
		String style = (!Utils.isNullString(emailControl.getStyle()) ? "class=\"" + emailControl.getStyle() + "\"" : "");
		IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
		String placeHolder = Utils.isNull(emailControl.getPlaceHolder())?"":"placeholder=\"" + facade.externalize(context,emailControl.getPlaceHolder())  + "\"";
		String dataProp = "data-property=\"" + emailControl.getDataProperty() + "\""; 
		String required = emailControl.isRequired()?"required":"";
		String autoFocus = emailControl.isAutofocus()?"autoFocus":"";
		
		out.println("<input type =\"Password\" id=\"" + emailControl.getId() + "\"  "  +" name =\"" + emailControl.getId()  + "\" value=\""+ 
				Utils.getFormattedValue(emailControl.getValue()) +"\""+ sizeStr + " " + placeHolder  + " " + style  + " " + required + " " + autoFocus + " " +  dataProp + " />");
	
	}
	
	protected String declareArrayforAdditionalControls(Map additionalKeys , String lookupText)
	{
		StringBuffer buffer = new StringBuffer("<script>\n");
		if ( !Utils.isNullMap(additionalKeys)) {
			buffer.append(" var " + RadsControlConstants.ADDITIONALOOKUPCTRLS + lookupText +"=[");
			
			Iterator iterator = additionalKeys.keySet().iterator() ;
			while(iterator.hasNext())  {
				String key = (String)iterator.next() ;
				buffer.append("'" +  key  + "'");
				if(iterator.hasNext())
					 buffer.append(",");
			}
			buffer.append("];\n");
		}
		
		buffer.append("</script>");
		return buffer.toString() ;
	}
	
	protected String declareArrayforAdditionalFields(Map additionalKeys , String lookupText)
	{
		StringBuffer buffer = new StringBuffer("<script>\n");
		if ( !Utils.isNullMap(additionalKeys)) {
			buffer.append(" var " + RadsControlConstants.ADDITIONALOOKUPFIELDS + lookupText +"=[");
			
			Iterator iterator = additionalKeys.keySet().iterator() ;
			while(iterator.hasNext())  {
				String key = (String)iterator.next() ;
				String property =(String) additionalKeys.get(key);
				buffer.append("'" +  property  + "'");
				if(iterator.hasNext())
					 buffer.append(",");
			}
			buffer.append("];\n");
		}
		
		buffer.append("</script>");
		return buffer.toString() ;
	}
	
	protected void writeLookupDialog(PrintWriter out, UILookupText textLookup) {
		String dialogStyle = (!Utils.isNullString(textLookup.getDialogStyle()) ? "class=\"" + textLookup.getDialogStyle() + "\"" : "");
		String frameStyle =(!Utils.isNullString(textLookup.getFrameStyle()) ? "class=\"" + textLookup.getFrameStyle() + "\"" : "");
		String urlText = textLookup.getUrl(portalPrefix) + "&lookupType=" + textLookup.getLookupType() + "&parentControl=" +  textLookup.getId() + 
				"&dialogId=" +textLookup.getDialogId()  ;
		out.println("<Dialog id ='" + textLookup.getDialogId() + "' " +  dialogStyle+ " >");
		out.println("<Iframe id ='idFRM" + textLookup.getDialogId() + "' src='" + urlText +  "' " + frameStyle +">" );
		out.println("</iframe>");
		out.println("</Dialog>");
	}
	
	
	protected void writeLookupText(PrintWriter out, UILookupText textLookup) throws IOException {
		String sizeStr = textLookup.getSize()>0?"size=\"" + textLookup.getSize()+"\"":"" ; 
		String dataProp = "data-property=\"" + textLookup.getDataProperty() +  "\"";
		out.println("<input type =\"text\" id=\"" + textLookup.getId() + "\" name =\"" + textLookup.getId()  + "\" "  + dataProp  +  " value=\""+ 
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
				out.println("<button  type =\"button\"   name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupDialog('" + textLookup.getDialogId()  + "',this,'"+additionalControl+"');\" >" + "..." +" </button>");
			else {
				String additionalLookupCtrls = declareArrayforAdditionalControls(textLookup.getSupplimentaryFields(), textLookup.getId());
				out.println(additionalLookupCtrls);
				String additionalLookupFields= declareArrayforAdditionalFields(textLookup.getSupplimentaryFields(), textLookup.getId());
				out.println(additionalLookupFields);
				String variableCtrlName = RadsControlConstants.ADDITIONALOOKUPCTRLS +  textLookup.getId();
				String variableFieldsName = RadsControlConstants.ADDITIONALOOKUPFIELDS +  textLookup.getId();
				out.println("<button  type =\"button\"   name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupDialogWithAdditionalFields('" + textLookup.getDialogId()
						+ "',this,'"+additionalControl+"',"+variableCtrlName+"," + variableFieldsName + ");\" >" + "..." +" </button>");
				
			}
				
		}else {
			out.println("<button  type =\"button\"    name=\"btn+"+ textLookup.getId() + "\" onClick=\"showLookupWindow('"+ urlText +"','"+title+"','"+textLookup.getId()+"','"+height+"','"+ width+"','"+additionalControl+"');\" >" + "..." +" </button>");
		}
		
		
	
	}
	
	protected void writeImage(PrintWriter out, UIImage img) throws IOException {
		//String vl = (img.getValue() != null)?String.valueOf(img.getValue()):img.getSrc();
		String vl = img.getSrc();
		String onClickJS = img.getOnClick();
		String clickString = Utils.isNullString(onClickJS)?"":" onclick = \"" + onClickJS + "\"";
		String title  = Utils.isNullString(img.getTitle())?"":" title=\"" + img.getTitle() + "\"";
		out.println("<img id=\"" + img.getId() + "\" src=\""+ 
				vl +  "\" height=\"" + img.getHeight()  +"\" width=\"" + img.getWidth() + "\" "+  title + " " +  clickString + "    />" ); 
	
	}
	
	protected void writeHyperLink(PrintWriter out, UIHyperLink link) throws IOException {
		if(!link.isExternalize())  {
			out.println("<a id=\"" + link.getId() + "\" href=\""+ 
				link.getHyperLink() +"\"/>" + link.getInnerText() +"</a>");
		}else {
			String labelKey = link.getInnerText();
			IExternalizeFacade facade  = currentPage.getExternalizeFacade() ;
			String labelValue =  facade.externalize(context, labelKey);
			out.println("<a id=\"" + link.getId() + "\" href=\""+ 
					link.getHyperLink() +"\"/>" + labelValue +"</a>");
		}
	
	}
	protected void writeHidden(PrintWriter out, UIHidden hidden) throws IOException {
		String dataProp = "data-property=\"" + hidden.getDataProperty() + "\"";
		if (!Utils.isNullString(hidden.getId())) {
		out.println("<input type =\"hidden\" id=\"" + hidden.getId() + "\"name =\"" + hidden.getId()  + "\" "  + dataProp + " value=\""+ 
				hidden.getValue() +"\"/>");
		}
	
	}
	
	private void writeTablediv(PrintWriter out, UITableCol col, String divTag, Object value,ViewController controller) throws IOException {
		String width = (!Utils.isNullString(col.getWidth()) ? "width=" + col.getWidth() : "");
		String colSpan = (!Utils.isNullString(col.getColSpan()) ? "colspan=" + col.getColSpan() : "");
		String align = (!Utils.isNullString(col.getAlign()) ? "align=\"" + col.getAlign()+ "\"" : "");
		String style = (!Utils.isNullString(col.getStyle()) ? "class=\"" + col.getStyle() + "\"" : "");
		String sort = (!Utils.isNullString(col.getSortField()) ? " data-sortfield=\"" + col.getSortField() + "\"" : "");
		String click = "";
		if (currentPage instanceof  UIListPage && !Utils.isNullString(col.getSortField())) {
			 click = " onClick = submitwithSort('"+ col.getSortField() +"');";
			
		}
		out.println("<" + divTag + " " +  width + " " + colSpan + " " + align + " " + sort + " " + style +" " + click + " >");
		if (!Utils.isNullList(col.getElements())) {
			for (UIElement element : col.getElements() ) {
				writeElement(element, value,controller);
			}
		}
		out.println("</" + divTag+">");
	}
	
	protected void writeTableCol(PrintWriter out, UITableCol col, Object value,ViewController controller) throws IOException {
		/*if(col.getElements() != null && col.getElements().size() == 1  && col.getElements().get(0) instanceof UICondition &&
				((UICondition)col.getElements().get(0)).getControl() == null ) {
			return; 
		}*/
		writeTablediv(out, col, "td",value,controller);
	}
	
	protected void writeTableHead(PrintWriter out, UITableHead head, Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(head.getStyle()) ? "class=\"" + head.getStyle() + "\"" : "");
		out.println("<THEAD " + style  +  ">");
		 if (head.getRow() != null ){
			 for (UITableCol col : head.getRow().getCols() ) {
					writeTablediv(out, col, "th",value,controller);
				}
		 }
		out.println("</THEAD>");
	}
	
	protected void writeTableFooter(PrintWriter out, UITableFooter footer, Object value,ViewController controller) throws IOException {
		String style = (!Utils.isNullString(footer.getStyle()) ? "class=\"" + footer.getStyle() + "\"" : "");
		out.println("<TFOOT " + style  +  ">");
		 if (footer.getRow() != null ){
			 for (UITableCol col : footer.getRow().getCols() ) {
					writeTablediv(out, col, "td",value,controller);
				}
		 }
		out.println("</TFOOT>");
	}
	
	protected void writeTableRow(PrintWriter out, UITableRow row,Object value,ViewController controller) throws IOException,CloneNotSupportedException {
		String style = (!Utils.isNullString(row.getStyle()) ? "class=\"" + row.getStyle() + "\"" : "");
		out.println("<tr " + style  +  ">");
		if (! Utils.isNullList(row.getCols())){
			for (UITableCol col : row.getCols() ) {
				UIElement element  = new UIElement((UITableCol)col.clone()) ; 
				element.setModelProperty(col.getDataProperty());
				element.setValue(value);
				writeElement(element, value,controller); 
				// THe code is commented out because Editing in Datasheet page was no working ; col.elems[0].value= descript col.dataprop = desc
		//	writeTableCol(out,col,value ,controller);
			}
		}
		out.println("</tr>");
	}
	
	protected void writeTable(PrintWriter out, UITable table, Object value,ViewController controller) throws IOException,CloneNotSupportedException  {
		String width = (!Utils.isNullString(table.getWidth()) ? "width=" + table.getWidth() : " ");
		String style = (!Utils.isNullString(table.getStyle()) ? "class=\"" + table.getStyle() + "\"" : "");
		out.println("<Table id=\"" + table.getId() + "\" " + style  + width  + ">");
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
		out.println("</Table>");
	}
	
	protected void writeUIGraphLegend(PrintWriter out, UIGraphLegend graphLegend, Object value,ViewController controller) throws IOException{
		int curX = graphLegend.getX();
		int curY =  graphLegend.getY();
		/*out.println("<rect x =\""+ curX  + "\"" + "  y =\""+ curY+ "\"   width=\"" + graphLegend.getWidth() + "\" height=\"" + graphLegend.getHeight() 
				+ "\" fill=\"none\" stroke=\""+ graphLegend.getBorderColor() +"\"> </rect>");*/
		Map entries = graphLegend.getColorDescriptions() ;
		Iterator it = entries.keySet().iterator();
		while (it.hasNext()) {
			String key =String.valueOf(it.next());
			UIGraphText  text  = (UIGraphText) entries.get(key);
			text.setX(curX + graphLegend.getConstWidth());
			text.setY(curY + graphLegend.getConstHeight());
			out.println("<rect x =\""+ curX  + "\"" + "  y =\""+ curY+ "\"   width=\"10\" height=\"10\" fill=\""+ key + "\" > </rect>");
			writeSVGText(out, text, value, controller);
			curX += graphLegend.getxWidthPerEntries() ;
			curY += graphLegend.getyHeightPerEntries();
		}
		out.println("</rect>");
	}
	
	
	protected void writeSVGText(PrintWriter out, UIGraphText graphText, Object value,ViewController controller) throws IOException {
		String style = "writing-mode: tb;";
		if(graphText.getDirection()== UIGraphText.Direction.HORIZONTAL)  {
			style = "";
		}
		out.println("<text x=\"" + graphText.getX() +"\" y =\"" + graphText.getY() +"\" fill=\"" + graphText.getFill() + 
				"\" style=\"" + style + "\">" );
		out.println(graphText.getText());
		out.println("</text>");
	}
	protected void writeSVGBar(PrintWriter out, UIGraphBar bar, Object value,ViewController controller) throws IOException {
		out.println("<rect x =\""+ bar.getX() + "\"" + "  y =\""+ (bar.getY() ) + "\"   width=\"" + bar.getWidth() + "\" height=\"" + bar.getHeight() 
				+ "\" fill=\""+ bar.getFill() +"\"> </rect>");
		if (bar.getText() != null) {
			writeSVGText(out, bar.getText(), value, controller);
		}
	}
	protected void writeSVGCircle(PrintWriter out, UIGraphCircle circle, Object value,ViewController controller) throws IOException {
		out.println("<circle  cx =\""+ circle.getCX() + "\"" + "  cy =\""+ (circle.getCY() ) + "\"   r=\"" + circle.getRadius() + "\" fill=\""+ circle.getFill() +"\"> </circle>");
		if (circle.getText() != null) {
			writeSVGText(out, circle.getText(), value, controller);
		}
	}
	protected void writeSVGPath(PrintWriter out, UIGraphPath path, Object value,ViewController controller) throws IOException {
			out.println("<path fill=\"" + path.getFill() + "\" d=\"" + path.getdParam() + "\"/>") ;
	}
	
	protected void writeSVGBarChartBorder (PrintWriter out, UIBarChart chart, Object value,ViewController controller) throws IOException {
		int x1 = chart.getStartX();
		int y1 = chart.getStartY();
		int width = chart.getWidth()  +chart.getStartX() ;
		int height = chart.getBarSectionHeight() + chart.getStartY();
		out.println("<line x1=\""+  x1 +"\" y1=\"" + height +"\" x2=\""+ width +"\" y2=\""+ height +"\" style=\"stroke:rgb(255,0,0);stroke-width:2\"/>\"");
		out.println("<line x1=\""+  x1 +"\" y1=\"" + y1 +"\" x2=\""+ x1 +"\" y2=\""+ height +"\" style=\"stroke:rgb(255,0,0);stroke-width:2\"/>\"");
	}
	
	protected void writeSVGLineChartBorder (PrintWriter out, UILineChart chart, Object value,ViewController controller) throws IOException {
		int x1 = chart.getStartX();
		int y1 = chart.getStartY();
		int width = chart.getWidth() ;
		int height = chart.getHeight()-chart.getMarginHeight();
		out.println("<line x1=\""+  x1 +"\" y1=\"" + height +"\" x2=\""+ width +"\" y2=\""+ height +"\" style=\"stroke:"+chart.getBorderColor()+";stroke-width:2\"/>\"");
		out.println("<line x1=\""+  x1 +"\" y1=\"" +  chart.getMarginHeight() +"\" x2=\""+ x1 +"\" y2=\""+ height +"\" style=\"stroke:"+chart.getBorderColor()+";stroke-width:2\"/>\"");
	}
	
	protected void writeSVGRangeforBarChart(PrintWriter out, UIBarChart chart, Object value,ViewController controller) throws IOException {
		SortedSet<Integer> rangeValue = chart.getValueRanges();
		if (rangeValue == null || rangeValue.size() == 0 ) return ;
		int maxValue =  rangeValue.last();
		int minValue = rangeValue.first() ;
		int window = chart.getNoYAxisDivisions();
		int remainder = rangeValue.last() % window ;
		if (remainder != 0 ) {
			maxValue = (window-remainder) + rangeValue.last() ;
		}
		double groupValue =  ( (double)maxValue  ) / (double)window  ;
		double curY = chart.getBarSectionHeight() + chart.getStartY() ;
		double propYValue = ((double)chart.getBarSectionHeight() / rangeValue.last()) *groupValue  ;
		double yValueWindow = (double)chart.getBarSectionHeight()  /(double) window ;
		for (int i = 0 ; i <=  window ; i ++ ) {
			curY-=propYValue ;
			if (curY <=0 ) break ;
			out.println("<text x=\"" +(chart.getStartX()+1) +"\" y =\"" + curY +"\" fill=\"Red\" >" );
			out.println ((int) groupValue * (i +1 ) );
			out.println("</text>");

		}
	}
	
	protected void writeSVGRangeforLineChart(PrintWriter out, UILineChart chart, Object value,ViewController controller) throws IOException {
		SortedSet<Integer> rangeValue = chart.getValueRanges();
		if (rangeValue == null || rangeValue.size() == 0 ) return ;
		int maxValue =  rangeValue.last();
		int minValue = rangeValue.first() ;
		int window = chart.getLineSets().get(0).getLines().size();
		int remainder = rangeValue.last() % window ;
		if (remainder != 0 ) {
			maxValue = (window-remainder) + rangeValue.last() ;
		}
		double groupValue =  ( (double)maxValue  ) / (double)window  ;
		double curY = chart.getHeight() + chart.getStartY() ;
		double propYValue = ((double)chart.getHeight() / rangeValue.last()) *groupValue  ;
		double yValueWindow = (double)chart.getHeight()  /(double) window ;
		for (int i = 0 ; i <=  window ; i ++ ) {
			curY-=propYValue ;
			if (curY <=0 ) break ;
			out.println("<text x=\"" +(chart.getStartX()+1) +"\" y =\"" + curY +"\" fill=\"Red\" >" );
			out.println ((int) groupValue * (i +1 ) );
			out.println("</text>");

		}
		
	}
	
	protected void writeGaugeChart(PrintWriter out, UIGaugeChart chart, Object value,ViewController controller) throws IOException {
		
		
	}
	
	protected void writePieChart(PrintWriter out, UIPieChart chart, Object value,ViewController controller) throws IOException {
		out.println("<svg  width=\"" +chart.getWidth() + "\" height=\"" + chart.getHeight() + "\">" ) ;
		writeSVGCircle(out, chart.getCircle(), value, controller);
		if(!Utils.isNullList( chart.getPaths())) {
			for (UIGraphPath graphPath : chart.getPaths()) {
				writeSVGPath(out,graphPath,value,controller);
			}
		}
		if  (chart.getLegend() != null) {
			writeUIGraphLegend(out, chart.getLegend(), value, controller);
		}
		if ( chart.getFooterNote()!= null)
			writeSVGText(out,chart.getFooterNote(), value, controller);
		out.println("</svg>");
	}
	
	protected void writeBarChart(PrintWriter out, UIBarChart chart, Object value,ViewController controller) throws IOException {
		
		out.println("<svg class=\"chart\" width=\"" +chart.getWidth() + "\" height=\"" + chart.getHeight() + "\">" ) ;
		out.println("<title>");
		out.println(chart.getTitle());
		out.println("</title>");
		out.println("<desc>");
		out.println(chart.getDesc());
		int chartHeight = chart.getHeight() ;
		out.println("</desc>");
		if (!Utils.isNullList(chart.getBars()))  {
			for (UIGraphBar bar : chart.getBars()) {
			//	bar.setY(chartHeight- bar.getHeight());
				writeSVGBar(out, bar, value, controller);
			}
		}
		writeSVGBarChartBorder(out, chart, value, controller);
		writeSVGRangeforBarChart(out, chart, value, controller);
		out.println("</svg>");
		
	}
	
	protected void writeSVGLine(PrintWriter out, UIGraphLine line, Object value,ViewController controller) throws IOException {
		out.println("<line x1 =\""+ line.getX1() + "\"" + "  y1 =\""+ line.getY1() + "\"   x2=\"" + line.getX2() + "\" y2=\"" + line.getY2()
				+ "\" style=\""+line.getStyle() +"\"> </line>");

	}
	
	protected void writeLineSet(PrintWriter out, UILineSet lineSet, Object value,ViewController controller) throws IOException {
		if (!Utils.isNullList(lineSet.getLines())) {
			for (UIGraphLine line : lineSet.getLines()) {
				line.setStyle(lineSet.getLineStyle());
				writeSVGLine(out,line,value,controller) ;
			}
		}
	}
	
	protected void writeLineChart(PrintWriter out, UILineChart chart, Object value,ViewController controller) throws IOException {
		out.println("<svg class=\"chart\" width=\"" +chart.getCanvasWidth() + "\" height=\"" + chart.getCanvasHeight() + "\">" ) ;
		out.println("<title>");
		out.println(chart.getTitle());
		out.println("</title>");
		out.println("<desc>");
		out.println(chart.getDesc());
		int chartHeight = chart.getHeight() ;
		out.println("</desc>");
		if (!Utils.isNullList(chart.getLineSets())) {
			for (UILineSet lineSet :  chart.getLineSets()) {
				writeLineSet(out,lineSet,value,controller);
			}
		}
		writeSVGLineChartBorder(out, chart, value, controller);
		writeSVGRangeforLineChart(out, chart, value, controller);
		UILineSet set = chart.getLineSets().get(0);
		if(!Utils.isNullList(chart.getDivisions())) {
			for (UILineChart.Division division : chart.getDivisions()) {
				division.getText().setX(division.getX());
				division.getText().setY(division.getY());
				writeSVGText(out,division.getText(),value,controller) ;
			}
		}
		if  (chart.getLegend() != null) {
			writeUIGraphLegend(out, chart.getLegend(), value, controller);
		}
		out.println("</svg>");
	}
	protected void writeForm(PrintWriter out, UIForm form, Object value,ViewController controller) throws IOException {
		String action = Utils.isNullString(form.getAction())?"" :"action = \"" + form.getAction() +"\"";
		String encType =Utils.isNullString(form.getEncType())?"" :"enctype = \"" + form.getEncType() +"\"";
		String style  = Utils.isNullString(form.getStyle())?"" :"class = \"" + form.getStyle() +"\"";
		out.println("<Form id=\"" + form.getId() + "\", name=\"" + form.getId() + "\", method= \"" +
	    form.getMethod()  +"\""+ action+ " " + style + " " + encType +  ">");
		out.println("<P>");
		if (! Utils.isNullList(form.getElements())){
			for (UIElement element: form.getElements()) {
				writeElement(element, value,controller);
			}
		}
		/*if (! Utils.isNullList(form.getHeaderElements())){
			for (UIElement element: form.getHeaderElements()) {
				writeElement(element, value,controller);
			}
		}
		out.println("</P><P>");
		if (! Utils.isNullList(form.getDetailElements())){
			for (UIElement element: form.getDetailElements()) {
				writeElement(element, value,controller);
			}
		}
		out.println("</P><P>");
		if (! Utils.isNullList(form.getSummaryElements())){
			for (UIElement element: form.getSummaryElements()) {
				writeElement(element, value,controller);
			}
		}*/
		out.println("</P>");
//		writeHidden(out, new UIHidden("hdnAction"));
		out.println("</Form>");
		
	}
	
	protected void writeConditonalElements (PrintWriter out, UICondition condition ,  Object value,ViewController controller) throws Exception {
		if (condition.shouldDisplay((ModelObject)value, controller)) {
			List<UIElement> elements =condition.getTrueElements() ;
			for (UIElement elem : elements ) {
				writeElement(elem, value, controller);
			}
		}
		
	}
	
	/*protected void printErrors(UIPage page,PrintWriter out) {
		String showErrors = page.getTemplate().getErrorType().getShowErrorsIn();
		StringBuffer buffer = new StringBuffer();
		if (!Utils.isNullString(showErrors) && !Utils.isNullList(page.getErrors())) {
	//		out.println("<div class=\"" + page.getTemplate().getErrorType().getErrorStyle() +  "\">");
			String tabStyle =page.getTemplate().getErrorType().getTablestyle() ;
			String oddRowStyle =  page.getTemplate().getErrorType().getOddRowStyle() ;
			String evenRowStyle = page.getTemplate().getErrorType().getEvenRowStyle() ;
		//	buffer.append("<table  class=\"" + tabStyle +  "\">");
			int ct = 0 ;
			
			for (RadsError err: page.getErrors()) {
				if (ct % 2 == 0){
					//buffer.append("<tr class=\"" + oddRowStyle +  "\"><td>");
					out.println(" <span class=\"" + oddRowStyle+  "\" >" +  err.getMessage() +"</span> </td></tr>"  );
				}else{
					//buffer.append("<tr class=\"" + evenRowStyle +  "\"><td>");
					out.println(" <span class=\"" + evenRowStyle +  "\" >" +  err.getMessage() +"</span> </td></tr>"  );
				}
					//out.println(" addError ('" + page.getTemplate().getShowErrorsIn() +"','"+page.getTemplate().getErrorStyle() +"','"+ err.getMessage()+"');"   );
				//buffer.append(" <span class=\"" + page.getTemplate().getErrorType().getErrorStyle() +  "\" >" +  err.getMessage() +"</span> </td></tr>"  );
			}
			
			buffer.append("</table></div><br>");
			//out.println("<script>");
			//out.println(" pushError ('" + showErrors+"','"+ buffer.toString()+"');"   );
			//out.println("</script>");
			out.println( buffer.toString());
			
		}
	}*/
	
	protected void printErrors(UIPage page,PrintWriter out) {
		String showErrors = page.getTemplate().getErrorType().getShowErrorsIn();
		StringBuffer buffer = new StringBuffer();
		if (!Utils.isNullString(showErrors) && !Utils.isNullList(page.getErrors())) {
			String tabStyle =page.getTemplate().getErrorType().getTablestyle() ;
			String oddRowStyle =  page.getTemplate().getErrorType().getOddRowStyle() ;
			String evenRowStyle = page.getTemplate().getErrorType().getEvenRowStyle() ;
			buffer.append("<table  class=\"" + tabStyle +  "\">");
			int ct = 0 ;
			for (RadsError err: page.getErrors()) {
				if (ct ++ % 2 == 0)
					buffer.append("<tr class=\"" + oddRowStyle +  "\"><td class=\"" + tabStyle +  "\">");
				else
					buffer.append("<tr class=\"" + evenRowStyle +  "\"><td class=\"" + tabStyle +  "\">");
					//out.println(" addError ('" + page.getTemplate().getShowErrorsIn() +"','"+page.getTemplate().getErrorStyle() +"','"+ err.getMessage()+"');"   );
				buffer.append(" <span class=\"" + page.getTemplate().getErrorType().getErrorStyle() +  "\" >" +  err.getMessage() +"</span> </td></tr>"  );
				}
			
			buffer.append("</table>");
			//out.println("<script>");
			//out.println(" pushError ('" + showErrors+"','"+ buffer.toString()+"');"   );
			//out.println("</script>");
			out.println( buffer.toString());
			
		}
	}
	protected void showErrors(UIPage page,PrintWriter out) {
		String showErrors = page.getTemplate().getErrorType().getShowErrorsIn();
		if (!Utils.isNullString(showErrors) && !Utils.isNullList(page.getErrors())) {
			out.println("<script>");
				for (RadsError err: page.getErrors()) {
					out.println(" addError ('" + page.getTemplate().getErrorType().getShowErrorsIn() +"','"+page.getTemplate().getErrorType().getErrorStyle() +"','"+ err.getMessage()+"');"   );
				}
			out.println("</script>");
		}
	}
	
	protected void writeGeneralPage(PrintWriter out, UIGeneralPage page, Object value,ViewController controller) throws IOException{
		
		out.println("<HTML>") ;
		out.println("<Title>") ;
		out.println(page.getTitle());
		out.println("</Title>") ;
		out.println("<Head>"); 
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + page.getStyleSheet() + "\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		writeStyleSheet(out, page);
		out.println("</Head>");
		out.println(jsToggle);
		out.println(jsLookupWindow); //FixedParamControl
		//out.println(jsLookupDialog); //FixedParamControl
		out.println(jsapplyFixedActionServer.replaceAll("-FixedControl-", page.getTemplate().getFixedActionfield())
				.replaceAll("-FormName-", page.getForm().getId()).replaceAll("-FixedParamControl-", page.getTemplate().getFixedActionParamfield()));
		out.println(jstoggleMenuVisibility);
		writeJSIncludes(out,page);
		String pageStyle= " class=\"" + page.getTemplate().getPageStyle() + "\"";
		out.println("<Body "  +  pageStyle + "> ") ;
		out.println("") ;
		out.println("<script>") ;
		out.println("var appURL = '" + appURL + ""+ (portalPrefix==null?"":portalPrefix+"/")   +"';");
		out.println("</script>") ;
		//writeFixedElementsofTemplate(out,page,value,controller);
		
		//out.println("<Div " + pageStyle+ " > ");
		//printErrors(page, out);
		//showErrors(page, out);
		
		UIElement pagkeyElem = new UIElement(new UIHidden("currentpage",page.getPageKey())); 
		pagkeyElem.setConstantValue(page.getPageKey());
		page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionfield())));
		page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getSubmitActionfield())));
		page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionParamfield())));
		page.getForm().getElements().add(pagkeyElem);
		if (controller.getMode() != null ) {
			UIElement modekeyElem = new UIElement(new UIHidden("currentmode",controller.getMode().name())); 
			modekeyElem.setConstantValue(controller.getMode().name());
			page.getForm().getElements().add(modekeyElem);
		}
		writeElement(new UIElement(page.getForm()), value,controller);
		
		 //out.println("</Div>");
		 writerAjaxGroup(out,page);
		out.println("</Body>") ;
		out.println("</HTML>");
	}
	
	
	protected void writeTransactionPage(PrintWriter out, UITransactionPage page, Object value,ViewController controller) throws IOException{
		out.println("<HTML>") ;
		out.println("<Title>") ;
		out.println(page.getTitle());
		out.println("</Title>") ;
		out.println("<Head>"); 
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + page.getStyleSheet() + "\">");
		writeStyleSheet(out, page);
		out.println("</Head>");
		out.println(jsToggle);
		out.println(jsCancelWindow);
		out.println(jsCancelDialog);
		out.println(jsLookupWindow); //FixedParamControl
		//out.println(jsLookupDialog); //FixedParamControl
		out.println(jsapplyFixedActionServer.replaceAll("-FixedControl-", page.getTemplate().getFixedActionfield())
				.replaceAll("-FormName-", page.getForm().getId()).replaceAll("-FixedParamControl-", page.getTemplate().getFixedActionParamfield()));
		out.println(jstoggleMenuVisibility);
		
		writeJSIncludes(out,page);
		out.println("<Body bgcolor=\"#f5f5f5\"> ") ;
		out.println("") ;
		
		//writeFixedElementsofTemplate(out,page,value,controller);
		String pageStyle= " style=\"" + page.getTemplate().getPageStyle() + "\"";
		//out.println("<Div " + pageStyle+ " > ");
	//	showErrors(page, out);
		
		UIElement pagkeyElem = new UIElement(new UIHidden("currentpage",page.getPageKey())); 
		pagkeyElem.setConstantValue(page.getPageKey());
			if (!Utils.isNullList(page.getForm().getElements())) {
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getSubmitActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionParamfield())));
			page.getForm().getElements().add(pagkeyElem);
			if (controller.getMode() != null ) {
				UIElement modekeyElem = new UIElement(new UIHidden("currentmode",controller.getMode().name())); 
				modekeyElem.setConstantValue(controller.getMode().name());
				page.getForm().getElements().add(modekeyElem);
			}

		}
		writeElement(new UIElement(page.getForm()), value,controller);
		 //out.println("</Div>");
		 writerAjaxGroup(out,page);
		out.println("</Body>") ;
		out.println("</HTML>");
	}
	
	protected void writeAddEditPage(PrintWriter out, UICRUDPage page, Object value,ViewController controller) throws IOException{
		out.println("<HTML>") ;
		out.println("<Title>") ;
		out.println(page.getTitle());
		out.println("</Title>") ;
		out.println("<Head>"); 
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + page.getStyleSheet() + "\">");
		writeStyleSheet(out, page);
		out.println("</Head>");
		out.println(jsToggle);
		out.println(jsCancelWindow);
		out.println(jsCancelDialog);
		out.println(jsLookupWindow); //FixedParamControl
		//out.println(jsLookupDialog); //FixedParamControl
		out.println(jsapplyFixedActionServer.replaceAll("-FixedControl-", page.getTemplate().getFixedActionfield())
				.replaceAll("-FormName-", page.getForm().getId()).replaceAll("-FixedParamControl-", page.getTemplate().getFixedActionParamfield()));
		out.println(jstoggleMenuVisibility);
		writeJSIncludes(out,page);
		out.println("<Body> ") ;
		out.println("") ;
		
		//writeFixedElementsofTemplate(out,page,value,controller);
		String pageStyle= " style=\"" + page.getTemplate().getPageStyle() + "\"";
		//out.println("<Div " + pageStyle+ " > ");
		
		if (!Utils.isNullList(page.getHeaderElements())) {
			for (UIElement element : page.getHeaderElements()){
				 writeElement(element, value,controller);
			}
		}
		
		if (!Utils.isNullList(page.getPanels())) {
			for (UIControl control : page.getPanels()){
				 writeElement(new UIElement(control), value,controller);
			 }
		}
		UIElement pagkeyElem = new UIElement(new UIHidden("currentpage",page.getPageKey())); 
		pagkeyElem.setConstantValue(page.getPageKey());
			if (!Utils.isNullList(page.getForm().getElements())) {
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getSubmitActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionParamfield())));
			page.getForm().getElements().add(pagkeyElem);
			if (controller.getMode() != null ) {
				UIElement modekeyElem = new UIElement(new UIHidden("currentmode",controller.getMode().name())); 
				modekeyElem.setConstantValue(controller.getMode().name());
				page.getForm().getElements().add(modekeyElem);
			}

		}
			//printErrors(page, out);
		writeElement(new UIElement(page.getForm()), value,controller);
//		showErrors(page, out);
		//printErrors(page, out);
		 for (UIElement element : page.getFooterElements()){
			 writeElement(element, value,controller);
		 }
		 
		 //out.println("</Div>");
		 writerAjaxGroup(out,page);
		out.println("</Body>") ;
		out.println("</HTML>");
	}
	
	/*protected void writeFixedElementsofTemplate (PrintWriter out, UIPage page, Object value,ViewController controller) throws IOException{
	
		TemplateType template = page.getTemplate();
		if (!Utils.isNullList(template.getFixedElements())) {
			for (UIElement element: template.getFixedElements() ) {
				writeElement(element, value , controller) ;
				
			}
		}
		
	}*/
	
/*	protected void writeFilter(PrintWriter out, UIListPage page)
	{
		List<UIElement> filterElements = page.getFilterElements();
		
		
	}*/
	
	protected void writeMetaData(PrintWriter out,UIPage page) {
		if (!Utils.isNullList(page.getTemplate().getMetaDatas())) {
			page.getTemplate().getMetaDatas().forEach(metaData ->
			{
				String charset = Utils.isNullString(metaData.getCharset())?"":"charset=\"" + metaData.getCharset() + "\"";
				String name = Utils.isNullString(metaData.getName())?"":"name=\"" + metaData.getName() + "\"";
				String content = Utils.isNullString(metaData.getContent())?"":"content=\"" + metaData.getContent() + "\"";
				out.println("<meta " + charset + " " + name + " " + content + " />");
			}
			);
		}
	}
	
	protected void writeStyleSheet(PrintWriter out,UIPage page) {
		writeMetaData(out,page);
		page.getStyleSheets().forEach( styleSheet -> { 
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + styleSheet + "\">");	
		});
	}
	
	protected void writeSortControls(PrintWriter out, UIListPage page, Object value,ViewController controller)throws IOException{
		
		UIHidden hdnSortField =  new UIHidden(RadsControlConstants.SORT_FIELD);
		UIHidden hdnSortDirection = new UIHidden(RadsControlConstants.SORT_DIRECTION);
		if (page.getSortCriteria() != null )  {
			hdnSortField.setValue(page.getSortCriteria().getFieldName());
			if (SortCriteria.DIRECTION.DESCENDING.equals(page.getSortCriteria().getDirection()))
				 hdnSortDirection.setValue("DESC");
			else
				 hdnSortDirection.setValue("ASC");
		}
		writeHidden(out,hdnSortField ) ;
		writeHidden(out,hdnSortDirection) ;
	}
	
	protected void writeListPage(PrintWriter out, UIListPage page, Object value,ViewController controller) throws IOException{
		out.println("<HTML>") ;
		out.println("<Title>" + page.getTitle()) ;
		
		out.println("</Title>") ;
		out.println("<Head>"); 
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + page.getStyleSheet() + "\">");
		writeStyleSheet(out, page);
		out.println("</Head>");
		writeJSIncludes(out,page);
		out.println("<Body>") ;
		out.println(jsapplyFixedActionServer.replaceAll("-FixedControl-", page.getTemplate().getFixedActionfield())
				.replaceAll("-FormName-", page.getFormName()).replaceAll("-FixedParamControl-", page.getTemplate().getFixedActionParamfield()));
		out.println(jstoggleMenuVisibility);
		out.println(jsLookupWindow); //FixedParamControl
		//out.println(jsLookupDialog); //FixedParamControl
		//writeFixedElementsofTemplate(out,page,value,controller);
		//writerAjaxGroup(out,page);
		String pageStyle= " class=\"" + page.getTemplate().getPageStyle() + "\"";
		//out.println("<Div " + pageStyle+ " > ");
		
		out.println("<Form name =\"" +  page.getFormName() + "\" , method= \"POST\" >");
		//printErrors(page, out);
		page.getPageNumElement().setValue(page.getPageNumber());
		writeElement(new UIElement(page.getPageNumElement()), page.getPageNumber(),controller);
		writeHidden(out, new UIHidden(page.getTemplate().getFixedActionfield()));
		writeHidden(out, new UIHidden(page.getTemplate().getSubmitActionfield()));
		writeHidden(out, new UIHidden(page.getTemplate().getFixedActionParamfield()));
		writeSortControls(out, page, value, controller);
		writeHidden(out, new UIHidden("currentpage",page.getPageKey()));
		if (controller.getMode() != null)
			writeHidden(out, new UIHidden("currentmode",controller.getMode().name()));
	//	writeFilterSet(out, page.getFilterSet(), value, controller,page);
		for (UIElement innerElem :  page.getInnerElements() ) {
			writeElement(innerElem, value,controller);
			
		}
		
		if ( !Utils.isNullList(page.getButtons())) {
			for (UIButton button: page.getButtons()) {
				writeButton(out, button);
			}
		}
		//showErrors(page, out);
		//displayFilter(page,;)
		out.println("</Form>");
		//out.println("</Div> ");
		writerAjaxGroup(out,page);
		out.println("</Body>") ;
		
		out.println("</HTML>");
	}
	
	protected void writeLookupPage(PrintWriter out, UILookupPage page, Object value,ViewController controller) throws IOException{
		out.println("<HTML>") ;
		out.println("<Title>" + page.getTitle()) ;
		
		out.println("</Title>") ;
		out.println("<Head>"); 
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + page.getStyleSheet() + "\">");
		writeStyleSheet(out, page);
		out.println("</Head>");
		out.println(jsCancelWindow);
		out.println(jsCancelDialog);
		out.println(jsCloseLookupWindow);
	//	out.println(jsCloseLookupDialog);
	//	out.println(jsCloseLookupDialogWithAdditions);
		writeJSIncludes(out,page);
		out.println("<Body>") ;
		out.println(jsapplyFixedActionServer.replaceAll("-FixedControl-", page.getTemplate().getFixedActionfield())
				.replaceAll("-FormName-", page.getForm().getId()).replaceAll("-FixedParamControl-", page.getTemplate().getFixedActionParamfield()));
/*		UIList list = page.getLookupList() ;
		FixedAction actionList = list.getFixedAction();
		if (actionList == FixedAction.CLOSELOOKUPWINDOW ) {
			String dblClick = "closeLookupWindow('" + page.getParentControl() + "','"+ page.getLookupList().getId()  +"');";
			list.setDblClickJS(dblClick);
		}*/
		UIElement pagkeyElem = new UIElement(new UIHidden("currentpage",page.getPageKey())); 
		pagkeyElem.setConstantValue(page.getPageKey());
		if (!Utils.isNullList(page.getForm().getElements())) {
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getSubmitActionfield())));
			page.getForm().getElements().add(new UIElement(new UIHidden(page.getTemplate().getFixedActionParamfield())));
			page.getForm().getElements().add(pagkeyElem);
			if (controller != null && controller.getMode() != null ) {
				UIElement modekeyElem = new UIElement(new UIHidden("currentmode",controller.getMode().name())); 
				modekeyElem.setConstantValue(controller.getMode().name());
				page.getForm().getElements().add(modekeyElem);
			}

		}
		
		writeElement(new UIElement(page.getForm()), value,controller);
		/*if (!Utils.isNullList(page.getFooterButtons())) {
			for (UIButton button : page.getFooterButtons() ) {
				FixedAction action = button.getFixedAction() ;
				if (action == FixedAction.CLOSELOOKUPWINDOW ) {
					button.setOnClickJS("closeLookupWindow('" + page.getParentControl() + "','"+ page.getLookupList().getId()  +"');");
				} else if (action == FixedAction.CANCELWINDOW ) {
					button.setOnClickJS("cancelWindow();");
				}else if (action == FixedAction.ACTION_PLAINSUBMIT ) {
					button.setOnClickJS("submit();");
				}
				button.setFixedAction(null);
				writeButton(out, button);
			}
		}*/
		out.println("</Body>") ;
		out.println("</HTML>");
	}

	
	
	
	private String convertMapToJSONArray(String varName , Map javaMap) {
		StringBuffer jsArrayStr = new StringBuffer( " var " + varName  + " = { ") ;
		int i = 0; 
		for (Object ob : javaMap.keySet()  ) {
		//	jsArrayStr  =  jsArrayStr.append("'" + javaArray[i].toString() + "'") ;
			Object val =javaMap.get(ob);
			jsArrayStr  =  jsArrayStr.append("'" + ob + "':'" + val + "'");
			i++;
			if (i < javaMap.size()  ) {
				jsArrayStr  = jsArrayStr.append(",");
			}
		}
		jsArrayStr  = jsArrayStr.append("};");
		return jsArrayStr.toString(); 
		
	}
	
	private String convertToJSArray(String varName , Object [] javaArray) {
		StringBuffer jsArrayStr = new StringBuffer( " var " + varName  + " = [ ") ;
		for (int i = 0 ; i < javaArray.length ; i ++ ) {
			jsArrayStr  =  jsArrayStr.append("'" + javaArray[i].toString() + "'") ;
			if (i < javaArray.length - 1 ) {
				jsArrayStr  = jsArrayStr.append(",");
			}
		}
		jsArrayStr  = jsArrayStr.append("];");
		return jsArrayStr.toString(); 
		
	}

	protected void writerAjaxGroup (PrintWriter out,UIPage page) {
		if (Utils.isNullList(page.getAjaxGroups())) return ;
		out.println("<script>") ;
		out.println("var appURL = '" + appURL + ""+ (portalPrefix==null?"":portalPrefix+"/")   +"';");
		out.println(" function loadAjaxServices()  {\n");
		for (AjaxGroup grp : page.getAjaxGroups()){
			String service   = grp.getAjaxService() ;
			String jsKeySet=  convertMapToJSONArray(service +"keys" ,  grp.getRequestElements());
			String jsRespSet=  convertMapToJSONArray(service +"results" , grp.getResponseElements());
			out.println(jsKeySet);
			out.println(jsRespSet);
			for (String str :  grp.getRequestElements().keySet())  {
				//out.println("document.getElementById('"+ str +"').addEventListener(\"onblur\", function(){ alert(\"Hello World!\"); });");
				String ctrl = grp.getRequestElements().get(str);
				out.println("registerEvent( '" + ctrl + "','" + service + "',"  + service +"keys" + "," + service +"results" +" );"); 
			}

		}
		out.println("}\n");
		out.println("loadAjaxServices();\n");
		out.println("</script>") ;
		
	}
	
	protected void writeJSIncludes(PrintWriter out,UIPage page) {
		if (page.getTemplate() != null && !Utils.isNullList(page.getTemplate().getIncludedJSs())) {
			for (String fileName : page.getTemplate().getIncludedJSs()) {
				if (!Utils.isNullString(fileName))
					out.println("<script src=\"" + fileName + "\"></script>");
			}
		}
		
		if (!Utils.isNullList(page.getIncludedJSs())) {
			for (String fileName : page.getIncludedJSs()) {
				if (!Utils.isNullString(fileName))
					out.println("<script src=\"" + fileName + "\"></script>");
			}
		}
	}
	
}

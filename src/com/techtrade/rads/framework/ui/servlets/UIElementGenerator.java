package com.techtrade.rads.framework.ui.servlets;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.GeneralController;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.PageForward;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UIPanel;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.config.AppConfig;
import com.techtrade.rads.framework.ui.config.PanelConfig;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UIBooleanCheckBox;
import com.techtrade.rads.framework.ui.controls.UIBreak;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UIDialog;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIElementCollection;
import com.techtrade.rads.framework.ui.controls.UIEmail;
import com.techtrade.rads.framework.ui.controls.UIErrorList;
import com.techtrade.rads.framework.ui.controls.UIErrorObject;
import com.techtrade.rads.framework.ui.controls.UIFileUpload;
import com.techtrade.rads.framework.ui.controls.UIHeader;
import com.techtrade.rads.framework.ui.controls.UIHidden;
import com.techtrade.rads.framework.ui.controls.UIHyperLink;
import com.techtrade.rads.framework.ui.controls.UIIFrame;
import com.techtrade.rads.framework.ui.controls.UIImage;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UIList;
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
import com.techtrade.rads.framework.ui.controls.graphs.UILineChart;
import com.techtrade.rads.framework.ui.controls.graphs.UIPieChart;
import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLElement;

public class UIElementGenerator {
	
	protected static String TAG_TYPE = "type";
	protected static String TAG_TITLE = "title";
	protected static String TAG_CONTROL = "control";
	protected static String TAG_ID = "Id";
	protected static String TAG_LABEL = "label";
	protected static String TAG_PROPERTY = "property";
	protected static String TAG_OPTIONS = "options";
	protected static String TAG_OPTION = "option";
	protected static String TAG_HIDDENCTRLID = "hiddenControlId";
	protected static String TAG_ROWS = "rows";
	protected static String TAG_COLS = "cols";
	protected static String TAG_FORMAT = "format";
	protected static String TAG_POPULATOR = "populator";
	protected static String TAG_KEY = "key";
	protected static String TAG_VALUE = "value";
	protected static String TAG_WIDTH = "width";
	protected static String TAG_ALIGN = "align";
	protected static String TAG_HEIGHT = "height";
	protected static String TAG_PLACEHOLDER = "placeholder";
	protected static String TAG_LEVEL = "level";
	protected static String TAG_READONLY = "readonly";
	protected static String TAG_SRC = "src";
	protected static String TAG_SIZE = "size" ;
	protected static String TAG_ISNUMERIC = "isNumeric" ;
	protected static String TAG_MULTIPLE = "multiple" ;
	protected static String TAG_MAXSIZE = "maxSize";
	protected static String TAG_ACCEPT = "accept";
	protected static String TAG_SAVEFILENAME = "fileNameProperty";
	protected static String TAG_SUPPLPROPERTY = "supplementaryProperty";
	protected static String TAG_LOOKUPTYPE = "lookupType";
	protected static String TAG_ADDITIONAL_INPUT_CTRL= "additionalInputControl";
	protected static String TAG_ONCLICKJS = "onClickJS";
	protected static String TAG_ONCHANGEJS = "onChangeJS";
	protected static String TAG_FOCUSINJS = "focusinJS";
	protected static String TAG_FOCUSOUTJS = "focusoutJS";
	protected static String TAG_CONSTANTVALUE = "constantValue";
	protected static String TAG_GENERATEDVALUE = "generatedValue";
	protected static String TAG_RENDERED = "rendered";
	protected static String TAG_REQUIRED = "required";
	protected static String TAG_AUTOFOCUS = "autofocus";
	protected static String TAG_CAPTION= "caption";
	protected static String TAG_FIXEDACTION= "fixedAction";
	protected static String TAG_FIXEDACTIONPARAM= "fixedActionParam";
	protected static String TAG_FIXEDACTIONSHOW_WARN= "fixedActionshowWarning";
	protected static String TAG_FIXEDACTION_WARN_MSG= "fixedActionwaringMessage";
	protected static String TAG_FIXEDACTION_VAIDATEFUN= "fixedActionvalidateFunc";
	protected static String TAG_XSTART = "xStart";
	protected static String TAG_YSTART = "yStart";
	protected static String TAG_CENTERX = "centerX";
	protected static String TAG_CENTERY = "centerY";
	protected static String TAG_RADIUS = "radius";
	protected static String TAG_RANGEHEIGT = "rangeHeight";
	protected static String TAG_RANGEWIDTH = "rangeWidth";
	protected static String TAG_CANVASHEIGT = "canvasHeight";
	protected static String TAG_CANVASWIDTH = "canvasWidth";
	protected static String TAG_BARWIDTH = "barWidth";
	protected static String TAG_MARGINWIDTH = "marginWidth";
	protected static String TAG_MARGINHEIGHT = "marginHeight";
	protected static String TAG_DATAPROVIDER = "dataProvider";
	protected static String TAG_NOYAXISDIVISIONS  ="noYAxisDivisions";
	protected static String TAG_NOXAXISDIVISIONS  ="noXAxisDivisions";
	protected static String TAG_CHECK = "check";
	protected static String TAG_STYLE = "style" ;
	protected static String TAG_APPLYSTYLEONCHILDREN  ="applyStyleonChildren";
	protected static String TAG_UNSELECTEDTABSTYLE = "unSelectedTabStyle" ;
	protected static String TAG_SELECTEDTABSTYLE = "selectedTabStyle" ;
	protected static String TAG_LINK = "link" ;
	protected static String TAG_TEXT = "text" ;
	protected static String TAG_GROUPID = "groupId" ;
	protected static String TAG_SHOWINTABLE = "showinTable" ;
	protected static String TAG_DATATYPE = "dataType" ;
	protected static String TAG_ISMANDATORY = "isMandatory" ;
	protected static String TAG_SHOWINPREVCOL ="showInPrevCol";
	protected static String TAG_EXTERNALISE ="externalise";

	protected static String TAG_SHOWLOOKUPASDLG = "showLookupAsDialog" ;
	protected static String TAG_DIALOGID = "dialogId" ;
	protected static String TAG_DIALOGSTYLE = "dialogStyle" ;
	protected static String TAG_FRAMESTYLE ="frameStyle";
	
	
	
	protected static String TAG_HEAD = "head" ;
	protected static String TAG_DETAIL = "detail" ;
	protected static String TAG_SUMMARRY = "summary" ;
	
	protected static String TAG_NAME = "";
	
	/*public static UIElement getUIElement(Element doc,ObjectController controller) throws Exception {
		String type = doc.getAttribute(TAG_TYPE) ;
		String label = doc.getAttribute(TAG_LABEL);
		String id = doc.getAttribute(TAG_ID); 
		String property = doc.getAttribute(TAG_PROPERTY);
		if (("UIText").equalsIgnoreCase(type)) {
			return new UIElement(label,new UIText(id),property);
		}else if (("UITextArea").equalsIgnoreCase(type)) {
			String rows =  Utils.getNodeValuefromXML(doc, TAG_ROWS) ;
			String cols =  Utils.getNodeValuefromXML(doc, TAG_COLS) ; 
			UITextArea tx = new UITextArea(id);
			tx.setRows(Integer.parseInt(rows));
			tx.setCols(Integer.parseInt(cols));
			return new UIElement(label,tx,property);
		}else if (("UIDate").equalsIgnoreCase(type)){
			return new UIElement(label,new UIDate(id),property);
		}else if (("UIList").equalsIgnoreCase(type)){
			UIList lst = new UIList(id);
			Map<String, String> options = getMapOptions(doc, controller);
			lst.setOptions(options);
			return new UIElement(label,lst,property);
		}else if (("UIRadioBox").equalsIgnoreCase(type)){
			UIRadioBox box = new UIRadioBox(id) ;
			List <String> options = getListOptions (doc,controller);
			box.setOptions(options);
			return new UIElement(label,box,property);
		}else if (("UILookupText").equalsIgnoreCase(type)){
			String lookupType = Utils.getNodeValuefromXML(doc, TAG_LOOKUPTYPE) ; 
			return new UIElement(label,new UILookupText(id,lookupType),property);
		}
			
		return null;
	}
	
	private static Map<String, String> getMapOptions(Element doc,ObjectController controller) throws Exception {
		NodeList node = doc.getElementsByTagName(TAG_OPTIONS);
		Map<String, String> ans = null ; 
		String populator  =  ((Element)node.item(0)).getAttribute(TAG_POPULATOR);
		if (Utils.isNullString(populator)) {
			NodeList options = ((Element)node.item(0)).getElementsByTagName(TAG_OPTIONS);
			ans = new HashMap<String , String>();
			for (int i = 0 ; i < options.getLength() ; i ++) {
				Element option = (Element)((Element)options.item(i)).getAttributeNode(TAG_OPTION);
				String key = option.getAttribute(TAG_KEY);
				String value = option.getAttribute(TAG_VALUE);
				ans.put(key, value);
			}
		}else {
			   Method methodRead =  controller.getClass().getMethod(populator);
			   if (methodRead.getReturnType().equals(Map.class)) {
				  ans = (Map) methodRead.invoke(controller, null);
			   }
		}
		return ans;
	}
	
	private static List< String> getListOptions(Element doc,ObjectController controller) throws Exception {
		NodeList node = doc.getElementsByTagName(TAG_OPTIONS);
		List ans = null ; 
		String populator  = ((Element)node.item(0)).getAttribute(TAG_POPULATOR);
		if (Utils.isNullString(populator)) {
			NodeList options = ((Element)node.item(0)).getElementsByTagName(TAG_OPTIONS);
			ans = new ArrayList<String >();
			for (int i = 0 ; i < options.getLength() ; i ++) {
				Element option = (Element)((Element)options.item(i)).getAttributeNode(TAG_OPTION);
				String value = option.getAttribute(TAG_VALUE);
				ans.add(value);
			}
		}else {
			   Method methodRead =  controller.getClass().getMethod(populator);
			   if (methodRead.getReturnType().equals(List.class)) {
				  ans = (List) methodRead.invoke(controller, null);
			   }
		}
		return ans;
	} */
	
	

	public static UITableCol getUIColumn(XMLElement doc, ModelObject object, int index) throws Exception {
		
		String title = doc.getAttributeValue(TAG_TITLE) ;
		String value = doc.getAttributeValue(TAG_VALUE)  ;
		String property = doc.getAttributeValue(TAG_PROPERTY)  ;
		String propertValue = null ;
		String width = doc.getAttributeValue(TAG_WIDTH);
		String control = doc.getAttributeValue(TAG_CONTROL);
		String id = doc.getAttributeValue(TAG_ID);
		if (!Utils.isNullString(property)) {
			   Method methodRead =  object.getClass().getMethod("get" + property);
			   Object ret = methodRead.invoke(object, new Object [] {});
			   propertValue = ret.toString() ;
		} else if (!Utils.isNullString(value)) {
			propertValue  =value ; 
		}
		
		if ("UINote".equals(control)) {
			UINote note = new UINote(propertValue) ;
			note.setValue(propertValue);
			UITableCol col = new UITableCol(new UIElement( note) , width);
			return col ;
		} else if ("UICheckBox".equals(control))  {
			UICheckBox box = new UICheckBox(id +index,"" );
			box.addOption(propertValue, propertValue);
			UITableCol col = new UITableCol(new UIElement(box) , width + "%");
			return col ;
		}else if ("UIBooleanCheckBox".equals(control))  {
			UIBooleanCheckBox box = new UIBooleanCheckBox(id +index);
			String hdnControlName = doc.getAttributeValue(TAG_HIDDENCTRLID);
			box.setHiddenControlId(hdnControlName);
			UITableCol col = new UITableCol(new UIElement(box) , width + "%");
			return col ;
		}
		return null;
		
	}
	
	private static ModelObject getObject(ViewController controller) {
		if (controller instanceof GeneralController) {
			return ((GeneralController)controller).getObject();
		}else if (controller instanceof CRUDController) {
			return ((CRUDController)controller).getObject();
		}if (controller instanceof TransactionController) {
			return ((TransactionController)controller).getObject();
		}else
			return null ;
	}
	
	public static UIElement getUIElement(XMLElement doc,ViewController controller,UIPage page,boolean propogateStyletoChildren, String parentStyle) throws Exception {
		String type = doc.getAttributeValue(TAG_TYPE) ;
		String label = doc.getAttributeValue(TAG_LABEL);
		String id = doc.getAttributeValue(TAG_ID);
		String style = doc.getAttributeValue(TAG_STYLE);
		String tagApplyStyleonChildren = doc.getAttributeValue(TAG_APPLYSTYLEONCHILDREN);
		String property = doc.getAttributeValue(TAG_PROPERTY);
		String constValue = doc.getAttributeValue(TAG_CONSTANTVALUE);
		String generatedValue = doc.getAttributeValue(TAG_GENERATEDVALUE);
		String rendered = doc.getAttributeValue(TAG_RENDERED);
		String value = doc.getAttributeValue(TAG_VALUE);
		String mandatory = doc.getAttributeValue(TAG_ISMANDATORY);
		String showInPrevCol = doc.getAttributeValue(TAG_SHOWINPREVCOL);
		String externalise = doc.getAttributeValue(TAG_EXTERNALISE);
		String onClickJS = doc.getAttributeValue(TAG_ONCLICKJS);
		String onChangeJS = doc.getAttributeValue(TAG_ONCHANGEJS);
		boolean  styleonChildren = false ;
		
		if (("true".equalsIgnoreCase(tagApplyStyleonChildren)) || (Utils.isNullString(tagApplyStyleonChildren)  &&  propogateStyletoChildren == true) ){
			styleonChildren= true  ;
		}
		if (propogateStyletoChildren  == true && Utils.isNullString(style) ) {
			style = parentStyle;
		}
		UIElement elem  =null ;
		if (("UIHidden").equalsIgnoreCase(type)) {
			UIHidden hidden  = new UIHidden(id);
			hidden.setDataProperty(property);
			elem = new UIElement("",hidden,property);
		}if (("UIText").equalsIgnoreCase(type)) {
			UIText txt = new UIText(id);
			txt.setStyle(style);
			txt.setDataProperty(property);
			elem = new UIElement(label,txt ,property);
			String size = doc.getAttributeValue(TAG_SIZE);
			String placeHolder = doc.getAttributeValue(TAG_PLACEHOLDER);
			txt.setPlaceHolder(placeHolder);
			String readOnly = doc.getAttributeValue(TAG_READONLY);
			String required = doc.getAttributeValue(TAG_REQUIRED);
			String autoFocus =  doc.getAttributeValue(TAG_AUTOFOCUS);
			
			if (!Utils.isNullString(required) && ("true".equalsIgnoreCase(required) ||  "false".equalsIgnoreCase(required))) {
				txt.setRequired(Boolean.parseBoolean(required));
			}
			if (!Utils.isNullString(autoFocus) && ("true".equalsIgnoreCase(autoFocus) ||  "false".equalsIgnoreCase(autoFocus))) {
				txt.setAutofocus(Boolean.parseBoolean(autoFocus));
			}
			if(!Utils.isNullString(readOnly) && ("true".equalsIgnoreCase(readOnly) ||  "false".equalsIgnoreCase(readOnly))) {
				txt.setReadOnly(Boolean.parseBoolean(readOnly));
			}
			if (Utils.isPositiveInt(size)) {
				txt.setSize(Integer.parseInt(size));
			}
			String isNumeric = doc.getAttributeValue(TAG_ISNUMERIC);
			if("true".equalsIgnoreCase(isNumeric)) {
				txt.setOnlyNumbers(true);
			}
			String focusin = doc.getAttributeValue(TAG_FOCUSINJS);
			txt.setOnfocusin(focusin);
			String focusout = doc.getAttributeValue(TAG_FOCUSOUTJS);
			txt.setOnfocusout(focusout);
			
			if (Utils.isNullString(property))
				elem.setValue(value);
		}else if (("UIHyperLink").equalsIgnoreCase(type)) {
			UIHyperLink link = new UIHyperLink (id);
			String hyperLink =doc.getAttributeValue(TAG_LINK);
			String innerText= doc.getAttributeValue(TAG_TEXT);
			String supplProperty = doc.getAttributeValue(TAG_SUPPLPROPERTY);
			link.setHyperLink(hyperLink);
			link.setInnerText(innerText);
			elem = new UIElement(label,link,property);
			if (!Utils.isNullString(supplProperty))
				elem.setSupplementaryProperty(supplProperty);
		}else if (("UIHeader").equalsIgnoreCase(type)) {
			UIHeader header = new UIHeader();
			header.setStyle(style);
			header.setDataProperty(property);
			String level = doc.getAttributeValue(TAG_LEVEL);
			elem = new UIElement(label,header ,property);
			if (Utils.isPositiveInt(level)) {
				header.setLevel(Integer.parseInt(level));
			}
			if (Utils.isNullString(property))
				elem.setValue(value);
		}else if (("UIEmail").equalsIgnoreCase(type)) {
			UIEmail txt = new UIEmail(id);
			String placeHolder = doc.getAttributeValue(TAG_PLACEHOLDER);
			txt.setStyle(style);
			txt.setDataProperty(property);
			txt.setPlaceHolder(placeHolder);
			elem = new UIElement(label,txt ,property);
			String size = doc.getAttributeValue(TAG_SIZE);
			if (Utils.isPositiveInt(size)) {
				txt.setSize(Integer.parseInt(size));
			}
			if (Utils.isNullString(property))
				elem.setValue(value);
			
			String required = doc.getAttributeValue(TAG_REQUIRED);
			String autoFocus =  doc.getAttributeValue(TAG_AUTOFOCUS);
			
			if (!Utils.isNullString(required) && ("true".equalsIgnoreCase(required) ||  "false".equalsIgnoreCase(required))) {
				txt.setRequired(Boolean.parseBoolean(required));
			}
			if (!Utils.isNullString(autoFocus) && ("true".equalsIgnoreCase(autoFocus) ||  "false".equalsIgnoreCase(autoFocus))) {
				txt.setAutofocus(Boolean.parseBoolean(autoFocus));
			}
		}else if (("UIPassword").equalsIgnoreCase(type)) {
			UIPassword txt = new UIPassword(id);
			String placeHolder = doc.getAttributeValue(TAG_PLACEHOLDER);
			txt.setStyle(style);
			txt.setDataProperty(property);
			txt.setPlaceHolder(placeHolder);
			elem = new UIElement(label,txt ,property);
			String size = doc.getAttributeValue(TAG_SIZE);
			if (Utils.isPositiveInt(size)) {
				txt.setSize(Integer.parseInt(size));
			}
			if (Utils.isNullString(property))
				elem.setValue(value);
			String required = doc.getAttributeValue(TAG_REQUIRED);
			String autoFocus =  doc.getAttributeValue(TAG_AUTOFOCUS);
			
			if (!Utils.isNullString(required) && ("true".equalsIgnoreCase(required) ||  "false".equalsIgnoreCase(required))) {
				txt.setRequired(Boolean.parseBoolean(required));
			}
			if (!Utils.isNullString(autoFocus) && ("true".equalsIgnoreCase(autoFocus) ||  "false".equalsIgnoreCase(autoFocus))) {
				txt.setAutofocus(Boolean.parseBoolean(autoFocus));
			}
		}else if (("UIFileUpload").equalsIgnoreCase(type)) {
			UIFileUpload flUpd = new UIFileUpload(id);
			flUpd.setDataProperty(property);
			String multiple = doc.getAttributeValue(TAG_MULTIPLE);
			String maxSize =  doc.getAttributeValue(TAG_MAXSIZE);
			String accept = doc.getAttributeValue(TAG_ACCEPT);
			flUpd.setAccept(accept);
			String fileNameproperty = doc.getAttributeValue(TAG_SAVEFILENAME);
			
			if("true".equalsIgnoreCase(multiple)) {
				flUpd.setMultiple(true);
			}
			if (!Utils.isNull(onChangeJS))
				flUpd.setOnChangeJS(onChangeJS);
				
			if( Utils.isPositiveInt(maxSize)) {
				flUpd.setMaxSize(Integer.parseInt(maxSize));
			}else {
				flUpd.setMaxSize(4098);
			}
				
			elem = new UIElement(label,flUpd ,property);
			elem.setFileNameProperty(fileNameproperty);
		}else if (("UILabel").equalsIgnoreCase(type)) {
			UILabel labelCtrl = new UILabel(id) ;
			labelCtrl.setStyle(style);
			elem = new UIElement(label,labelCtrl,property);
			String size = doc.getAttributeValue(TAG_SIZE);
			if (Utils.isPositiveInt(size)) {
				labelCtrl.setSize(Integer.parseInt(size));
			}
			if (Utils.isNullString(property))
				labelCtrl.setLabel(value);
		}else if (("UIMenu").equalsIgnoreCase(type)) {
			UIMenu menu = new UIMenu(id);
			elem = new UIElement(label,menu);
			String title = doc.getAttributeValue(TAG_TITLE);
			
			if (Utils.isNullList(doc.getAllChildElements()) ) {
				String link =  doc.getAttributeValue(TAG_LINK);
				menu.setMenuLink(link);
			}else {
				String groupId=  doc.getAttributeValue(TAG_GROUPID) ;
				menu.setGroupId(groupId);
				for (XMLElement menuEle: doc.getAllChildElements()) {
					 UIElement  childMenuElement  = getUIElement(menuEle, controller, page,styleonChildren,style);
					 if (Utils.isNullString(childMenuElement.getControl().getStyle()) && "true".equalsIgnoreCase(tagApplyStyleonChildren)) {
						 childMenuElement.getControl().setStyle(style);
					 }
					 menu.addChileMenu(((UIMenu)childMenuElement.getControl()))  ;
				}
			}
			menu.setMenuText(title);
			menu.setStyle(style);
			
		}else if (("UIBreak").equalsIgnoreCase(type)) {
			UIBreak breakCtrl = new UIBreak();
			elem = new UIElement(label,breakCtrl);
		}else if (("UIStaticText").equalsIgnoreCase(type)) {
			UIStaticText staticText = new UIStaticText();
			elem = new UIElement(label,staticText); 
			String content = doc.getValue();
			elem.setConstantValue(content);
		}else if (("UIParagraph").equalsIgnoreCase(type)) {
			UIParagraph paragraph = new UIParagraph();
			elem = new UIElement(label,paragraph);
			elem.setStyle(style);
			for (XMLElement childElem : doc.getChildElements()) {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if(element != null)
					paragraph.addElement(element);
			}
		}else if (("UINote").equalsIgnoreCase(type)) {
			UINote note = new UINote(id);
			note.setStyle(style);
			elem = new UIElement(label,note,property);
			if (Utils.isNullString(property))
				note.setNote(value);
		}else if ("if".equalsIgnoreCase(doc.getTag()) ) {
			UICondition condition = new UICondition() ;
			String check = doc.getAttributeValue(TAG_CHECK);
			condition.setRendered(check);
			if(condition.shouldDisplay(getObject(controller), controller)) { 
				List <XMLElement> childElements = doc.getAllChildElements();
				for (XMLElement xmlElement : childElements) {
			 		UIElement element = getUIElement(xmlElement, controller,page,styleonChildren,style);
			 		if (element != null )  {
			 			condition.addTrueElement(element);
			 		}
				}
				elem = condition;
			}else {
				return null ;
			}
		}else if (("UITextArea").equalsIgnoreCase(type)) {
			String rows =  Utils.getNodeValuefromXML(doc, TAG_ROWS) ;
			String cols =  Utils.getNodeValuefromXML(doc, TAG_COLS) ; 
			UITextArea tx = new UITextArea(id);
			tx.setDataProperty(property);
			tx.setStyle(style);
			tx.setRows(Integer.parseInt(rows));
			tx.setCols(Integer.parseInt(cols));
			elem = new UIElement(label,tx,property);
			if (Utils.isNullString(property))
				elem.setValue(value);
		}else if (("UIDate").equalsIgnoreCase(type)){
			UIDate dt = new UIDate(id);
			elem =  new UIElement(label,dt,property);
			String format   =  doc.getAttributeValue(TAG_FORMAT);
			if (!Utils.isNull(format))
				  dt.setFormat(format);
			elem.getControl().setDataProperty(property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
		}else if (("UIImage").equalsIgnoreCase(type)){
			UIImage img = new UIImage(id);
			elem =  new UIElement(label,img,property);
			String width = doc.getAttributeValue(TAG_WIDTH);
			String height = doc.getAttributeValue(TAG_HEIGHT);
			String src = doc.getAttributeValue(TAG_SRC);
			img.setHeight(height);
			img.setWidth(width);
			img.setSrc(src);
		}else if (("UITable").equalsIgnoreCase(type)){
			UITable table = new UITable();
			elem  = new UIElement(table);
			elem.getControl().setStyle(style);
			elem.setModelProperty(property);
			table.setDataProperty(property);
			String rows =  doc.getAttributeValue(TAG_ROWS);
			String cols = doc.getAttributeValue(TAG_COLS);
			table.setNoRows(Integer.parseInt(rows));
			table.setNoColumns(Integer.parseInt(cols));
			for  (XMLElement childElem : doc.getAllChildElements())  {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if (element != null && element.getControl() instanceof UITableHead) {
					table.addHeader((UITableHead)element.getControl());
				}
				if (element != null && element.getControl() instanceof UITableRow) {
					table.addRow((UITableRow)element.getControl());
				}
			}
			
		}else if (("UITableHead").equalsIgnoreCase(type)){
			UITableHead tabHead = new UITableHead();
			String width = doc.getAttributeValue(TAG_WIDTH);
			//tabHead
			elem  = new UIElement(tabHead);
			for  (XMLElement childElem : doc.getAllChildElements())  {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if (element !=null && element.getControl() instanceof UITableRow) {
					tabHead.setRow((UITableRow)(element.getControl()));
				}
			}
			elem.getControl().setStyle(style);
		}else if (("UITableRow").equalsIgnoreCase(type)){
			UITableRow tableRow = new UITableRow();
			elem  = new UIElement(tableRow);
			String dataType  = doc.getAttributeValue(TAG_DATATYPE);
			tableRow.setDataType(dataType);
			tableRow.setRendered(rendered);
			for  (XMLElement childElem : doc.getAllChildElements())  {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if (element !=null && element.getControl() instanceof UITableCol) {
					tableRow.addCol((UITableCol)element.getControl());
				}
			}
			elem.getControl().setStyle(style);
		}else if (("UITableCol").equalsIgnoreCase(type)){
			UITableCol tableCol  = new UITableCol();
			String width = doc.getAttributeValue(TAG_WIDTH);
			String align  = doc.getAttributeValue(TAG_ALIGN);
			elem = new UIElement(tableCol);
			tableCol.setAlign(align);
			tableCol.setWidth(width);
			if(!Utils.isNullList(doc.getAllChildElements()))  {
				for  (XMLElement childElem : doc.getAllChildElements())  {
					UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
					if(element != null )
						tableCol.addElement(element);
				}
			}else { 
				String xmlValue = doc.getValue() ;
				UIElement element  = new UIElement(new UILabel(xmlValue)) ;
				tableCol.addElement(element);
			}
			elem.getControl().setStyle(style);
		}else if (("UIDiv").equalsIgnoreCase(type)){
			UIDiv div = new UIDiv();
			String width = doc.getAttributeValue(TAG_WIDTH);
			String align  = doc.getAttributeValue(TAG_ALIGN);
			elem = new UIElement(div);
			div.setStyle(style);
			div.setAlign(align);
			div.setWidth(width);

			for (XMLElement childElem : doc.getChildElements()) {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if(element != null)
					div.addElement(element);
			}
			
		}else if (("UIDialog").equalsIgnoreCase(type)){
			UIDialog dialog = new UIDialog(id);
			elem = new UIElement(dialog);
			dialog.setStyle(style);
			for (XMLElement childElem : doc.getChildElements()) {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if(element != null)
					dialog.addElement(element);
			}
		}else if (("UIIFrame").equalsIgnoreCase(type)){
			UIIFrame frame = new UIIFrame(id);
			String src = doc.getAttributeValue(TAG_SRC);
			frame.setSrc(src);
			elem = new UIElement(frame);
			frame.setStyle(style);
			if (!Utils.isNullList( doc.getChildElements())) {
				for (XMLElement childElem : doc.getChildElements()) {
					UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
					if(element != null)
						frame.addElement(element);
				}
			}
		}else if ("UIErrorObject".equalsIgnoreCase(type)) {
			UIErrorObject object = new  UIErrorObject();
			elem = new UIElement(object);
			object.setStyle(style);
		}else if ("UIErrorList".equalsIgnoreCase(type)) {
			UIErrorList list = new  UIErrorList();
			elem = new UIElement(list);
			list.setStyle(style);
		}else if (("UITab").equalsIgnoreCase(type)){
			String title= doc.getAttributeValue(TAG_TITLE);
			UITab tab = new UITab(id,title);
			String showinTabString = doc.getAttributeValue(TAG_SHOWINTABLE);
			boolean showInTab = Utils.getBooleanValue(showinTabString);
			List <UIElement> allElements = new ArrayList<UIElement>(); 
			String cols = doc.getAttributeValue(TAG_COLS);
			tab.setStyle(style);
			elem =  new UIElement(label,tab,property);
			for  (XMLElement childElem : doc.getAllChildElements())  {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if (element != null ) {
					if (!showInTab)
						tab.addElement(element);
					else 
						allElements.add(element);
				}
			}
			if (showInTab) {
				UITable tabOrganized = UITable.tabularizeElements("", allElements,  Integer.parseInt(cols));
				tab.addElement(new UIElement(tabOrganized));
			}
			
		}else if ("UIPieChart".equalsIgnoreCase(type)) {
			UIPieChart chart = new  UIPieChart();
			String centerX =doc.getChildAttributeValue(TAG_CENTERX) ;
			String centerY = doc.getChildAttributeValue(TAG_CENTERY);
			String radius = doc.getChildAttributeValue(TAG_RADIUS);
			String rangeWidth = doc.getChildAttributeValue(TAG_RANGEWIDTH);
			String rangeHeight = doc.getChildAttributeValue(TAG_RANGEHEIGT);
			String dataProvider = doc.getAttributeValue(TAG_DATAPROVIDER);
			if (Utils.isPositiveInt(rangeWidth)) {
				chart.setWidth(Integer.parseInt(rangeWidth));
			}
			if (Utils.isPositiveInt(rangeHeight)) {
				chart.setHeight(Integer.parseInt(rangeHeight));
			}
			if (Utils.isPositiveInt(centerX)) {
				chart.setCenterX(Integer.parseInt(centerX));
			}
			if (Utils.isPositiveInt(centerY)) {
				chart.setCenterY(Integer.parseInt(centerY));
			}
			if (Utils.isPositiveInt(radius)) {
				chart.setRadius(Integer.parseInt(radius));
			}
			chart.setDataProvider(dataProvider);
			elem = new UIElement("",chart);
		}else if ("UIBarChart".equalsIgnoreCase(type)) { 
			UIBarChart chart = new  UIBarChart();
			String xStart =doc.getChildAttributeValue(TAG_XSTART) ;
			String yStart = doc.getChildAttributeValue(TAG_YSTART);
			String rangeWidth = doc.getChildAttributeValue(TAG_RANGEWIDTH);
			String rangeHeight = doc.getChildAttributeValue(TAG_RANGEHEIGT);
			String marginWidht = doc.getChildAttributeValue(TAG_MARGINWIDTH);
			String barWidth = doc.getChildAttributeValue(TAG_BARWIDTH);
			String dataProvider = doc.getAttributeValue(TAG_DATAPROVIDER);
			String noYAxisDivisions = doc.getChildAttributeValue(TAG_NOYAXISDIVISIONS) ;
			if (Utils.isPositiveInt(xStart)) {
				chart.setStartX(Integer.parseInt(xStart));
			}
			if (Utils.isPositiveInt(yStart)) {
				chart.setStartY(Integer.parseInt(yStart));
			}
			if (Utils.isPositiveInt(rangeWidth)) {
				chart.setWidth(Integer.parseInt(rangeWidth));
			}
			if (Utils.isPositiveInt(rangeHeight)) {
				chart.setHeight(Integer.parseInt(rangeHeight));
			}
			if (Utils.isPositiveInt(marginWidht)) {
				chart.setMarginWidth(Integer.parseInt(marginWidht));
			}
			if (Utils.isPositiveInt(barWidth)) {
				chart.setBarWidth(Integer.parseInt(barWidth));
			}
			if (Utils.isPositiveInt(noYAxisDivisions)) {
				chart.setNoYAxisDivisions(Integer.parseInt(noYAxisDivisions));
			}
			chart.setDataProvider(dataProvider);
			elem = new UIElement("",chart);
			
		}else if ("UILineChart".equalsIgnoreCase(type)) { 
			UILineChart chart = new  UILineChart();
			String xStart =doc.getChildAttributeValue(TAG_XSTART) ;
			String yStart = doc.getChildAttributeValue(TAG_YSTART);
			String rangeWidth = doc.getChildAttributeValue(TAG_RANGEWIDTH);
			String rangeHeight = doc.getChildAttributeValue(TAG_RANGEHEIGT);
			String canvasHeight  =  doc.getChildAttributeValue(TAG_CANVASHEIGT);
			String canvasWidth  =  doc.getChildAttributeValue(TAG_CANVASWIDTH);
			String marginWidht = doc.getChildAttributeValue(TAG_MARGINWIDTH);
			String marginHeight = doc.getChildAttributeValue(TAG_MARGINHEIGHT);
			String dataProvider = doc.getAttributeValue(TAG_DATAPROVIDER);
			String noYAxisDivisions = doc.getChildAttributeValue(TAG_NOYAXISDIVISIONS) ;
			String noXAxisDivisions = doc.getChildAttributeValue(TAG_NOXAXISDIVISIONS);
			if (Utils.isPositiveInt(xStart)) {
				chart.setStartX(Integer.parseInt(xStart));
			}
			if (Utils.isPositiveInt(yStart)) {
				chart.setStartY(Integer.parseInt(yStart));
			}
			if (Utils.isPositiveInt(rangeWidth)) {
				chart.setWidth(Integer.parseInt(rangeWidth));
			}
			if (Utils.isPositiveInt(rangeHeight)) {
				chart.setHeight(Integer.parseInt(rangeHeight));
			}
			if (Utils.isPositiveInt(marginWidht)) {
				chart.setMarginWidth(Integer.parseInt(marginWidht));
			}
			if (Utils.isPositiveInt(marginHeight)) {
				chart.setMarginHeight(Integer.parseInt(marginHeight));
			}
			if (Utils.isPositiveInt(noYAxisDivisions)) {
				chart.setNoYAxisDivisions(Integer.parseInt(noYAxisDivisions));
			}
			if (Utils.isPositiveInt(noXAxisDivisions)) {
				chart.setNoXAxisDivisions(Integer.parseInt(noXAxisDivisions));
			}
			if (Utils.isPositiveInt(canvasHeight)) {
				chart.setCanvasHeight(Integer.parseInt(canvasHeight));
			}
			if (Utils.isPositiveInt(canvasWidth)) {
				chart.setCanvasWidth(Integer.parseInt(canvasWidth));
			}
			chart.setDataProvider(dataProvider);
			elem = new UIElement("",chart);
			
		}else if (("UITabSet").equalsIgnoreCase(type)){
			UITabSet tSet = new UITabSet(id);
			String selectedTabStyle =  doc.getAttributeValue(TAG_SELECTEDTABSTYLE);
			String unSelectedTabStyle = doc.getAttributeValue(TAG_UNSELECTEDTABSTYLE);
			tSet.setStyle(style);
			tSet.setSelectedTabStyle(selectedTabStyle);
			tSet.setUnSelectedTabStyle(unSelectedTabStyle);
			elem =  new UIElement(label, tSet,property);
			for  (XMLElement childElem : doc.getAllChildElements())  {
				UIElement element = getUIElement(childElem, controller, page,styleonChildren,style);
				if(element != null) {
					element.getControl().setStyle(style);
					tSet.addTab((UITab)element.getControl());
				}
			}
		}else if (("UIList").equalsIgnoreCase(type)){
			UIList lst = new UIList(id);
			String size =doc.getAttributeValue(TAG_SIZE);
			if (!Utils.isNull(size))
				 lst.setSize(size);
			lst.setDataProperty(property);
			String multipe = doc.getAttributeValue(TAG_MULTIPLE);
			if (!Utils.isNull(multipe) && "true".equalsIgnoreCase(multipe))
				lst.setMultiSelect(true);
			/*Map<String, String> options = getMapOptions(doc, controller);
			lst.setOptions(options);*/
			elem = new UIElement(label,lst,property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
			if(!Utils.isNullString(onChangeJS))
				lst.setOnChangeJS(onChangeJS);
			XMLElement node = doc.getFirstChildElement(TAG_OPTIONS);
			if (node != null ) {
				String populator  =  node.getAttributeValue(TAG_POPULATOR);
				elem.setPopulator(populator);
			}
		}else if (("UIRadioBox").equalsIgnoreCase(type)){
			UIRadioBox box = new UIRadioBox(id) ;
			box.setDataProperty(property);
			Map <String,String> options = getListOptions (doc,controller);
			for (String s : options.keySet() ) {
				box.addOption(s, options.get(s));
			}
			elem = new UIElement(label,box,property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
		}else if (("UICheckBox").equalsIgnoreCase(type)){
			UICheckBox box = new UICheckBox(id) ;
			box.setDataProperty(property);
			Map <String,String> options = getListOptions (doc,controller);
			for (String s : options.keySet() ) {
				box.addOption(s, options.get(s));			}
			elem = new UIElement(label,box,property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
		}else if ("UIBooleanCheckBox".equals(type))  {
			UIBooleanCheckBox box = new UIBooleanCheckBox(id );
			box.setDataProperty(property);
			String hdnControlName = doc.getAttributeValue(TAG_HIDDENCTRLID);
			box.setHiddenControlId(hdnControlName);
			elem = new UIElement(label,box,property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
		}else if (("UILookupText").equalsIgnoreCase(type)){
			String lookupType = Utils.getNodeValuefromXML(doc, TAG_LOOKUPTYPE) ;
			String height =  Utils.getNodeValuefromXML(doc, TAG_HEIGHT) ;
			String width = Utils.getNodeValuefromXML(doc, TAG_WIDTH) ;
			String size = doc.getAttributeValue(TAG_SIZE) ;
			String additionalInputControl = doc.getAttributeValue(TAG_ADDITIONAL_INPUT_CTRL);
			UILookupText lkpText =new UILookupText(id,lookupType);
			lkpText.setWindowHeight(height);
			lkpText.setWindowWidth(width);
			lkpText.setAdditionalInputControl(additionalInputControl);
			if (Utils.isPositiveInt(size)) {
				lkpText.setSize(Integer.parseInt(size));
			}
			elem = new UIElement(label,lkpText,property);
			elem.getControl().setDataProperty(property);
			if (Utils.isNullString(property))
				elem.setValue(value);
			elem.getControl().setStyle(style);
			
			String showasDlg = doc.getAttributeValue(TAG_SHOWLOOKUPASDLG) ;
			String dlgId = doc.getAttributeValue( TAG_DIALOGID) ;
			String dlgStyle =doc.getAttributeValue( TAG_DIALOGSTYLE) ;
			String frmStyle = doc.getAttributeValue( TAG_FRAMESTYLE) ;
			
			if ("true".equalsIgnoreCase(showasDlg)) { 
				lkpText.setShowLookupAsDialog(true);
				lkpText.setDialogId(dlgId);
				lkpText.setDialogStyle(dlgStyle);
				lkpText.setFrameStyle(frmStyle);
			}
			
			
		}else if ("Panel".equalsIgnoreCase(type )) {
		    String key = doc.getAttributeValue(TAG_KEY);
		    PanelConfig panelConfig  = AppConfig.APPCONFIG.getPanel(".", key) ;
		    UIElementCollection collection = new UIElementCollection();
		    UIDiv  div = new UIDiv() ;
		    for (XMLElement xmlElement :  panelConfig.getXmlElements())  {
		    	UIElement element = getUIElement(xmlElement, controller, page,styleonChildren,style);
		    	if (element != null)
		    		collection.addElement(element);
		    }
		    if (panelConfig.isShowOnDiv()) {
		    	div.setId(id);
		    	div.setStyle(style);
		    	div.setElements(collection.getElements());
		    	elem  = new UIElement(div) ;
		    }else  {
		    	elem  = new UIElement(collection) ;
		    }

		}else if (("UIButton").equalsIgnoreCase(type)){
			//String onClickJS = doc.getAttributeValue(TAG_ONCLICKJS);
			String caption = doc.getAttributeValue(TAG_CAPTION);
			String fixedAction = doc.getAttributeValue(TAG_FIXEDACTION);
			String fixedActionParam = doc.getAttributeValue(TAG_FIXEDACTIONPARAM);
			//String showWarning =doc.getAttributeValue(TAG_FIXEDACTIONSHOW_WARN);
			//String warningMsg = doc.getAttributeValue(TAG_FIXEDACTION_WARN_MSG);
			String validateFunc = doc.getAttributeValue(TAG_FIXEDACTION_VAIDATEFUN);
			UIButton btn =  new UIButton(id,caption,onClickJS) ;
			if (!Utils.isNullString(fixedActionParam))
				btn.setFixedActionParam(fixedActionParam);
			if (!Utils.isNullString(fixedAction)){
				FixedAction action = FixedAction.getFixedAction(fixedAction);
				btn.setFixedAction(action);
				/*if("true".equalsIgnoreCase(showWarning)) 
					action.setShowWaring(true);
				if (!Utils.isNullString(warningMsg))
					action.setWarningMessage(warningMsg);*/
				action.setValidateFunc(validateFunc);
				if(  action == FixedAction.ACTION_PAGEFORWARD ) {
					if (!Utils.isNullString(fixedActionParam) ) {
						PageForward forward =  page.getPageForwardforKey(fixedActionParam);
						if (forward != null){ 
							btn.setOnClickJS("window.location.href ='"+ forward.getLink() +"' ");
							btn.setFixedAction(null);
						}
					}
				}else if (  action == FixedAction.ACTION_ADDTABLEROW )  {
					if (!Utils.isNullString(fixedActionParam)  ) {
						if ("this".equals(fixedActionParam)) {
							if (page instanceof UIListPage) {
								String oddRowStyle = ((UIListPage) page).getOddRowStyle();
								String evenRowStyle =((UIListPage) page).getEvenRowStyle();
								btn.setOnClickJS("addRow(" + fixedActionParam +",'"+ oddRowStyle +"','"+ evenRowStyle  +"')");
							}else { 
								btn.setOnClickJS("addRow(" + fixedActionParam +",'','')");
							}
						}else
							btn.setOnClickJS("addRowofTable(document.getElementById('" + fixedActionParam +"'))");
						btn.setFixedAction(null);
					}
				}else if (  action == FixedAction.ACTION_DELETETABLEROW )  {
					if (!Utils.isNullString(fixedActionParam)  ) {
						btn.setOnClickJS("deleteRow(" + fixedActionParam +")");
						btn.setFixedAction(null);
					}
				}
			}
			elem = new UIElement(btn);
			elem.getControl().setStyle(style);		
		}
		if (!Utils.isNullString(rendered) &&  !elem.shouldDisplay(getObject(controller), controller)){
			return null;
		}
		    
		
		if (!Utils.isNullString(constValue) && elem != null)
			elem.setConstantValue(constValue);
		else if (!Utils.isNullString(generatedValue) && elem != null){
		//	elem.setConstantValue(UIElement.getGeneratedValue(controller, null, generatedValue));
			elem.setGeneratedValue(generatedValue);
		}if (!Utils.isNullString(rendered) && elem != null)
			elem.setRendered(rendered);
		if (!Utils.isNullString(mandatory) && elem != null && "true".equalsIgnoreCase(mandatory))
			elem.setMandatory(true);
		if (!Utils.isNullString(showInPrevCol) && elem != null && "true".equalsIgnoreCase(showInPrevCol))
			elem.setShowInPrevCol(true);
		if(!Utils.isNullString(externalise) && elem != null && ("true".equalsIgnoreCase(externalise) || "false".equalsIgnoreCase(externalise))
				 &&  elem.getControl() != null )
			elem.getControl().setExternalize(Boolean.valueOf(externalise));
		return elem;
		
	}
	

	private static Map<String, String> getMapOptions(XMLElement doc,ViewController controller) throws Exception {
		XMLElement node = doc.getFirstChildElement(TAG_OPTIONS);
		if (node == null) return null;
		Map<String, String> ans = null ; 
		String populator  =  node.getAttributeValue(TAG_POPULATOR);
		if (Utils.isNullString(populator)) {
			List <XMLElement> optionSet = node.getChildElements(TAG_OPTION);
			ans = new HashMap<String , String>();
			for (XMLElement option :  optionSet) {
				String key = option.getAttributeValue(TAG_KEY);
				String value = option.getValue();
				ans.put(key, value);
			}
		}else {
			  ans = UIElement.populateOptions(controller,null,populator);
		}
		return ans;
	}
	
	private static Map< String,String> getListOptions(XMLElement doc,ViewController controller) throws Exception {
		XMLElement node = doc.getFirstChildElement(TAG_OPTIONS);
		 Map< String,String> ans = new HashMap<String,String>(); ; 
		if(node == null) return ans;
		String populator  =  node.getAttributeValue(TAG_POPULATOR);
		if (Utils.isNullString(populator)) {
			List <XMLElement> optionSet = node.getChildElements(TAG_OPTION);
			for (XMLElement option :  optionSet) {
				String key = option.getAttributeValue(TAG_KEY);
				String value = option.getValue();
				ans.put(key, value);
			}
		}else {
			   Method methodRead =  controller.getClass().getMethod(populator);
			   if (methodRead.getReturnType().equals(Map.class)) {
				  ans = (Map) methodRead.invoke(controller, null);
			   }
		}
		return ans;
	}

}

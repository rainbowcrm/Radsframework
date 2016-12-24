package com.techtrade.rads.framework.ui.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.controller.abstracts.DataSheetController;
import com.techtrade.rads.framework.controller.abstracts.GeneralController;
import com.techtrade.rads.framework.controller.abstracts.ListController;
import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.PageForward;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.AjaxGroup;
import com.techtrade.rads.framework.ui.components.PanelDef;
import com.techtrade.rads.framework.ui.components.UICRUDPage;
import com.techtrade.rads.framework.ui.components.UIDataColumn;
import com.techtrade.rads.framework.ui.components.UIDataSheetPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIFilterElement;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UIGeneralPage;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UILookupPage;
import com.techtrade.rads.framework.ui.components.UIPanel;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.components.UITransactionPage;
import com.techtrade.rads.framework.ui.config.AppConfig;
import com.techtrade.rads.framework.ui.config.PageConfig;
import com.techtrade.rads.framework.ui.config.PanelConfig;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIErrorList;
import com.techtrade.rads.framework.ui.controls.UIFilterSet;
import com.techtrade.rads.framework.ui.controls.UIHidden;
import com.techtrade.rads.framework.ui.controls.UIImage;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UINote;
import com.techtrade.rads.framework.ui.controls.UIRadioBox;
import com.techtrade.rads.framework.ui.controls.UIText;
import com.techtrade.rads.framework.ui.templates.CRUDTemplateType;
import com.techtrade.rads.framework.ui.templates.DataSheetTemplateType;
import com.techtrade.rads.framework.ui.templates.ListTemplateType;
import com.techtrade.rads.framework.ui.templates.TemplateType;
import com.techtrade.rads.framework.ui.templates.TemplateReader;
import com.techtrade.rads.framework.ui.templates.TransactionTemplateType;
import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLDocument;
import com.techtrade.rads.framework.utils.XMLElement;

public class PageGenerator {
	
	protected static String TAG_PAGE =   "Page";
	protected static String TAG_TEMPLATE = "Template";
	protected static String TAG_PAGECONTENT = "PageContent";
	protected static String TAG_DATASETUP = "DataSetup";
	protected static String TAG_OBJECT = "Object";
	protected static String TAG_TITLEROW = "TitleRow";
	protected static String TAG_ELEMENTS = "Elements";
	protected static String TAG_ELEMENT = "Element";
	protected static String TAG_CONTROLLER = "Controller";
	protected static String TAG_TEMPLATETYPE = "type";
	protected static String TAG_UIFORM = "UIForm";
	protected static String TAG_HEADER = "Header";
	protected static String TAG_DETAIL = "Detail";
	protected static String TAG_SUMMARY = "Summmary";
	protected static String TAG_METHOD = "method";
	protected static String TAG_STYLE = "style" ;
	protected static String TAG_ENCTYPE = "enctype";
	protected static String TAG_ACTION = "action";
	protected static String TAG_VALUE = "value";
	protected static String TAG_ID = "Id";
	protected static String TAG_TITLE = "title";
	protected static String TAG_COLSPAN = "colspan";
	protected static String TAG_COLUMNS  = "Columns";
	protected static String TAG_COLUMN  = "Column";
	protected static String TAG_SELECTIONCOLUMN  = "SelectionColumn";
	protected static String TAG_ALLOWROWSELECTION  = "allowrowselection";
	protected static String TAG_TYPE = "type";
	protected static String TAG_CONSTANTVALUE = "constantValue";
	protected static String TAG_PROPERTY = "property";
	protected static String TAG_UNIQUEPROPERTY = "uniqueproperty";
	protected static String TAG_SRC = "src";
	protected static String TAG_HEIGHT = "height";
	protected static String TAG_WIDTH = "width";
	protected static String TAG_CONTROL = "control";
	protected static String TAG_FORWARDS = "Forwards" ;
	protected static String TAG_FORWARD = "Forward" ;
	protected static String TAG_KEY = "key" ;
	protected static String TAG_INCLUDEDJS = "IncludeJS";
	protected static String TAG_AJAXGROUPS = "AjaxGroups";
	protected static String TAG_AJAXGROUP = "AjaxGroup";
	protected static String TAG_SERVICE = "service";
	protected static String TAG_REQUESTELEMENTS = "requestElements";
	protected static String TAG_REQUESTELEMENT = "requestElement";
	protected static String TAG_RESPONSEELEMENTS = "responseElements";
	protected static String TAG_RESPONSEELEMENT = "responseElement";
	protected static String TAG_SECTIONTYPE = "type";
	protected static String TAG_SECTION = "Section";
	protected static String TAG_LISTSECTION = "ListSection";
	protected static String TAG_FILTERSECTION = "FilterSection";
	protected static String TAG_ERRORSECTION = "ErrorSection";
	protected static String TAG_CORESECTION = "CoreSection";
	protected static String TAG_FILTERNODE = "FilterNode";
	protected static String TAG_OPERATOR  = "operator";
	protected static String TAG_RENDERED  = "rendered";
	protected static String TAG_SEARCHFIELDID  = "SearchFieldId";
	protected static String TAG_LISTFIELDID  ="listFieldId";
	protected static String TAG_DATAENTRYCOLS  = "DataEntryColumns";

	
	
	
	protected static String TAG_TEMPLATENAME = "name";
	protected static String TEMPLATE_PATH = "/resources/templates/";
	
	protected static String TEMPLATETYPE_LOOKUP = "lookup";
	protected static String TEMPLATETYPE_CRUD = "CRUD";
	protected static String TEMPLATETYPE_LIST = "List";
	protected static String TEMPLATETYPE_DATASHEET = "DataSheet";
	protected static String TEMPLATETYPE_GENERAL = "General";
	protected static String TEMPLATETYPE_TRANSACTION = "Transaction";
	
	  private static final String MULTIPLE = "Multiple" ;
	  private static final String SINGLE =  "Single" ;
	  private static final String NONE =  "None" ;
	
   public static UIPage getPagefromKey (PageConfig config,ModelObject object,HttpServletRequest req,ViewController.Mode mode
			,HttpServletResponse response, ServletContext context)  throws Exception{
		UIPage page = null;
		String pageDef = config.getDefinition() ;
		page = PageGenerator.generatePage(context.getRealPath("/") +
			pageDef , context.getRealPath("/"),object,req,mode,response) ;
		return page ;
	}
	  
	public static ModelObject readObjectfromPageConfig(PageConfig config )  throws Exception{
		String modelClass = config.getModelClass() ;
		if(!Utils.isNullString(modelClass)) {
			return  (ModelObject) Class.forName(modelClass).newInstance() ;
		}
		return null;
	}
	
	public static ViewController.Mode getModeFromString(String modeKey) {
		if (Utils.isNull(modeKey))
			return null;
		else if ("CREATE".equals(modeKey))
			return ViewController.Mode.CREATE ;
		else if ("READ".equals(modeKey))
			return ViewController.Mode.READ ;
		else if ("UPDATE".equals(modeKey))
			return ViewController.Mode.UPDATE ;
		else if ("DELETE".equals(modeKey))
			return ViewController.Mode.DELETE ;
		return null;			
		
	}
	
	public static UIPage generatePage(String xmlFile, String contextPath,ModelObject object,HttpServletRequest req ,
			ViewController.Mode mode,HttpServletResponse response) throws Exception {

		File fXmlFile = new File(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		doc.getDocumentElement().getNodeName();
		Element  pageElement =  doc.getElementById(TAG_PAGE);
		NodeList templateNodes = doc.getElementsByTagName(TAG_TEMPLATE);
		Node firstNode = templateNodes.item(0);
		Element  templateElement =  (Element)firstNode;
	    String templateType = templateElement.getAttribute(TAG_TEMPLATETYPE) ;
		String templateName = templateElement.getAttribute(TAG_TEMPLATENAME) ;
		UIPage page = null ;
		
		if(TEMPLATETYPE_CRUD.equalsIgnoreCase(templateType))  {
			page =  generateCRUDPage( doc ,contextPath +  TEMPLATE_PATH  + templateName  + ".xml",object,req,mode,response);
		}else if(TEMPLATETYPE_LIST.equalsIgnoreCase(templateType) || TEMPLATETYPE_DATASHEET.equalsIgnoreCase(templateType) )  {
			page = generateListPage( doc ,contextPath +  TEMPLATE_PATH  + templateName  + ".xml",req,mode,templateType,response);
		}else if(TEMPLATETYPE_GENERAL.equalsIgnoreCase(templateType))  {
			page = generateGeneralPage( doc ,contextPath +  TEMPLATE_PATH  + templateName  + ".xml",object,req,mode,response);
		}else if(TEMPLATETYPE_TRANSACTION.equalsIgnoreCase(templateType))  {
			page = generateTransactionPage( doc ,contextPath +  TEMPLATE_PATH  + templateName  + ".xml",object,req,mode,response);
		}else if(TEMPLATETYPE_LOOKUP.equalsIgnoreCase(templateType))  {
			page = generateLookupPage( doc ,contextPath +  TEMPLATE_PATH  + templateName  + ".xml",object,req,mode);
		}

		
		return page;
	}
	
	
		
	protected static List<UIElement> getPanel(PanelDef def,ViewController objController,UIPage page) throws Exception {
		    List<UIElement> panelElements = new ArrayList<UIElement> ();  

		    String key = def.getKey() ;
		    PanelConfig panelConfig  = AppConfig.APPCONFIG.getPanel(".", key) ;
		    UIDiv  div = new UIDiv() ;
		    div.setId(def.getId());
		    div.setStyle(def.getStyle());
		    for (XMLElement xmlElement :  panelConfig.getXmlElements())  {
		    	UIElement element = UIElementGenerator.getUIElement(xmlElement, objController, page,false,null);
		    	if (panelConfig.isShowOnDiv())
		    		div.addElement(element);
		    	else
		    		panelElements.add(element);
		    }
		    if (panelConfig.isShowOnDiv()) 
		    	panelElements.add(new UIElement(div));
		    return panelElements;
		  
	}
	
	/*protected static void addFixedElements (TemplateType  listTemplate, List<PanelDef> fixedPanelList ,ViewController listController,UIPage page)throws Exception{
		if (!Utils.isNullList(fixedPanelList)) {
			for (PanelDef def :  fixedPanelList) {
				UIDiv div = getPanel(def,listController,page);
				listTemplate.addFixedElement(new UIElement(div));
			}
		}
	}*/
	
	protected static UIListPage generateListPage (Document  pageElement,String templateName,HttpServletRequest req,
			ViewController.Mode mode,String templateType,HttpServletResponse response){
		UIListPage page = null;
		try {
			ListTemplateType listTemplate = null ;
			if(TEMPLATETYPE_LIST.equalsIgnoreCase(templateType))   {
				listTemplate = (ListTemplateType) TemplateReader.INSTANCE.readTemplate( templateName);
				page = new UIListPage();
			}else if ( TEMPLATETYPE_DATASHEET.equalsIgnoreCase(templateType)) { 
				listTemplate = (DataSheetTemplateType) TemplateReader.INSTANCE.readTemplate( templateName);
				page = new UIDataSheetPage();
			}
			page.setTemplate(listTemplate);
			int noRows = listTemplate.getNoRows();
			String oddRowStyle  = listTemplate.getOddRowStyle();
			String evenRowStyle =  listTemplate.getEvenRowStyle() ;
			String titleRowStyle = listTemplate.getTitleRowStyle() ;
			XMLDocument pageDoc = XMLDocument.parse(pageElement);
			XMLElement root = pageDoc.getRootElement();
			String controller  = root.getAttributeValue(TAG_CONTROLLER);
			String modelClass = root.getAttributeValue(TAG_OBJECT);
			page.setModelClass(modelClass);
			String title = root.getAttributeValue(TAG_TITLE);
			page.setTitle(title);
			page.setPageForwards(readPageForward(root.getFirstChildElement(TAG_FORWARDS)));
			page.setAjaxGroups(readAjaxGroups(root.getFirstChildElement(TAG_AJAXGROUPS)));
			page.setFormName(listTemplate.getFormName());
			ListController listController  =  null; 
			if(TEMPLATETYPE_LIST.equalsIgnoreCase(templateType))   {
				listController = (ListController) Class.forName(controller).newInstance() ;
			} else if ( TEMPLATETYPE_DATASHEET.equalsIgnoreCase(templateType)) {
				listController = (DataSheetController) Class.forName(controller).newInstance() ;
			}
			page.setViewController(listController);
			IRadsContext ctx = null;
			String auth = String.valueOf(req.getAttribute("authToken")) ;
			if (!Utils.isNullString(auth)) {
				ctx  = page.getViewController().generateContext(auth);
			}else {
				ctx  = page.getViewController().generateContext(req,response);
			}
			if ( ctx == null || !ctx.isAuthenticated()) {
				throw new ServletException("Authentication Failed- Rads Level");
			}
			listController.setContext(ctx);
			if(mode != null)
				((ListController)listController).setMode(mode);
			listController.setRecordsPerPage(noRows);
			List<XMLElement> includedJSs = root.getChildElements(TAG_INCLUDEDJS);
			if(!Utils.isNullList(includedJSs)) {
				for (XMLElement elem : includedJSs) {
					if (!Utils.isNullString(elem.getValue()))
						page.addIncludedJSs(elem.getValue());
				}
			}
			/*List<PanelDef> fixedPanelList = listTemplate.getFixedPanels() ;
			addFixedElements(listTemplate, fixedPanelList, listController, page);*/
			
			//page.setUniqueKeySeperator(listTemplate.getUniqueKeySeperator());
			UIControl pageNum = null ; 
			if (listTemplate.isHidePageNumberfield()) {
				pageNum = new UIHidden(listTemplate.getPageNumberfield()) ;
			} else{
				pageNum = new UIText(listTemplate.getPageNumberfield()) ;
				((UIText)pageNum).setSize(1);
			}
			page.setPageNumElement(pageNum);
			page.setOddRowStyle(oddRowStyle);
			page.setEvenRowStyle(evenRowStyle);
			page.setTitleRowStyle(titleRowStyle);
			XMLElement elements = root.getFirstChildElement(TAG_ELEMENTS);
			UIDiv containerDiv = new UIDiv();
			UIDiv listDiv= null;
			UIFilterSet filterSet = null;
			boolean populatedContainer = false;
		
			List <XMLElement> elementList = elements.getChildElements();
			for (XMLElement sectionElement : elementList) {
				if (sectionElement.getTag().equals(TAG_SECTION)) {
					UIDiv div = readSection(sectionElement, page.getTemplate(), listController, page);
					page.addInnerElement(new UIElement(div));
				}else if (sectionElement.getTag().equals(TAG_LISTSECTION))  {
					listDiv = readListSection(sectionElement, listTemplate, listController, page,mode,templateType);
					page.addInnerElement(new UIElement(listDiv));
				} else if (sectionElement.getTag().equals(TAG_FILTERSECTION)) {
					filterSet = readFilterSection(sectionElement, listTemplate, listController, page);
					page.setFilterSet(filterSet);
					page.addInnerElement(new UIElement(filterSet));
				}else if (sectionElement.getTag().equals(TAG_CORESECTION)) {
					containerDiv = readCoreSection(sectionElement, listTemplate, listController, page,mode,templateType);
					page.addInnerElement(new UIElement(containerDiv));
				}
			/*	if (!populatedContainer && filterSet!= null && listDiv!=null ) {
					containerDiv.addElement(new UIElement(filterSet));
					containerDiv.addElement(new UIElement(listDiv));
					containerDiv.setStyle(listTemplate.getPageStyle());
					populatedContainer= true;
					page.addInnerElement(new UIElement(containerDiv));
							
				}*/
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	
	
	protected static List<AjaxGroup> readAjaxGroups(XMLElement ajaxGroups) {
		if (ajaxGroups == null) return null;
		List<AjaxGroup> ajaxGroupList = new ArrayList<AjaxGroup> ();
		List<XMLElement> list = ajaxGroups.getChildElements(TAG_AJAXGROUP);
		for (XMLElement grp : list) {
			AjaxGroup  ajaxGroup = new AjaxGroup() ;
			String service   = grp.getAttributeValue(TAG_SERVICE);
			ajaxGroup.setAjaxService(service);
			XMLElement reqElements = grp.getFirstChildElement(TAG_REQUESTELEMENTS);
			List <XMLElement> reqElementList = reqElements.getChildElements(TAG_REQUESTELEMENT);
			for (XMLElement req : reqElementList) {
				String key =  req.getAttributeValue(TAG_KEY);
				String value = req.getValue() ;
				ajaxGroup.addRequestElement(key,value);
			}
			
			XMLElement respElements = grp.getFirstChildElement(TAG_RESPONSEELEMENTS);
			List <XMLElement> respElementList = respElements.getChildElements(TAG_RESPONSEELEMENT);
			for (XMLElement resp : respElementList) {
				String key =  resp.getAttributeValue(TAG_KEY);
				String value = resp.getValue() ;
				ajaxGroup.addResponseElement(key,value);
			}
			ajaxGroupList.add(ajaxGroup);
		}
		return ajaxGroupList;
	}
	
	protected static List<PageForward>  readPageForward(XMLElement forwards) {
		if (forwards == null)  return null;
		List <PageForward> pageForwards = new ArrayList<PageForward>();
		List<XMLElement> forwardList = forwards.getChildElements(TAG_FORWARD);
		for (XMLElement forward : forwardList) {
			String key = forward.getAttributeValue(TAG_KEY);
			String link = forward.getValue();
			PageForward pageForward = new PageForward(key,link);
			pageForwards.add(pageForward);
			
		}
		return pageForwards;
		
	}
	
	protected static UIForm readForm(XMLElement elements,ViewController objController,UIPage page,
			ViewController.Mode mode,String templateType) throws Exception{
		XMLElement formElement = elements.getFirstChildElement(TAG_UIFORM);
		String method = formElement.getAttributeValue(TAG_METHOD);
		String encType = formElement.getAttributeValue(TAG_ENCTYPE);
		/*XMLElement header = formElement.getFirstChildElement(TAG_HEADER);
		XMLElement detail = formElement.getFirstChildElement(TAG_DETAIL);
		XMLElement summary = formElement.getFirstChildElement(TAG_SUMMARY);*/
		//String action = formElement.getAttributeValue(TAG_ACTION);
		String id = formElement.getAttributeValue(TAG_ID) ;
		
		UIForm form = new UIForm(id,"Post".equalsIgnoreCase(method)?UIForm.METHOD.POST:UIForm.METHOD.GET );
		String style = formElement.getAttributeValue(TAG_STYLE) ;
		form.setStyle(style);
		if (!Utils.isNullString(encType))
			form.setEncType(encType);
		List <XMLElement> elementList = formElement.getChildElements();
		for (XMLElement xmlElement : elementList) {
			if (TAG_SECTION.equals(xmlElement.getTag())) {
				UIDiv div = readSection(xmlElement, page.getTemplate(), objController, page);
				form.addElement(new UIElement(div));
			}else if (TAG_CORESECTION.equals(xmlElement.getTag())) {
				UIDiv div =readCoreSection(xmlElement, page.getTemplate(), objController, page, mode, templateType);
				form.addElement(new UIElement(div));
			}else { 
				UIElement element = UIElementGenerator.getUIElement(xmlElement, objController,page,false,null);
		 		if (element != null )  {
		 			form.addElement(element);
		 		}
			}
		}
		return form;
	}
	
  private static UIElement getElementImage(XMLElement xmlElement,ViewController controller, UIPage page) throws Exception {
	 // UIControl control= null ;
	  return UIElementGenerator.getUIElement(xmlElement, controller, page, false, null);
  }
	  /*String type = xmlElement.getAttributeValue(TAG_TYPE);
	  String rendered = xmlElement.getAttributeValue(TAG_RENDERED);
	  String property = xmlElement.getAttributeValue(TAG_PROPERTY);
	  String constantValue = xmlElement.getAttributeValue(TAG_CONSTANTVALUE);
	  String id = xmlElement.getAttributeValue(TAG_ID);*.
	/*  if("UINote".equals(type))  
		  control = new UINote("");
	  else if("UICheckBox".equals(type))
		  control = new UICheckBox(id);
	  else if("UIRadioBox".equals(type))
		  control = new UIRadioBox(id);
	  else if("UIButton".equals(type)) {
		  control = new UIButton(id);
	  }else if("UIImage".equals(type)) {
		  control = new UIImage(id);
		  String src = xmlElement.getAttributeValue(TAG_SRC);
		  String height= xmlElement.getAttributeValue(TAG_HEIGHT); ;
		  String width= xmlElement.getAttributeValue(TAG_WIDTH); ;
		  ((UIImage)control).setSrc(src);
		  ((UIImage)control).setHeight(height);
		  ((UIImage)control).setWidth(width);
	  }
	  UIElement elementImage = new UIElement();
	  elementImage.setControl(control);
	  elementImage.setId(id);
	  elementImage.setRendered(rendered);
	  elementImage.setModelProperty(property);
	  elementImage.setConstantValue(constantValue);
	  return elementImage;*/

	
  protected static UITableRow readDataEntryColumn(XMLElement dataEntryElement, ListTemplateType template ,ViewController controller, UIDataSheetPage page) 
  throws Exception{
	  UITableRow row = new UITableRow();
	  List<XMLElement> columns   =  dataEntryElement.getChildElements(TAG_COLUMN) ;
	  for (XMLElement  column: columns ) {
			String width = column.getAttributeValue(TAG_WIDTH);
			UITableCol col = new UITableCol();
			col.setWidth(width);
			List<XMLElement> xmlElements= column.getChildElements(TAG_ELEMENT);
			for (XMLElement xmlElement :xmlElements) {
				UIElement element = UIElementGenerator.getUIElement(xmlElement, controller, page, false, null);
				page.addDataEntryColumn(element);
				col.addElement(element);
			}
			
			
			row.addCol(col);
		}
	  
	  if (Utils.isNullList(page.getTable().getInnerRows()) || (page.getTable().getInnerRows().size() % 2 == 0))
		  row.setStyle(template.getOddRowStyle());
	  else
		  row.setStyle(template.getEvenRowStyle());
	  page.setRowForEntry(row);
	  return row ;
  }
	
	protected static UIDiv readListSection (XMLElement section, ListTemplateType template ,ViewController objController, UIListPage page,
			ViewController.Mode mode,String templateType) throws Exception{
		TemplateType.Section listSection = template.getListSection();
		XMLElement tableTitle = section.getFirstChildElement(TAG_TITLEROW);
		String tblID = section.getAttributeValue(TAG_ID);
		UITableHead head = new UITableHead();
		UITableRow headRow = new UITableRow();
		if (tableTitle != null ) {
			String colSpan = tableTitle.getAttributeValue(TAG_COLSPAN);
			String tabTit = tableTitle.getAttributeValue(TAG_TITLE);
			headRow.addCol(new UITableCol(new UIElement(new UILabel(tabTit)),null,colSpan));
			head.setRow(headRow);
			page.addHeader(head);
		}
		
		XMLElement columnList =  section.getFirstChildElement(TAG_COLUMNS);
		String allowRowSelection = columnList.getAttributeValue(TAG_ALLOWROWSELECTION);
		XMLElement selectionColumn= columnList.getFirstChildElement(TAG_SELECTIONCOLUMN);
		List<XMLElement> columns   =  columnList.getChildElements(TAG_COLUMN) ;
		String uniqueProperty= columnList.getAttributeValue(TAG_UNIQUEPROPERTY);		
		page.setAllowRowSelection(allowRowSelection);
		page.setUniqueProperty(uniqueProperty);
		UITableHead titleHead = new UITableHead();
		UITableRow titleRow = new UITableRow();
		boolean hasColSelection =  !Utils.isNullString(allowRowSelection) && selectionColumn != null;
		if(hasColSelection ){
			UITableCol col = new UITableCol("");
			col.setDataProperty(uniqueProperty);
			titleRow.addCol(col );
			String control = "UICheckBox";
			if (SINGLE.equalsIgnoreCase(allowRowSelection)) {
				control ="UIRadioBox";
			}
			
			UIDataColumn uiDataColumn = new UIDataColumn("","");
			List<XMLElement> elements= selectionColumn.getChildElements(TAG_ELEMENT);
			for (XMLElement element :elements) {
				String rendered = element.getAttributeValue(TAG_RENDERED);
				String id = element.getAttributeValue(TAG_ID);
				col.setId(id);
				page.setUniquePropertyUIId(id);
				uiDataColumn.addElementImage(getElementImage(element,objController,page));
			}
			page.addUIDataColumns(uiDataColumn);
			
		}
		for (XMLElement  column: columns ) {
			String width = column.getAttributeValue(TAG_WIDTH);
			String title =column.getAttributeValue(TAG_TITLE);
			UITableCol col = new UITableCol();
			UILabel lbl = new UILabel("");
			lbl.setLabel(title);
			UIElement titLabel = new UIElement(lbl) ;
			titLabel.setStyle(template.getTitleRowSpanStyle());
			col.addElement(titLabel);
			col.setWidth(width);
			titleRow.addCol(col );
			page.addColumnDefs(col);
			
			
			UIDataColumn uiDataColumn = new UIDataColumn(title,width);
			List<XMLElement> elements= column.getChildElements(TAG_ELEMENT);
			for (XMLElement element :elements) {
				uiDataColumn.addElementImage(getElementImage(element,objController,page));
			}
			page.addUIDataColumns(uiDataColumn);
		}
		titleHead.setRow(titleRow);
		page.addHeader(titleHead);
		if(page.getTable() == null ) {
			UITable mainTable = new UITable();
			page.setTable(mainTable);
		}
		page.getTable().setId(tblID);
		if ( template instanceof DataSheetTemplateType) {
			XMLElement dataEntryElement = section.getFirstChildElement(TAG_DATAENTRYCOLS);
			if (dataEntryElement != null ) {
				UITableRow row = readDataEntryColumn(dataEntryElement,template, objController, (UIDataSheetPage)page);
				/*if(ViewController.Mode.CREATE.equals(mode) )
					page.getTable().addRow(row);*/
			}
		}
			
		UIDiv mainDiv = new UIDiv();
		mainDiv.setId(listSection.getId());
		mainDiv.setStyle(listSection.getStyle());
		mainDiv.addElement(new UIElement(page.getTable()));
		return mainDiv;
	}
	
	protected static UIDiv readCoreSection (XMLElement section, TemplateType template ,ViewController objController, UIPage page,
			ViewController.Mode mode,String templateType) throws Exception{
	    UIDiv div = new UIDiv ();
		List <XMLElement> elementList = section.getChildElements();
		String coreStyle = template.getCoreStyle() ;
		if (Utils.isNullString(coreStyle))
			div.setStyle(template.getPageStyle());
		else
			div.setStyle(coreStyle);
		for (XMLElement sectionElement : elementList) {
			if (sectionElement.getTag().equals(TAG_SECTION)) {
				UIDiv divChi = readSection(sectionElement, page.getTemplate(), objController, page);
				div.addElement(new UIElement(divChi));
			}else if (sectionElement.getTag().equals(TAG_LISTSECTION))  {
				UIDiv listDiv = readListSection(sectionElement, (ListTemplateType)template, objController, (UIListPage)page,mode,templateType);
				div.addElement(new UIElement(listDiv));
			} else if (sectionElement.getTag().equals(TAG_FILTERSECTION)) {
				UIFilterSet filterSet = readFilterSection(sectionElement,(ListTemplateType) template, objController,(UIListPage) page);
				((UIListPage)page).setFilterSet(filterSet);
				div.addElement(new UIElement(filterSet));
			}else if (sectionElement.getTag().equals(TAG_ERRORSECTION)) {
				UIErrorList  errorList = page.getErrorList() ;
				errorList.setStyle(template.getErrorSection().getStyle());
				div.addElement(new UIElement(errorList));
			}else if (sectionElement.getTag().equals(TAG_SECTION)) {
				UIDiv newDiv = readSection(sectionElement, template, objController, page);
				div.addElement(new UIElement(newDiv));
			}
		}
		return div;
	}
	
	protected static UIFilterSet readFilterSection (XMLElement section, ListTemplateType template ,ViewController objController, UIListPage page) throws Exception{
		TemplateType.Section filterSection = template.getFilterSection();
		UIFilterSet div  = new UIFilterSet() ;
		div.setStyle(filterSection.getStyle());
		div.setId(filterSection.getId());
		
		List <XMLElement> sectionElementList = section.getAllChildElements();
		if (sectionElementList != null && !sectionElementList.isEmpty()) {
			for (XMLElement xmlNodeElement : sectionElementList) {
				if (xmlNodeElement.getTag().equals(TAG_FILTERNODE))  {
					String operator = xmlNodeElement.getAttributeValue(TAG_OPERATOR);
					XMLElement xmlElement =xmlNodeElement.getFirstChildElement(TAG_ELEMENT) ;
			 		UIElement element = UIElementGenerator.getUIElement(xmlElement, objController,page,false,null);
			 	    if (element != null )  {
			 	    	UIFilterElement filterElement =  new UIFilterElement (element);
			 	    	if (!Utils.isNull(operator))
			 	    		filterElement.setOperator(operator);
			 			div.addElement(filterElement);
			 		}
				}else {
					UIElement element = UIElementGenerator.getUIElement(xmlNodeElement, objController,page,false,null);
					div.addElement(element);
				}
		 	}
		}
		if (filterSection.isShowIntable())  {
			int cond =0  ; 
			if (!Utils.isNullList(div.getElements()))  {
			 	for (UIElement element : div.getElements() )  {
			 		if (element instanceof UICondition) {
			 			UITable tableDet  = UITable.tabularizeElements("TBLCnd" + cond++, ((UICondition)element).getTrueElements(), filterSection.getNoCols()) ;
			 			((UICondition)element).setTrueElements(null);
			 			((UICondition)element).addTrueElement(new UIElement(tableDet));
			 		}
			 	}
			}
 		UITable tableDet  = UITable.tabularizeElements("TBL" + filterSection.getId(), div.getElements(), filterSection.getNoRows(), filterSection.getNoCols()) ;
 		div.setElements(null);
 		tableDet.setStyle(filterSection.getTabStyle());
 		div.addElement(new UIElement(tableDet));
 		
		
		if (!Utils.isNullList(filterSection.getFixedPanels())) {
			for(PanelDef pDef : filterSection.getFixedPanels()) {
				List<UIElement> panelContents = getPanel(pDef, objController, page) ; 
				if (!Utils.isNullList(panelContents))
				for (UIElement panelContent : panelContents)	
					div.addElement(panelContent);
			}
		}
		
		
	}
		
		return div;
		
	}
	protected static UIDiv readSection (XMLElement section, TemplateType template ,ViewController objController, UIPage page) throws Exception{
		
		List <XMLElement> sectionElementList = section.getAllChildElements();
		String sectionType = section.getAttributeValue(TAG_SECTIONTYPE);
		TemplateType.Section tempSection = null;
		if ( !Utils.isNullList(template.getSections())  ) {
			for (TemplateType.Section templateSection :  template.getSections()) {
				if (templateSection.getId().equals(sectionType)) {
					tempSection = templateSection;
					break;
				}
			}
		}
		UIDiv div  = new UIDiv() ;
		div.setStyle(tempSection.getStyle());
		div.setId(tempSection.getId());
		if (!Utils.isNullList(tempSection.getFixedPanels())) {
			for(PanelDef pDef : tempSection.getFixedPanels()) {
				List<UIElement> panelContents = getPanel(pDef, objController, page) ; 
				if (!Utils.isNullList(panelContents))
				for (UIElement panelContent : panelContents)	
					div.addElement(panelContent);
			}
		}
		
		if (sectionElementList != null && !sectionElementList.isEmpty()) {
			for (XMLElement xmlElement : sectionElementList) {
		 		UIElement element = UIElementGenerator.getUIElement(xmlElement, objController,page,false,null);
		 		if (element != null )  {
		 			div.addElement(element);
		 		}
		 	}
		}
		
		
	/*	if (tempSection.isShowIntable())  {
	 		UITable tableSumm  = UITable.tabularizeElements(" ", div.getElements(), tempSection.getNoRows(),
	 				tempSection.getNoCols()) ;
	 		tableSumm.setStyle(tempSection.getTabStyle());
	 		div.setElements(null);
	 		div.addElement(new UIElement(tableSumm));
 		}*/
		
		div =applyTabularization(tempSection, div);
		
		
		
		return div;
	}
	
	
	public static UIDiv applyTabularization(TemplateType.Section tempSection,UIDiv div) {
		if (tempSection.isShowIntable())  {
			int cond =0  ; 
			if (!Utils.isNullList(div.getElements()))  {
			 	for (UIElement element : div.getElements() )  {
			 		if (element instanceof UICondition) {
			 			/*UITable tableDet  = UITable.tabularizeElements("TBLCnd" + cond++, ((UICondition)element).getTrueElements(), tempSection.getNoCols()) ;
			 			((UICondition)element).setTrueElements(null);
			 			((UICondition)element).addTrueElement(new UIElement(tableDet));*/
			 			div.insertAt(element, ((UICondition)element).getTrueElements());
			 		}
			 	}
			}
 		UITable tableDet  = UITable.tabularizeElements("TBL" + tempSection.getId(), div.getElements(), tempSection.getNoRows(), tempSection.getNoCols()) ;
 		div.setElements(null);
 		tableDet.setStyle(tempSection.getTabStyle());
 		div.addElement(new UIElement(tableDet));
	}
		return div;
	}
	
	protected static UILookupPage generateLookupPage (Document  pageElement,String templateName,ModelObject object,HttpServletRequest req,ViewController.Mode mode) throws Exception{
		UILookupPage page = new UILookupPage() ;
		TemplateType template = (TemplateType) TemplateReader.INSTANCE.readTemplate( templateName);
		page.setTemplate(template);
		XMLDocument pageDoc = XMLDocument.parse(pageElement);
		XMLElement root = pageDoc.getRootElement();
		/*String controller  = root.getAttributeValue(TAG_CONTROLLER);
		ViewController objController  = (ViewController) Class.forName(controller).newInstance() ;
		page.setViewController(objController);
		IRadsContext ctx  = objController.generateContext(req);
		if ( ctx == null || !ctx.isAuthenticated()) {
			throw new ServletException("Authentication Failed- Rads Level");
		}
		objController.setContext(ctx);*/
		/*if(mode != null)
			((GeneralController)objController).setMode(mode);
		((GeneralController)objController).setObject(object);*/
		String title = root.getAttributeValue(TAG_TITLE);
		page.setTitle(title);
		String searchFieldId = root.getChildAttributeValue(TAG_SEARCHFIELDID);
		String listFieldId = root.getChildAttributeValue(TAG_LISTFIELDID);
		page.setSearchControl(searchFieldId);
		page.setListControl(listFieldId);
		XMLElement elements = root.getFirstChildElement(TAG_ELEMENTS);
		page.setPageForwards(readPageForward(root.getFirstChildElement(TAG_FORWARDS)));
		page.setAjaxGroups(readAjaxGroups(root.getFirstChildElement(TAG_AJAXGROUPS)));
	//	UIForm form = readForm(elements, objController,page);
		UIForm form = readForm(elements, null,page,mode,templateName);
		page.setFixedActionField(template.getFixedActionfield());
	 	page.setForm(form);
	 	page.setStyleSheets(template.getStyleSheets());
	 /*	List<XMLElement> includedJSs = root.getChildElements(TAG_INCLUDEDJS);
		if(!Utils.isNullList(includedJSs)) {
			for (XMLElement elem : includedJSs) {
				if (!Utils.isNullString(elem.getValue()))
					page.addIncludedJSs(elem.getValue());
			}
		}*/
		return page ;
		
	}
	
	protected static UIGeneralPage generateGeneralPage (Document  pageElement,String templateName,ModelObject object,HttpServletRequest req,
			ViewController.Mode mode,HttpServletResponse response) throws Exception{
		UIGeneralPage page = new UIGeneralPage() ;
		TemplateType template = (TemplateType) TemplateReader.INSTANCE.readTemplate( templateName);
		page.setTemplate(template);
		XMLDocument pageDoc = XMLDocument.parse(pageElement);
		XMLElement root = pageDoc.getRootElement();
		String controller  = root.getAttributeValue(TAG_CONTROLLER);
		ViewController objController  = (ViewController) Class.forName(controller).newInstance() ;
		page.setViewController(objController);
		IRadsContext ctx =null;
		String auth = String.valueOf(req.getAttribute("authToken")) ;
		if (!Utils.isNullString(auth)) {
			ctx  = page.getViewController().generateContext(auth);
		}else {
			ctx  = page.getViewController().generateContext(req,response);
		}
		if ( ctx == null || !ctx.isAuthenticated()) {
			throw new ServletException("Authentication Failed- Rads Level");
		}
		objController.setContext(ctx);
		if(mode != null)
			((GeneralController)objController).setMode(mode);
		((GeneralController)objController).setObject(object);
		String title = root.getAttributeValue(TAG_TITLE);
		page.setTitle(title);
		XMLElement elements = root.getFirstChildElement(TAG_ELEMENTS);
		page.setPageForwards(readPageForward(root.getFirstChildElement(TAG_FORWARDS)));
		page.setAjaxGroups(readAjaxGroups(root.getFirstChildElement(TAG_AJAXGROUPS)));
		UIForm form = readForm(elements, objController,page,mode,templateName);
		page.setFixedActionField(template.getFixedActionfield());
	 	page.setForm(form);
	 	page.setStyleSheets(template.getStyleSheets());
	 	List<XMLElement> includedJSs = root.getChildElements(TAG_INCLUDEDJS);
		if(!Utils.isNullList(includedJSs)) {
			for (XMLElement elem : includedJSs) {
				if (!Utils.isNullString(elem.getValue()))
					page.addIncludedJSs(elem.getValue());
			}
		}
		return page ;
		
	}

	protected static UICRUDPage generateCRUDPage (Document  pageElement,String templateName,ModelObject object,HttpServletRequest req,
			ViewController.Mode mode,HttpServletResponse response) 
			throws RadsException,ServletException{
		UICRUDPage page = new UICRUDPage();
		try  {
			CRUDTemplateType  pageTemplate = (CRUDTemplateType)TemplateReader.INSTANCE.readTemplate( templateName);
			page.setTemplate(pageTemplate);
			XMLDocument pageDoc = XMLDocument.parse(pageElement);
			XMLElement root = pageDoc.getRootElement();
			String controller  = root.getAttributeValue(TAG_CONTROLLER);
			ViewController objController  = (ViewController) Class.forName(controller).newInstance() ;
			page.setViewController(objController);
			IRadsContext ctx = null;
			String auth = String.valueOf(req.getAttribute("authToken")) ;
			if (!Utils.isNullString(auth)) {
				ctx  = page.getViewController().generateContext(auth);
			}else {
				ctx  = page.getViewController().generateContext(req,response);
			}
			if ( ctx == null || !ctx.isAuthenticated()) {
				throw new ServletException("Authentication Failed- Rads Level");
			}
			objController.setContext(ctx);
			if(mode != null)
				((CRUDController)objController).setMode(mode);
			((CRUDController)objController).setObject(object);
			String title = root.getAttributeValue(TAG_TITLE);
			page.setTitle(title);
			XMLElement elements = root.getFirstChildElement(TAG_ELEMENTS);
			page.setPageForwards(readPageForward(root.getFirstChildElement(TAG_FORWARDS)));
			page.setAjaxGroups(readAjaxGroups(root.getFirstChildElement(TAG_AJAXGROUPS)));
			UIForm form = readForm(elements, objController,page,mode,templateName);
			page.setFixedActionField(pageTemplate.getFixedActionfield());
		 	page.setForm(form);
		 	page.setStyleSheets(pageTemplate.getStyleSheets());
		 	List<XMLElement> includedJSs = root.getChildElements(TAG_INCLUDEDJS);
			if(!Utils.isNullList(includedJSs)) {
				for (XMLElement elem : includedJSs) {
					if (!Utils.isNullString(elem.getValue()))
						page.addIncludedJSs(elem.getValue());
				}
			}
		/*
			List<PanelDef> fixedPanelList = pageTemplate.getFixedPanels() ;
			addFixedElements(pageTemplate, fixedPanelList, objController, page);
			
		 		if (pageTemplate.getHeaderSection().isShowIntable())  {
		 			UITable tableHead  = UITable.tabularizeElements("TBLH", form.getHeaderElements(), pageTemplate.getHeaderSection().getNoRows(),
		 					pageTemplate.getHeaderSection().getNoCols()) ;
		 			tableHead.setStyle(pageTemplate.getHeaderSection().getTabStyle());
		 			form.setHeaderElements(null);
		 			form.addHeaderElement(new UIElement(tableHead));
		 		}
		 	
		 		if (pageTemplate.getDataSection().isShowIntable())  {
		 				int cond =0  ; 
		 				if (!Utils.isNullList(form.getDetailElements()))  {
			 			 	for (UIElement element : form.getDetailElements() )  {
			 			 		if (element instanceof UICondition) {
			 			 			UITable tableDet  = UITable.tabularizeElements("TBLCnd" + cond++, ((UICondition)element).getTrueElements(), pageTemplate.getDataSection().getNoCols()) ;
			 			 			((UICondition)element).setTrueElements(null);
			 			 			((UICondition)element).addTrueElement(new UIElement(tableDet));
			 			 		}
			 			 	}
		 				}
				 		UITable tableDet  = UITable.tabularizeElements("TBL", form.getDetailElements(), pageTemplate.getDataSection().getNoCols()) ;
				 		form.setDetailElements(null);
				 		tableDet.setStyle(pageTemplate.getDataSection().getTabStyle());
				 		form.addDetailElement(new UIElement(tableDet));
		 		}
		 		
		 		if (pageTemplate.getSummarySection().isShowIntable())  {
			 		UITable tableSumm  = UITable.tabularizeElements("TBLS", form.getSummaryElements(), pageTemplate.getSummarySection().getNoRows(),
			 				pageTemplate.getSummarySection().getNoCols()) ;
			 		tableSumm.setStyle(pageTemplate.getSummarySection().getTabStyle());
			 		form.setSummaryElements(null);
			 		form.addSummaryElement(new UIElement(tableSumm));
		 		}
		 		*/
		 	
			return page;
		}catch (Exception ex) {
			ex.printStackTrace(); 
			throw new RadsException (ex.getMessage()) ;
		}
		
	}
	
	
	protected static UITransactionPage generateTransactionPage (Document  pageElement,String templateName,ModelObject object,HttpServletRequest req,
			ViewController.Mode mode,HttpServletResponse response) 
			throws RadsException{
		UITransactionPage page = new UITransactionPage();
		try  {
			TransactionTemplateType  pageTemplate = (TransactionTemplateType)TemplateReader.INSTANCE.readTemplate( templateName);
			page.setTemplate(pageTemplate);
			XMLDocument pageDoc = XMLDocument.parse(pageElement);
			XMLElement root = pageDoc.getRootElement();
			String controller  = root.getAttributeValue(TAG_CONTROLLER);
			ViewController objController  = (ViewController) Class.forName(controller).newInstance() ;
			page.setViewController(objController);
			IRadsContext ctx =null;
			String auth = String.valueOf(req.getAttribute("authToken")) ;
			if (!Utils.isNullString(auth)) {
				ctx  = page.getViewController().generateContext(auth);
			}else {
				ctx  = page.getViewController().generateContext(req,response);
			}
			if ( ctx == null || !ctx.isAuthenticated()) {
				throw new ServletException("Authentication Failed- Rads Level");
			}
			objController.setContext(ctx);
			if(mode != null)
				((TransactionController)objController).setMode(mode);
			((TransactionController)objController).setObject(object);
			XMLElement elements = root.getFirstChildElement(TAG_ELEMENTS);
			String title = root.getAttributeValue(TAG_TITLE);
			page.setTitle(title);
			page.setPageForwards(readPageForward(root.getFirstChildElement(TAG_FORWARDS)));
			page.setAjaxGroups(readAjaxGroups(root.getFirstChildElement(TAG_AJAXGROUPS)));
			UIForm form = readForm(elements, objController,page,mode,templateName);
			page.setFixedActionField(pageTemplate.getFixedActionfield());
		 	page.setForm(form);
		 	page.setStyleSheets(pageTemplate.getStyleSheets());
		 	List<XMLElement> includedJSs = root.getChildElements(TAG_INCLUDEDJS);
			if(!Utils.isNullList(includedJSs)) {
				for (XMLElement elem : includedJSs) {
					if (!Utils.isNullString(elem.getValue()))
						page.addIncludedJSs(elem.getValue());
				}
			}
			
			//List<PanelDef> fixedPanelList = pageTemplate.getFixedPanels() ;
			//addFixedElements(pageTemplate, fixedPanelList, objController, page);
			
		 		/*if (pageTemplate.getHeaderSection().isShowIntable())  {
		 			UITable tableHead  = UITable.tabularizeElements("TBLH", form.getHeaderElements(), pageTemplate.getHeaderSection().getNoRows(),
		 					pageTemplate.getHeaderSection().getNoCols()) ;
		 			tableHead.setStyle(pageTemplate.getHeaderSection().getTabStyle());
		 			form.setHeaderElements(null);
		 			form.addHeaderElement(new UIElement(tableHead));
		 		}
		 	
		 		if (pageTemplate.getDetailSection().isShowIntable())  {
		 				int cond =0  ; 
		 				if (!Utils.isNullList(form.getDetailElements()))  {
			 			 	for (UIElement element : form.getDetailElements() )  {
			 			 		if (element instanceof UICondition) {
			 			 			UITable tableDet  = UITable.tabularizeElements("TBLCnd" + cond++, ((UICondition)element).getTrueElements(), pageTemplate.getDetailSection().getNoCols()) ;
			 			 			((UICondition)element).setTrueElements(null);
			 			 			((UICondition)element).addTrueElement(new UIElement(tableDet));
			 			 		}
			 			 	}
		 				}
				 		UITable tableDet  = UITable.tabularizeElements("TBL", form.getDetailElements(), pageTemplate.getDetailSection().getNoCols()) ;
				 		form.setDetailElements(null);
				 		tableDet.setStyle(pageTemplate.getDetailSection().getTabStyle());
				 		form.addDetailElement(new UIElement(tableDet));
		 		}
		 		
		 		if (pageTemplate.getSummarySection().isShowIntable())  {
			 		UITable tableSumm  = UITable.tabularizeElements("TBLS", form.getSummaryElements(), pageTemplate.getSummarySection().getNoRows(),
			 				pageTemplate.getSummarySection().getNoCols()) ;
			 		tableSumm.setStyle(pageTemplate.getSummarySection().getTabStyle());
			 		form.setSummaryElements(null);
			 		form.addSummaryElement(new UIElement(tableSumm));
		 		}*/
		 		
		 	page.setViewController(objController);
			return page;
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new RadsException (ex.getMessage()) ;
		}
		
	}
	
	/*
	protected static UICRUDPage generateCRUDPage (Document  pageElement,String templateName) 
			throws IOException,SAXException,ParserConfigurationException{
		UICRUDPage page = new UICRUDPage();
		try {
	 	CRUDTemplate  pageTemplate = (CRUDTemplate)TemplateReader.INSTANCE.readTemplate( templateName);
	 	NodeList pageContent = pageElement.getElementsByTagName(TAG_PAGECONTENT);
	 	NodeList dataSetups = ((Element)pageContent.item(0)).getElementsByTagName(TAG_DATASETUP);
	 	Element dataSetup =  (Element)dataSetups.item(0);
	 	String controller = dataSetup.getAttribute(TAG_CONTROLLER);
	 	ObjectController objController  = (ObjectController) Class.forName(controller).newInstance() ;
	 	String object = dataSetup.getAttribute(TAG_OBJECT);
	 	NodeList elementsNode = ((Element)pageContent.item(0)).getElementsByTagName(TAG_ELEMENTS);
	 	Element formElement =(Element) elementsNode.item(0);
	 	String action = formElement.getAttribute(TAG_ACTION);
		String method = formElement.getAttribute(TAG_METHOD);
		String id = formElement.getAttribute(TAG_ID);
		UIForm form = new UIForm(id,UIForm.METHOD.POST,action );
	 	NodeList elements = formElement.getElementsByTagName(TAG_ELEMENT);
	 	for (int i = 0 ; i < elements.getLength() ; i  ++ ) {
	 		Element uiElement = (Element) elements.item(i);
	 		UIElement element = UIElementGenerator.getUIElement(uiElement, objController);
	 		if (element != null )  {
	 			form.addElement(element);
	 		}
	 	}
	 	page.setForm(form);
	
	 	page.setStyleSheet(pageTemplate.getStyleSheet());
	 	if (pageTemplate.isShowIntable())  {
	 		pageTemplate.getNoRows() ;
	 		UITable table  = UITable.tabularizeElements("TBL", form.getElements(), pageTemplate.getNoCols()) ;
	 		form.setElements(null);
	 		form.addElement(table);
	 	}
	 	
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}*/
	
	

}

package com.techtrade.rads.framework.ui.templates;

import java.util.ArrayList;
import java.util.List;

import org.omg.IOP.TAG_ORB_TYPE;
import org.w3c.dom.Element;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.PanelDef;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLElement;

public class TemplateType {
	protected static final String TAG_ID = "Id";
	protected static final String TAG_DATATYPE = "DataType";
	protected static final String TAG_TYPE = "RendererType";
	protected static final String TAG_SHOW_INFRAME = "ShowInFrame";
	protected static final String TAG_STYLE_SHEET = "StyleSheet";
	protected static final String TAG_PAGESTYLE = "PageStyle";
	protected static final String TAG_RENDERERS = "Renderers";
	protected static final String TAG_RENDERER = "Renderer";
	protected static final String TAG_HTMLRENDERER = "HTMLRenderers";
	protected static final String TAG_FIXEDACTIONFIELD = "FixedActionField";
	protected static final String TAG_SUBMITACTIONFIELD = "SubmitActionField"; 
	protected static final String TAG_FIXEDACTIONPARAMFIELD = "FixedActionParamField"; 
	protected static final String TAG_INCLUDEDJS = "IncludeJS";
	protected static final String TAG_ERRORS="Errors";
	protected static final String TAG_SHOWIN="showin";
	protected static final String TAG_STYLE="style";
	protected static final String TAG_PANEL = "Panel";
	protected static final String TAG_PANELS = "Panels";
	protected static final String TAG_KEY = "key";
	protected static final String TAG_ODDROW = "OddRow";
	protected static final String TAG_EVENROW = "EvenRow";
	protected static final String TAG_TITLEROW = "TitleRow";
	protected static final String TAG_TITLEROWSPAN = "TitleRowSpan";
	protected static final String TAG_METADATA = "MetaData";
	protected static final String TAG_META = "meta";
	protected static final String TAG_CHARTSET = "charset";
	protected static final String TAG_NAME = "name";
	protected static final String TAG_CONTENT = "content";
	
	
	protected static final String TAG_SECTIONS = "Sections";
	protected static final String TAG_SECTION = "Section";
	protected static final String TAG_HEADERSECTION = "HeaderSection";
	protected static final String TAG_ERRORSECTION = "ErrorSection";
	protected static final String TAG_CORESECTION = "CoreSection";
	protected static final String TAG_NOROWS = "noRows";
	protected static final String TAG_NOCOLS = "noCols";
	protected static final String TAG_TABSTYLE =  "tabStyle" ;
	protected static final String TAG_SHOWINTABLE = "showinTable";
	protected static final String TAG_MANDATORYFIELD_MARKER = "MandatoryFieldMarker";
	protected static final String TAG_CRUDFIELD_MARKER = "CRUDFieldMarker";
	
	
	public enum DataType  {
    	MODELOBJECT , COLLECTION , REPORTOBJECT
    }  
    
	public class MetaData {
		String charset ;
		String name;
		String content ;
		public String getCharset() {
			return charset;
		}
		public void setCharset(String charset) {
			this.charset = charset;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public MetaData(String charset, String name, String content) {
			super();
			this.charset = charset;
			this.name = name;
			this.content = content;
		}
		
	}
	
	
	public class ErrorType {
	
		String showErrorsIn;
	    String errorStyle ; // span
	    
	    boolean showInTable;
	    String tablestyle ;
	    String oddRowStyle ; 
	    String evenRowStyle;
	    String headerStyle ;
	    
	    
		public boolean isShowInTable() {
			return showInTable;
		}
		public void setShowInTable(boolean showInTable) {
			this.showInTable = showInTable;
		}
		public String getTablestyle() {
			return tablestyle;
		}
		public void setTablestyle(String tablestyle) {
			this.tablestyle = tablestyle;
		}
		public String getOddRowStyle() {
			return oddRowStyle;
		}
		public void setOddRowStyle(String oddRowStyle) {
			this.oddRowStyle = oddRowStyle;
		}
		public String getEvenRowStyle() {
			return evenRowStyle;
		}
		public void setEvenRowStyle(String evenRowStyle) {
			this.evenRowStyle = evenRowStyle;
		}
		public String getHeaderStyle() {
			return headerStyle;
		}
		public void setHeaderStyle(String headerStyle) {
			this.headerStyle = headerStyle;
		}
		public String getShowErrorsIn() {
			return showErrorsIn;
		}
		public void setShowErrorsIn(String showErrorsIn) {
			this.showErrorsIn = showErrorsIn;
		}
		public String getErrorStyle() {
			return errorStyle;
		}
		public void setErrorStyle(String errorStyle) {
			this.errorStyle = errorStyle;
		}
	    
	    
	};
	
	ErrorType errorType = new ErrorType();
	
    DataType dataType;
    List<String> styleSheets;
    boolean showInFrame ;
    UIPage page  = null;
    String fixedActionfield ;
    String fixedActionParamfield ;
    String submitActionfield ;
    String mandatoryMarker ;
    String crudMarker ;
    String pageStyle ;
    String coreStyle; 
    
    List<String> includedJSs ;

    
    
    List<Section> sections ;
    List <MetaData> metaDatas ;
    
    
    
   public  ErrorType getErrorType() {
	   return errorType;
   }

	Section errorSection ;
	
	public Section getErrorSection() {
		return errorSection;
	}

	public void setErrorSection(Section errorSection) {
		this.errorSection = errorSection;
	}
	

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}


	public void addSection(Section section ) {
		if (sections == null ) {
			sections = new ArrayList<Section> ();
		}
		sections.add(section);
	}
	
	
	public List<MetaData> getMetaDatas() {
		return metaDatas;
	}

	public void setMetaDatas(List<MetaData> metaDatas) {
		this.metaDatas = metaDatas;
	}
	
	public void addMetaData(MetaData metaData) {
		if (metaDatas == null)
			metaDatas = new ArrayList<MetaData>();
		metaDatas.add(metaData);
		
	}

	public String getFixedActionfield() {
		return fixedActionfield;
	}

	public void setFixedActionfield(String fixedActionfield) {
		this.fixedActionfield = fixedActionfield;
	}
	
	
    
	public String getFixedActionParamfield() {
		return fixedActionParamfield;
	}

	public void setFixedActionParamfield(String fixedActionParamfield) {
		this.fixedActionParamfield = fixedActionParamfield;
	}

	public String getSubmitActionfield() {
		return submitActionfield;
	}

	public void setSubmitActionfield(String submitActionfield) {
		this.submitActionfield = submitActionfield;
	}

	protected UIPage getPage() {
		return page;
	}

	protected void setPage(UIPage page) {
		this.page = page;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public void addStyleSheet(String styleSheet) {
		if (styleSheets == null)
			styleSheets = new ArrayList<>();
		styleSheets.add(styleSheet);
	}
	
	
	public List<String> getStyleSheets() {
		return styleSheets;
	}

	public void setStyleSheets(List<String> styleSheets) {
		this.styleSheets = styleSheets;
	}

	public String getCoreStyle() {
		return coreStyle;
	}

	public void setCoreStyle(String coreStyle) {
		this.coreStyle = coreStyle;
	}

	public String getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}

	public boolean isShowInFrame() {
		return showInFrame;
	}

	public void setShowInFrame(boolean showInFrame) {
		this.showInFrame = showInFrame;
	}
	
	public String getMandatoryMarker() {
		return mandatoryMarker;
	}

	public void setMandatoryMarker(String mandatoryMarker) {
		this.mandatoryMarker = mandatoryMarker;
	}
	
	public String getCrudMarker() {
		return crudMarker;
	}
	public void setCrudMarker(String crudMarker) {
		this.crudMarker = crudMarker;
	}




	public List<String> getIncludedJSs() {
		return includedJSs;
	}

	public void setIncludedJSs(List<String> includedJSs) {
		this.includedJSs = includedJSs;
	}
	
	public void addIncludedJSs(String jsfile) {
		if (Utils.isNullList(includedJSs)) {
			includedJSs = new ArrayList<String> ();
		}
		includedJSs.add(jsfile);
	}

	public TemplateType generateTemplate(XMLElement  templateElement) {
		XMLElement hdnFixedActionfld = templateElement.getFirstChildElement(TAG_FIXEDACTIONFIELD);
		if (hdnFixedActionfld != null ) {
			setFixedActionfield(hdnFixedActionfld.getValue());
		}
		XMLElement submitActionfld = templateElement.getFirstChildElement(TAG_SUBMITACTIONFIELD);
		if(submitActionfld != null ) {
			setSubmitActionfield(submitActionfld.getValue());
		}
		
		XMLElement fixedActionParamField = templateElement.getFirstChildElement(TAG_FIXEDACTIONPARAMFIELD);
		if(fixedActionParamField != null ) {
			setFixedActionParamfield(fixedActionParamField.getValue());
		}
		String marker = templateElement.getChildAttributeValue(TAG_MANDATORYFIELD_MARKER);
		if (!Utils.isNull(marker))
			setMandatoryMarker(marker);
		
		String CRUDmarker = templateElement.getChildAttributeValue(TAG_CRUDFIELD_MARKER);
		if (!Utils.isNull(CRUDmarker))
			setCrudMarker(CRUDmarker);
		
		List<XMLElement> includedJSs = templateElement.getChildElements(TAG_INCLUDEDJS);
		if(!Utils.isNullList(includedJSs)) {
			for (XMLElement elem : includedJSs) {
				if (!Utils.isNullString(elem.getValue()))
					addIncludedJSs(elem.getValue());
			}
		}
		
	
		XMLElement metaDataXML = templateElement.getFirstChildElement(TAG_METADATA);
		if (metaDataXML != null ) {
			List<XMLElement> metasXML =  metaDataXML.getChildElements(TAG_META) ;
			metasXML.forEach(metaXML ->
			{
				String charset  = metaXML.getAttributeValue(TAG_CHARTSET);
				String content = metaXML.getAttributeValue(TAG_CONTENT);
				String name = metaXML.getAttributeValue(TAG_NAME);
				MetaData metaData = new TemplateType.MetaData(charset,name,content);
				addMetaData(metaData);
			}
			);
		}
		
		XMLElement renderers  = templateElement.getFirstChildElement(TAG_RENDERERS);
		List<XMLElement> rendererElements =  renderers.getChildElements(TAG_RENDERER);
		for (XMLElement renderer : rendererElements ) {
			String type = renderer.getChildAttributeValue(TAG_TYPE);
			if ("HTML".equalsIgnoreCase(type)) {
			  List<XMLElement> styleSheetsXML = renderer.getChildElements(TAG_STYLE_SHEET) ;
			  styleSheetsXML.forEach(styeSheetXML ->  { 
				  addStyleSheet(styeSheetXML.getValue());
			  });
      		  String pageStyle =   renderer.getChildAttributeValue(TAG_PAGESTYLE);
      		  setPageStyle(pageStyle);
      	  } 
		}
		XMLElement errors = templateElement.getFirstChildElement(TAG_ERRORS);
		if (errors  != null ) {
			String showErrorsIn  = errors.getAttributeValue(TAG_SHOWIN);
			String errorStyle = errors.getAttributeValue(TAG_STYLE);
			String showinTable = errors.getAttributeValue(TAG_SHOWINTABLE);
			errorType.setShowErrorsIn(showErrorsIn);
			errorType.setErrorStyle(errorStyle);
			if ("true".equalsIgnoreCase(showinTable)) { 
				errorType.setShowInTable(true);
				String oddRowStyle = errors.getChildAttributeValue(TAG_ODDROW);
				String evenRowStyle = errors.getChildAttributeValue(TAG_EVENROW);
				String titleRowStyle = errors.getChildAttributeValue(TAG_TITLEROW);
				errorType.setOddRowStyle(oddRowStyle);
				errorType.setEvenRowStyle(evenRowStyle);
				errorType.setHeaderStyle(titleRowStyle);
				String tabStyle = errors.getAttributeValue(TAG_TABSTYLE);
				errorType.setTablestyle(tabStyle);
			}
		}
		
		
		XMLElement sections = templateElement.getFirstChildElement(TAG_SECTIONS); 
		if (sections != null ) {
			List<XMLElement> sectionList = sections.getChildElements(TAG_SECTION) ;
			for (XMLElement element : sectionList ) {
				Section section  = readSection(element);
				addSection(section);
			}
			XMLElement coreSection = sections.getFirstChildElement(TAG_CORESECTION) ;
			if (coreSection != null) {
				coreStyle = coreSection.getAttributeValue(TAG_STYLE);
				XMLElement ErrorSection = coreSection.getFirstChildElement(TAG_ERRORSECTION);
				errorSection= readSection(ErrorSection);
				List<XMLElement> coreSectionList = coreSection.getChildElements(TAG_SECTION) ;
				for (XMLElement element : coreSectionList ) {
					Section section  = readSection(element);
					addSection(section);
				}
			}
		}
		/*XMLElement panels = templateElement.getFirstChildElement(TAG_PANELS);
		if (panels  != null ) {
			fixedPanels = new ArrayList<PanelDef>();
			List<XMLElement> panelList = panels.getChildElements(TAG_PANEL) ;
			for (XMLElement panel : panelList ) {
				String key = panel.getAttributeValue(TAG_KEY);
				String style = panel.getAttributeValue(TAG_STYLE);;
				String id = panel.getAttributeValue(TAG_ID);
				PanelDef def = new PanelDef(key,style,id);
				fixedPanels.add(def);
			}
		}*/
		
		return this;
	}
	
	protected Section readSection (XMLElement sectionElement ) {
		if (sectionElement == null ||  sectionElement.isEmpty())  
			return null ; 
		Section section  = new Section();
		String showinTable = sectionElement.getAttributeValue(TAG_SHOWINTABLE);
		String id = sectionElement.getAttributeValue(TAG_ID);
		String style = sectionElement.getAttributeValue(TAG_STYLE);
		section.setId(id);
		section.setStyle(style);
		if("true".equalsIgnoreCase(showinTable)) {
			section.setShowIntable(true);
			XMLElement rows = sectionElement.getFirstChildElement(TAG_NOROWS) ;
			XMLElement cols = sectionElement.getFirstChildElement(TAG_NOCOLS) ;
			String tabStyle = sectionElement.getAttributeValue(TAG_TABSTYLE) ;
			if ( !Utils.isNullString(tabStyle))
				section.setTabStyle(tabStyle);
			if (rows!= null &&  Utils.isPositiveInt(rows.getValue())) 
				section.setNoRows(Integer.parseInt(rows.getValue()));
			else
				section.setNoRows(-1);
			if (cols != null && Utils.isPositiveInt(cols.getValue())) 
				section.setNoCols(Integer.parseInt(cols.getValue()));
			else
				section.setNoCols(-1);
				
		}
		List<XMLElement> panelList = sectionElement.getChildElements(TAG_PANEL);
		if (!Utils.isNullList(panelList)) {
			for (XMLElement panel : panelList) {
				String key = panel.getAttributeValue(TAG_KEY);
				String panelId = panel.getAttributeValue(TAG_ID);
				String tagStyle = panel.getAttributeValue(TAG_STYLE);
				PanelDef panelDef = new PanelDef(key,tagStyle,panelId);
				section.addFixedPanel(panelDef);
			}
		}
		
		return section;
	}


/*
	public TemplateType generateTemplate(Element  templateElement) {
  		NodeList renderers = templateElement.getElementsByTagName(TAG_RENDERERS);
    	  for (int i = 0 ;  i < renderers.getLength() ; i ++ ) {
    	  Element renderersElement = (Element) renderers.item(i);
    	  NodeList renderer =  renderersElement.getElementsByTagName(TAG_RENDERER);
    	  for (int j = 0 ; j < renderer.getLength() ; j ++ ) {
    		  Element rend = (Element)renderer.item(j);
    		  String type = Utils.getNodeValuefromXML(rend, TAG_TYPE);
        	  if ("HTML".equalsIgnoreCase(type)) {
        		  List<XMLElement> styleSheetsXML = renderer.getElementsByTagName(TAG_STYLE_SHEET) ;
    			  styleSheetsXML.forEach(styeSheetXML ->  { 
    				  addStyleSheet(styeSheetXML.getValue());
    			  });
        	  }  
    	  }
    	}
    	return this  ;
    }
	
*/	




	public class Section  {
		boolean showIntable;
		int noRows ;  
		int noCols ;
		String id ;
		String style ;
		String tabStyle ;
		
		
	    List<PanelDef> fixedPanels; 
	    List<UIElement> fixedElements;
		
		
		public boolean isShowIntable() {
			return showIntable;
		}
		public void setShowIntable(boolean showIntable) {
			this.showIntable = showIntable;
		}
		public int getNoRows() {
			return noRows;
		}
		public void setNoRows(int noRows) {
			this.noRows = noRows;
		}
		public int getNoCols() {
			return noCols;
		}
		public void setNoCols(int noCols) {
			this.noCols = noCols;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		
		
		
		
		public String getTabStyle() {
			return tabStyle;
		}
		public void setTabStyle(String tabStyle) {
			this.tabStyle = tabStyle;
		}
		public void addFixedElement(UIElement fixedElement) {
			if (fixedElements == null ) {
				fixedElements = new ArrayList<UIElement> ();
			}
			fixedElements.add(fixedElement);
		}
		public List<UIElement> getFixedElements() {
			return fixedElements;
		}
		public void setFixedElements(List<UIElement> fixedElements) {
			this.fixedElements = fixedElements;
		}
		
		public List<PanelDef> getFixedPanels() {
			return fixedPanels;
		}

		public void setFixedPanels(List<PanelDef> fixedPanels) {
			this.fixedPanels = fixedPanels;
		}
		
		public void addFixedPanel(PanelDef fixedPanel) {
			if (fixedPanels  ==  null)
				fixedPanels = new ArrayList<> () ;
			fixedPanels.add(fixedPanel);
		}
		
	}
}

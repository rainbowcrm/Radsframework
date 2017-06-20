package com.techtrade.rads.framework.ui.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.controller.abstracts.IExternalizeFacade;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIPanel;
import com.techtrade.rads.framework.ui.servlets.UIElementGenerator;
import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLDocument;
import com.techtrade.rads.framework.utils.XMLElement;

public enum AppConfig {
	
	APPCONFIG ;
	
	
	protected static final String TAG_PAGES = "Pages";
	protected static final String TAG_PAGE = "Page";
	protected static final String TAG_ACCESSCODE = "accessCode";
	protected static final String TAG_ID = "id";
	protected static final String TAG_KEY = "key";
	protected static final String TAG_DEF = "definition";
	protected static final String TAG_MODELCLASS = "modelclass";
	protected static final String TAG_AUTHREQUIRED = "authRequired";
	protected static final String TAG_LOOKUPS = "Lookups";
	protected static final String TAG_LOOKUP = "Lookup";
	protected static final String TAG_TYPE = "type";
	protected static final String TAG_LOOKUPSERVICE = "lookupservice";
	protected static final String TAG_LOOKUPPAGE = "lookuppage";
	protected static final String TAG_PANELS = "Panels";
	protected static final String TAG_PANEL = "Panel";
	protected static final String TAG_SHOWINDIV = "showInDiv";
	protected static final String TAG_ELEMENT = "Element";
	
	protected static final String TAG_AJAXSERVICES = "AjaxServices";
	protected static final String TAG_AJAXSERVICE = "AjaxService";
	protected static final String TAG_SERVICE = "service";
	protected static final String TAG_CLASS = "class";
	protected static final String TAG_PROPERTIES= "Properties";
	protected static final String TAG_APPURL= "appURL";
	protected static final String TAG_PORTALPREFIX= "portalPrefix";
	protected static final String TAG_GOOGLEGRAPHS= "useGoogleforGraphs";
	protected static final String TAG_BOOTSTRAPFOLDER= "bootstrapFolder";
	protected static final String TAG_EXTERNALIZEFACADE= "ExternalizeFacade";
	protected static final String TAG_EXTERNALIZECLASS= "class";
	
	/*AjaxServices
	AjaxService
	service
	class
	key*/ 
	
	
	Map <String,PageConfig> configMap = new HashMap<String,PageConfig>();
	Map <String,LookupConfig> lookupMap = new HashMap<String,LookupConfig >();
	Map <String, AjaxServiceConfig> ajaxServiceMap =  new HashMap<String, AjaxServiceConfig> ();
	String appURL ;
	String bootstrapFolder;
	Boolean useGoogleGraphs;
	String portalPrefix ;
	Map <String, PanelConfig> panelMap =  new HashMap<String, PanelConfig> ();
	IExternalizeFacade externalizeFacade ;
	
	public  void readDocument (XMLDocument doc) throws Exception{
		XMLElement conf = doc.getRootElement() ;
		XMLElement propElement = conf.getFirstChildElement(TAG_PROPERTIES);
		if (propElement != null ) { 
			appURL= propElement.getChildAttributeValue(TAG_APPURL);
			bootstrapFolder=propElement.getChildAttributeValue(TAG_BOOTSTRAPFOLDER);
			portalPrefix = propElement.getChildAttributeValue(TAG_PORTALPREFIX);
			String googleGraphs = propElement.getChildAttributeValue(TAG_GOOGLEGRAPHS);
			if(!Utils.isNullString(googleGraphs))
				useGoogleGraphs = Boolean.parseBoolean(googleGraphs);
		}
		
		XMLElement pages = conf.getFirstChildElement(TAG_PAGES);
		
		XMLElement externalizeFacadeXML = conf.getFirstChildElement(TAG_EXTERNALIZEFACADE);
		if (externalizeFacadeXML != null) {
			String externalizeClassXML = externalizeFacadeXML.getAttributeValue(TAG_EXTERNALIZECLASS);
			externalizeFacade =(IExternalizeFacade) Class.forName(externalizeClassXML).newInstance();
		}
		List<XMLElement> pageList   = pages.getChildElements(TAG_PAGE);
		for (XMLElement page :  pageList ) {
			String pageID = page.getAttributeValue(TAG_ID);
			String def =  page.getAttributeValue(TAG_DEF);
			String accessCode = page.getAttributeValue(TAG_ACCESSCODE);
			String modelClass = page.getAttributeValue(TAG_MODELCLASS);
			String auth = page.getAttributeValue(TAG_AUTHREQUIRED) ; 
			PageConfig config = new PageConfig();
			config.setConfigId(pageID);
			config.setDefinition(def);
			config.setModelClass(modelClass);
			config.setAccessCode(accessCode);
			if(!Utils.isNullString(auth) && "false".equalsIgnoreCase(auth) ) {
				config.setAuthRequired(false);  
			}
			configMap.put(pageID, config);
		}
		XMLElement lookups = conf.getFirstChildElement(TAG_LOOKUPS);
		List<XMLElement> lookupList   = lookups.getChildElements(TAG_LOOKUP);
		for (XMLElement lookup :  lookupList ) {
			String type = lookup.getAttributeValue(TAG_TYPE);
			String service = lookup.getAttributeValue(TAG_LOOKUPSERVICE);
			String lookuppage = lookup.getAttributeValue(TAG_LOOKUPPAGE);
			LookupConfig lookupConfig = new LookupConfig();
			lookupConfig.setType(type);
			lookupConfig.setService(service);
			lookupConfig.setLookupPage(lookuppage);
			lookupMap.put(type, lookupConfig);
		}
		
		XMLElement panels = conf.getFirstChildElement(TAG_PANELS) ;
 		List<XMLElement> panelList = panels.getChildElements(TAG_PANEL) ;
 		for (XMLElement panel : panelList) {
 			PanelConfig panelConfig = new PanelConfig();
 			String key =  panel.getAttributeValue(TAG_KEY) ;
 			String showInDiv = panel.getAttributeValue(TAG_SHOWINDIV);
 			panelConfig.setXmlElements(panel.getAllChildElements());
 			if ("true".equalsIgnoreCase(showInDiv))
 				panelConfig.setShowOnDiv(true);
 			panelMap.put(key, panelConfig);
 		}
 		
 		XMLElement ajaxServices = conf.getFirstChildElement(TAG_AJAXSERVICES) ;
 		List<XMLElement> ajaxServiceList = ajaxServices.getChildElements(TAG_AJAXSERVICE) ;
 		for (XMLElement ajaxService : ajaxServiceList) {
 			AjaxServiceConfig ajaxConfig = new AjaxServiceConfig();
 			String serviceClass = ajaxService.getAttributeValue(TAG_CLASS);
 			String serviceKey = ajaxService.getAttributeValue(TAG_SERVICE);
 			ajaxConfig.setServiceClass(serviceClass);
 			for (XMLElement childKey : ajaxService.getChildElements(TAG_KEY)) {
 				ajaxConfig.addKey(childKey.getValue());
 			}
 			ajaxServiceMap.put(serviceKey, ajaxConfig);
 		}
 		
	}
		
	    
	
	public AjaxServiceConfig getAjaxServiceConfig(String realPath, String serviceKey) throws Exception {
		if (Utils.isNullMap(ajaxServiceMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return ajaxServiceMap.get(serviceKey);
	}
	
	public PanelConfig getPanel(String realPath, String panelKey) throws Exception {
    	if (Utils.isNullMap(panelMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return panelMap.get(panelKey);
    }

	public PageConfig getPageConfig(String realPath, String pageID) throws Exception {
	if (Utils.isNullMap(configMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return configMap.get(pageID);
	}
	
	public String getAppURL(String realPath) throws Exception {
	if (Utils.isNullMap(configMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return appURL;
	}
	
	
	public String getPortalPrefix(String realPath) throws Exception {
		if (Utils.isNullMap(configMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return portalPrefix;
	}



	public boolean useGoogleGraphs(String realPath)throws Exception {
	if (Utils.isNullMap(configMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return useGoogleGraphs;
	}
	
	
		
	public String getBootstrapFolder(String realPath) throws Exception {
		if (Utils.isNullMap(configMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return bootstrapFolder;
	}



	public IExternalizeFacade getExternalizeFacade() {
		return externalizeFacade;
	}

	public void setExternalizeFacade(IExternalizeFacade externalizeFacade) {
		this.externalizeFacade = externalizeFacade;
	}



	public LookupConfig getLookupConfig(String realPath, String lookupType) throws Exception {
		if (Utils.isNullMap(lookupMap)) {
			XMLDocument doc = XMLDocument.parse(realPath + "/resources/config/RadsConfig.xml") ;
			readDocument(doc);
		}
		return lookupMap.get(lookupType);
	}
	
	

}

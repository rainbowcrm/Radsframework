package com.techtrade.rads.framework.ui.templates;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.utils.XMLDocument;

public class TemplateReader {

	protected static String TAG_PAGE =   "Page";
	protected static String TAG_TEMPLATE = "Template";
	protected static String TAG_TEMPLATE_TYPE = "type";

	
	public static TemplateReader INSTANCE = new TemplateReader ();
	
	public TemplateType readTemplate (String templateName)
	throws RadsException
	{
		try {
			XMLDocument document  = XMLDocument.parse(templateName);
			String templateClass = document.getRootElement().getAttributeValue(TAG_TEMPLATE_TYPE);
			TemplateType pageTemplate  = (TemplateType) Class.forName(templateClass).newInstance();
			return pageTemplate.generateTemplate(document.getRootElement());
		}catch(Exception cf) {
			cf.printStackTrace();
			throw new RadsException (cf.getMessage());
		}
	}
	
	
	
}

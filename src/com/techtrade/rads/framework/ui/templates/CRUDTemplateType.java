
package com.techtrade.rads.framework.ui.templates;


import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLElement;

public class CRUDTemplateType extends TemplateType{
	
	
	
	/**
	 * These fields might not be used .. 
	 */
	Section headerSection ;
	Section dataSection ;
	Section summarySection ;
	
	
	
	
	
	public Section getHeaderSection() {
		return headerSection;
	}

	public void setHeaderSection(Section headerSection) {
		this.headerSection = headerSection;
	}

	public Section getDataSection() {
		return dataSection;
	}

	public void setDataSection(Section dataSection) {
		this.dataSection = dataSection;
	}

	public Section getSummarySection() {
		return summarySection;
	}

	public void setSummarySection(Section summarySection) {
		this.summarySection = summarySection;
	}

	
	

	

	@Override
	public TemplateType generateTemplate(XMLElement templateElement) {
		super.generateTemplate(templateElement);
		
		/*XMLElement headerSection = templateElement.getFirstChildElement(TAG_HEADERSECTION);
		XMLElement dataSection = templateElement.getFirstChildElement(TAG_DATASECTION);
		XMLElement summarySection = templateElement.getFirstChildElement(TAG_SUMMARYSECTION);
		
		setHeaderSection(readSection(headerSection));
		setDataSection(readSection(dataSection));
		setSummarySection(readSection(summarySection));*/
		return this ;
	}
	
	/*
	@Override
	public Template generateTemplate(Element templateElement) {
		super.generateTemplate(templateElement);
		NodeList  dataSections = templateElement.getElementsByTagName(TAG_DATASECTION);
		Element dataSection =(Element) dataSections.item(0);
		String  showinTable =  dataSection.getAttribute(TAG_SHOWINTABLE);
		if ("true".equalsIgnoreCase(showinTable)) {
			setShowIntable(true);
		}
		

		setHeaderPanel(Utils.getNodeValuefromXML(dataSection,TAG_HEADERPANEL));
		setFooterPanel(Utils.getNodeValuefromXML(dataSection,TAG_FOOTERPANEL));
		setNoRows(Integer.parseInt(Utils.getNodeValuefromXML(dataSection,TAG_NOROWS)));
		setNoCols(Integer.parseInt(Utils.getNodeValuefromXML(dataSection,TAG_NOCOLS)));

		return this ;
	}*/
	
	

	
}

package com.techtrade.rads.framework.ui.config;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.utils.XMLElement;

public class PanelConfig {

	List <XMLElement> xmlElements;
	boolean showOnDiv = false;


	public List<XMLElement> getXmlElements() {
		return xmlElements;
	}

	public void setXmlElements(List<XMLElement> xmlElements) {
		this.xmlElements = xmlElements;
	}
	
	
	public void addXmlElement(XMLElement xmlElement)  {
		if (xmlElements == null ) {
			xmlElements = new ArrayList <XMLElement>();
		}
		xmlElements.add(xmlElement);
	}

	public boolean isShowOnDiv() {
		return showOnDiv;
	}

	public void setShowOnDiv(boolean showOnDiv) {
		this.showOnDiv = showOnDiv;
	}

	
}

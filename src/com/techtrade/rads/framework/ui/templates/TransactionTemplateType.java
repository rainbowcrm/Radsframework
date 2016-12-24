package com.techtrade.rads.framework.ui.templates;

import com.techtrade.rads.framework.ui.templates.TemplateType.Section;
import com.techtrade.rads.framework.utils.Utils;
import com.techtrade.rads.framework.utils.XMLElement;

public class TransactionTemplateType extends TemplateType{
	
	protected static final String TAG_DETAILSECTION = "DetailSection";
	protected static final String TAG_HEADERSECTION = "HeaderSection";
	protected static final String TAG_SUMMARYSECTION = "SummarySection";
	protected static final String TAG_SHOWINTABLE = "showinTable";
	protected static final String TAG_NOROWS = "noRows";
	protected static final String TAG_NOCOLS = "noCols";
	protected static final String TAG_TABSTYLE =  "tabStyle" ;
	
	
	Section headerSection ;
	Section detailSection ;
	Section summarySection ;
	
	
	public Section getHeaderSection() {
		return headerSection;
	}

	public void setHeaderSection(Section headerSection) {
		this.headerSection = headerSection;
	}

	

	
	public Section getDetailSection() {
		return detailSection;
	}

	public void setDetailSection(Section detailSection) {
		this.detailSection = detailSection;
	}

	public Section getSummarySection() {
		return summarySection;
	}

	public void setSummarySection(Section summarySection) {
		this.summarySection = summarySection;
	}

	/*protected Section readSection (XMLElement sectionElement ) {
		if (sectionElement == null ||  sectionElement.isEmpty())  
			return null ; 
		Section section  = new Section();
		String showinTable = sectionElement.getAttributeValue(TAG_SHOWINTABLE);
		if("true".equalsIgnoreCase(showinTable)) {
			section.setShowIntable(true);
			XMLElement rows = sectionElement.getFirstChildElement(TAG_NOROWS) ;
			XMLElement cols = sectionElement.getFirstChildElement(TAG_NOCOLS) ;
			XMLElement tabStyle = sectionElement.getFirstChildElement(TAG_TABSTYLE) ;
			if (tabStyle != null &&   !Utils.isNullString(tabStyle.getValue()))
				section.setTabStyle(tabStyle.getValue());
			if (rows!= null &&  Utils.isPositiveInt(rows.getValue())) 
				section.setNoRows(Integer.parseInt(rows.getValue()));
			else
				section.setNoRows(-1);
			if (cols != null && Utils.isPositiveInt(cols.getValue())) 
				section.setNoCols(Integer.parseInt(cols.getValue()));
			else
				section.setNoCols(-1);
				
		}
		XMLElement panel = sectionElement.getFirstChildElement(TAG_PANEL);
		//if (panel != null && !panel.isEmpty())
			//section.setPanel(panel.getValue());
		//return section;
	}*/

	@Override
	public TemplateType generateTemplate(XMLElement templateElement) {
		super.generateTemplate(templateElement);
		/*XMLElement headerSection = templateElement.getFirstChildElement(TAG_HEADERSECTION);
		XMLElement detailSection = templateElement.getFirstChildElement(TAG_DETAILSECTION);
		XMLElement summarySection = templateElement.getFirstChildElement(TAG_SUMMARYSECTION);
		
		setHeaderSection(readSection(headerSection));
		setDetailSection(readSection(detailSection));
		setSummarySection(readSection(summarySection));*/
		return this ;
	}


}

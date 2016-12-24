package com.techtrade.rads.framework.ui.templates;

import com.techtrade.rads.framework.utils.XMLElement;

public class ListTemplateType extends TemplateType{
	
	protected static final String TAG_LISTSECTION = "ListSection";
	protected static final String TAG_FILTERSECTION = "FilterSection";
	protected static final String TAG_HEADERPANEL = "HeaderPanel";
	protected static final String TAG_SUMMARYPANEL = "summaryPanel";
	protected static final String TAG_NOROWS = "noRows";
	protected static final String TAG_NOCOLS = "noCols";
	protected static final String TAG_COLSTYLE = "Colstyle";
	protected static final String TAG_FORM ="form";
	protected static final String TAG_NAME ="name";
	protected static final String TAG_PAGENUMFLD ="pageNumberfield";
	protected static final String TAG_HIDEPGNUM ="hidePageNumber";
	protected static final String TAG_FIXEDACTIONFLD ="fixedActionField";
	protected static final String TAG_UNIQUEKEYSEPERATOR ="uniquekeyseperator";
	
	private int noRows;
	private String oddRowStyle ;
	private String evenRowStyle;
	private String titleRowStyle;
	private String titleRowSpanStyle;
	private String formName ; 
	private String pageNumberfield;
	private String uniqueKeySeperator;
	
	private boolean hidePageNumberfield;
	
	Section listSection ;
	Section filterSection ;


	public Section getFilterSection() {
		return filterSection;
	}

	public void setFilterSection(Section filterSection) {
		this.filterSection = filterSection;
	}

	public Section getListSection() {
		return listSection;
	}

	public void setListSection(Section listSection) {
		this.listSection = listSection;
	}

	
	public int getNoRows() {
		return noRows;
	}

	public void setNoRows(int noRows) {
		this.noRows = noRows;
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
	
	public String getTitleRowStyle() {
		return titleRowStyle;
	}

	public void setTitleRowStyle(String titleRowStyle) {
		this.titleRowStyle = titleRowStyle;
	}
	public String getTitleRowSpanStyle() {
		return titleRowSpanStyle;
	}
	public void setTitleRowSpanStyle(String titleRowSpanStyle) {
		this.titleRowSpanStyle = titleRowSpanStyle;
	}

	@Override
	public TemplateType generateTemplate(XMLElement templateElement) {
		super.generateTemplate(templateElement);
		XMLElement sectionsElement = templateElement.getFirstChildElement(TAG_SECTIONS);
		XMLElement coreSection = sectionsElement.getFirstChildElement(TAG_CORESECTION);
		XMLElement ListSection = coreSection.getFirstChildElement(TAG_LISTSECTION);
		listSection = readSection(ListSection);
		XMLElement FilterSection = coreSection.getFirstChildElement(TAG_FILTERSECTION);
		filterSection = readSection(FilterSection);
		XMLElement form = ListSection.getFirstChildElement(TAG_FORM);
		XMLElement rows = ListSection.getFirstChildElement(TAG_NOROWS);
		XMLElement colStyle = ListSection.getFirstChildElement(TAG_COLSTYLE);
		XMLElement oddRowStyle = colStyle.getFirstChildElement(TAG_ODDROW);
		XMLElement evenRowStyle = colStyle.getFirstChildElement(TAG_EVENROW);
		XMLElement titleRowStyle =colStyle.getFirstChildElement(TAG_TITLEROW);
		XMLElement titleRowSpanStyle =colStyle.getFirstChildElement(TAG_TITLEROWSPAN);
		XMLElement uniqueKeySep = ListSection.getFirstChildElement(TAG_UNIQUEKEYSEPERATOR);
		if (uniqueKeySep != null )
			setUniqueKeySeperator(uniqueKeySep.getValue());
		setOddRowStyle(oddRowStyle.getValue());
		setEvenRowStyle(evenRowStyle.getValue());
		setTitleRowStyle(titleRowStyle.getValue());
		setTitleRowSpanStyle(titleRowSpanStyle.getValue());
		setNoRows(Integer.parseInt(rows.getValue()));
		setFormName(form.getAttributeValue(TAG_NAME));
		setPageNumberfield(form.getAttributeValue(TAG_PAGENUMFLD));
		setHidePageNumberfield(Boolean.valueOf(form.getAttributeValue(TAG_HIDEPGNUM)));
		
		return this ;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getPageNumberfield() {
		return pageNumberfield;
	}

	public void setPageNumberfield(String pageNumberfield) {
		this.pageNumberfield = pageNumberfield;
	}

	public boolean isHidePageNumberfield() {
		return hidePageNumberfield;
	}

	public void setHidePageNumberfield(boolean hidePageNumberfield) {
		this.hidePageNumberfield = hidePageNumberfield;
	}

	public String getUniqueKeySeperator() {
		return uniqueKeySeperator;
	}

	public void setUniqueKeySeperator(String uniqueKeySeperator) {
		this.uniqueKeySeperator = uniqueKeySeperator;
	}

	
	

}

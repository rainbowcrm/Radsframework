package com.techtrade.rads.framework.ui.constants;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UILeftNav;
import com.techtrade.rads.framework.ui.components.UIMainDataPanel;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.controls.UITabSet;

public enum RadsStyles {
	
	DEFAULTHTMLSTYLE;
	
	public static final String STYLESHEET = "./css/display.css";
	public static final String LISTSTYLESHEET = "./css/listPage.css";
	public static final String CRUDSTYLESHEET = "./css/CRUDPage.css";
	public static final String TAB_TABSET = "tabset";
	public static final String TABLE_SUBTITLE = "subtitle";
	public static final String TABLE_SUMMARY = "summary";
	public static final String LEFT_NAV = "leftnav";
	public static final String MAIN_SECTION = "main";
	
	
	public static final String TR = "tabset";
	public static final String TD = "tabset";
	public static final String TH = "tabset";
	public static final String TABLEHEAD = "tabhead";
	
	public String getStyleSheet() {
		return STYLESHEET;
	}
	
	

}

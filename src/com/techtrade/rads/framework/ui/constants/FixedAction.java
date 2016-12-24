package com.techtrade.rads.framework.ui.constants;

public enum FixedAction {
	
	NAV_FIRSTPAGE , NAV_PREVPAGE  , NAV_NEXTPAGE , NAV_LASTPAGE , 
	ACTION_CREATE,  ACTION_READ ,  	ACTION_UPDATE, ACTION_DELETE , ACTION_CANCELOPERATION, 
	ACTION_CLOSE, ACTION_GOEDITMODE  , ACTION_GOADDMODE, ACTION_GOVIEWMODE , ACTION_PRINT,
	ACTION_PLAINSUBMIT ,CLOSELOOKUPWINDOW ,CANCELWINDOW,CLOSELOOKUPDIALOG ,CANCELDIALOG,
	ACTION_PAGEFORWARD, ACTION_ADDTABLEROW, ACTION_DELETETABLEROW,
	ACTION_SAVEFILTER,ACTION_CLEARFILTER,ACTION_APPLYFILTER,ACTION_LOADFILTERS,ACTION_RELOAD;

	
/*	private boolean showWaring;
	private String warningMessage;*/
	private String validateFunc;

	public static FixedAction getFixedAction(String action ) { 
		 switch (action ) {
		 case "FixedAction.NAV_FIRSTPAGE": return NAV_FIRSTPAGE ;
		 case "FixedAction.NAV_PREVPAGE": return NAV_PREVPAGE ;
		 case "FixedAction.NAV_NEXTPAGE": return NAV_NEXTPAGE ;
		 case "FixedAction.NAV_LASTPAGE": return NAV_LASTPAGE ;
		 case "FixedAction.ACTION_PAGEFORWARD": return ACTION_PAGEFORWARD ;
		 case "FixedAction.ACTION_CREATE": return ACTION_CREATE ;
		 case "FixedAction.ACTION_UPDATE": return FixedAction.ACTION_UPDATE ;
		 case "FixedAction.ACTION_DELETE": return ACTION_DELETE ;
		 case "FixedAction.ACTION_CANCELOPERATION": return ACTION_CANCELOPERATION ;
		 case "FixedAction.ACTION_GOVIEWMODE": return ACTION_GOVIEWMODE ;
		 case "FixedAction.ACTION_GOEDITMODE": return ACTION_GOEDITMODE ;
		 case "FixedAction.ACTION_GOADDMODE": return ACTION_GOADDMODE ;
		 case "FixedAction.ACTION_ADDTABLEROW": return ACTION_ADDTABLEROW ;
		 case "FixedAction.ACTION_DELETETABLEROW": return ACTION_DELETETABLEROW ;
		 case "FixedAction.ACTION_FILTERAPPLY": return ACTION_APPLYFILTER ;
		 case "FixedAction.ACTION_FILTERCLEAR": return ACTION_CLEARFILTER;
		 case "FixedAction.ACTION_FILTERSAVE": return ACTION_SAVEFILTER;
		 case "FixedAction.ACTION_PLAINSUBMIT": return ACTION_PLAINSUBMIT;
		 case "FixedAction.CLOSELOOKUPWINDOW": return CLOSELOOKUPWINDOW;
		 case "FixedAction.CANCELWINDOW": return CANCELWINDOW;
		 case "FixedAction.CLOSELOOKUPDIALOG": return CLOSELOOKUPDIALOG;
		 case "FixedAction.CANCELDIALOG": return CANCELDIALOG;
		 case "FixedAction.ACTION_RELOAD": return ACTION_RELOAD;
		 case "FixedAction.ACTION_READ": return ACTION_READ;
		 case "FixedAction.ACTION_PRINT" : return ACTION_PRINT;
		 }
		 
		 return null ;
	 }
	 
	 public static String getFixedActionString(FixedAction action ) { 
		 switch (action ) {
		 case  NAV_FIRSTPAGE: return "FixedAction.NAV_FIRSTPAGE" ;
		 case  NAV_PREVPAGE : return "FixedAction.NAV_PREVPAGE"   ;
		 case NAV_NEXTPAGE : return "FixedAction.NAV_NEXTPAGE";
		 case NAV_LASTPAGE : return  "FixedAction.NAV_LASTPAGE";
		 case ACTION_PAGEFORWARD: return "FixedAction.ACTION_PAGEFORWARD";  // Client Side
		 case ACTION_CREATE: return "FixedAction.ACTION_CREATE";
		 case ACTION_UPDATE: return "FixedAction.ACTION_UPDATE";
		 case ACTION_DELETE: return  "FixedAction.ACTION_DELETE";
		 case ACTION_CANCELOPERATION :return  "FixedAction.ACTION_CANCELOPERATION";
		 case ACTION_GOVIEWMODE: return "FixedAction.ACTION_GOVIEWMODE";
		 case ACTION_GOEDITMODE: return "FixedAction.ACTION_GOEDITMODE";
		 case ACTION_GOADDMODE: return "FixedAction.ACTION_GOADDMODE";
		 case ACTION_DELETETABLEROW:return  "FixedAction.ACTION_DELETETABLEROW";
		 case ACTION_ADDTABLEROW:  return "FixedAction.ACTION_ADDTABLEROW";
		 case ACTION_APPLYFILTER:return  "FixedAction.ACTION_FILTERAPPLY"  ;
		 case ACTION_CLEARFILTER:  return "FixedAction.ACTION_FILTERCLEAR";
		 case ACTION_SAVEFILTER: return "FixedAction.ACTION_FILTERSAVE";
		 case ACTION_PLAINSUBMIT: return "FixedAction.ACTION_PLAINSUBMIT";
		 case CLOSELOOKUPWINDOW: return "FixedAction.CLOSELOOKUPWINDOW";
		 case CANCELWINDOW: return "FixedAction.CANCELWINDOW";
		 case CLOSELOOKUPDIALOG: return "FixedAction.CLOSELOOKUPDIALOG";
		 case CANCELDIALOG: return "FixedAction.CANCELDIALOG";
		 case ACTION_RELOAD: return "FixedAction.ACTION_RELOAD";
		 case ACTION_READ: return "FixedAction.ACTION_READ";
		 case ACTION_PRINT: return "FixedAction.ACTION_PRINT";
		 } 
		 return null ;
	 }

	/*public boolean isShowWaring() {
		return showWaring;
	}
	public void setShowWaring(boolean showWaring) {
		this.showWaring = showWaring;
	}
	public String getWarningMessage() {
		return warningMessage;
	}
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}*/
	public String getValidateFunc() {
		return validateFunc;
	}
	public void setValidateFunc(String validateFunc) {
		this.validateFunc = validateFunc;
	}
	
	 
	 
}
 
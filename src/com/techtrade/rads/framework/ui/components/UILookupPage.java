package com.techtrade.rads.framework.ui.components;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.ui.abstracts.ILookupService;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.UIForm.METHOD;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsConstants;
import com.techtrade.rads.framework.ui.constants.RadsStyles;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UIHidden;
import com.techtrade.rads.framework.ui.controls.UIList;
import com.techtrade.rads.framework.ui.controls.UIText;
import com.techtrade.rads.framework.utils.Utils;

public class UILookupPage extends UIPage{
	
	

	ILookupService lookupSevice;
	//UIList lookupList ;
	UIForm form ;
	String searchControl ;
	String searchValue ;
	String listControl;
	
	int noRecords = -1;
	int from ;
	/** can be removed **/
	String lookupType; 
	String parentControl; 
	String dialogId;
	
	public UILookupPage() {
	}
	
	public UILookupPage(String lookupType) {
		this.lookupType = lookupType;
	}
	/*public UIList getLookupList() {
		return lookupList;
	}
	public void setLookupList(UIList lookupList) {
		this.lookupList = lookupList;
	}*/
	public UILookupPage(ILookupService lookupSevice) {
		this.lookupSevice = lookupSevice;
	}
	public ILookupService getLookupSevice() {
		return lookupSevice;
	}
	public void setLookupSevice(ILookupService lookupSevice) {
		this.lookupSevice = lookupSevice;
	}
	public Map<String, String> lookupValues( String searchString ,int from, int noRecords) {
		return lookupSevice.lookupData(null,searchString, from, noRecords,null,null);
	}
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
	}

	public int getNoRecords() {
		if (noRecords == -1 )
			return  RadsConstants.DEFAULT.getLookupListSize();
		else
		   return noRecords;
	}
	
	




	public void setNoRecords(int noRecords) {
		this.noRecords = noRecords;
	}


	public int getFrom() {
		return from;
	}


	public void setFrom(int from) {
		this.from = from;
	}



	public String getLookupType() {
		return lookupType;
	}
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	public String getParentControl() {
		return parentControl;
	}
	public void setParentControl(String parentControl) {
		this.parentControl = parentControl;
	}
	@Override
	public PageResult applyFixedAction() throws Exception {
		return null;
	}
	@Override
	public PageResult submit() {
		return null;
	}
	
	@Override
	public PageResult submit(String submitAction) throws Exception {
		return null;
	}
	@Override
	public List<UIElement> getInputElements() {
		List<UIElement> inputElements = new ArrayList<> ();
		if (form != null) {
			return super.getFormElements(form);
		}
		return inputElements;
	}

	public String getSearchControl() {
		return searchControl;
	}

	public void setSearchControl(String searchControl) {
		this.searchControl = searchControl;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getListControl() {
		return listControl;
	}

	public void setListControl(String listControl) {
		this.listControl = listControl;
	}
	
	@Deprecated
	public void applyListValues(List<String> lstValues) {
		List<UIElement > elements = this.getInputElements();
		for(UIElement element : elements) {
			if(element.getControl() != null && listControl.equalsIgnoreCase(element.getControl().getId())) {
			  UIList lstOptions = (UIList) element.getControl() ;
			  lstOptions.setOptionsAsList(lstValues);
			  if (Utils.isNullString(dialogId)) {
				  String dblClick = "closeLookupWindow('" + this.getParentControl() + "','"+ this.getListControl()  +"');";
				  lstOptions.setDblClickJS(dblClick);
			  }else {
				  String dblClick = "closeLookupDialog('"+ dialogId +"','" + this.getParentControl() + "','"+ this.getListControl()  +"');";
				  lstOptions.setDblClickJS(dblClick);
			  }
			}
		}
	}

	public void applyMapValues(Map<String,String> mapValues) {
		List<UIElement > elements = this.getInputElements();
		for(UIElement element : elements) {
			if(element.getControl() != null && listControl.equalsIgnoreCase(element.getControl().getId())) {
			  UIList lstOptions = (UIList) element.getControl() ;
			  lstOptions.setOptions(mapValues);
			  if (Utils.isNullString(dialogId)) {
				  String dblClick = "closeLookupWindow('" + this.getParentControl() + "','"+ this.getListControl()  +"');";
				  lstOptions.setDblClickJS(dblClick);
			  }else {
				  String dblClick = "closeLookupDialog('"+ dialogId +"','" + this.getParentControl() + "','"+ this.getListControl()  +"');";
				  lstOptions.setDblClickJS(dblClick);
			  }
			}
		}
	}


	
	
	public String getDialogId() {
		return dialogId;
	}

	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
	
    
    
}

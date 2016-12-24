package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult.Result;
import com.techtrade.rads.framework.ui.abstracts.PageForward;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsStyles;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.utils.Utils;

public  class UICRUDPage extends UIPage {

	 ModelObject object;
	 List<UIElement> headerElements = new ArrayList<UIElement>();
	 List<UIElement> footerElements = new ArrayList<UIElement>();
	 List<UIFixedPanel> panels;
	 UIForm form ;
	 
	 
	
	

	@Override
	public PageResult submit() throws Exception {
			// TODO Auto-generated method stub
		return null;
	}




	@Override
	public PageResult applyFixedAction() throws Exception {
		FixedAction fixedAction= getFixedAction() ;
		PageResult result = new PageResult();
		if (fixedAction != null && (fixedAction == FixedAction.ACTION_CREATE || fixedAction == FixedAction.ACTION_UPDATE ||
				fixedAction == FixedAction.ACTION_DELETE)){
			CRUDController objController = ((CRUDController)getViewController());
			List<RadsError> errors = null;
			if (fixedAction == FixedAction.ACTION_CREATE)			
				errors = objController.validateforCreate() ;
			else if (fixedAction == FixedAction.ACTION_UPDATE) 
				errors = objController.validateforUpdate() ;
			else 
				errors = objController.validateforDelete();
			if (Utils.isNullList(errors)) {
				if (fixedAction == FixedAction.ACTION_CREATE)
					result =objController.create();
				else  if (fixedAction == FixedAction.ACTION_UPDATE)
					result = objController.update();
				else
					result =objController.delete() ;
				if (!result.hasErrors() && result.getResult() != PageResult.Result.FAILURE) {
					String fixedActionParam = this.getFixedActionParam() ;
					if (!Utils.isNullList(getPageForwards())) {
						for (PageForward forward : getPageForwards() ) {
							if (forward.getKey().equals(fixedActionParam)) {
								result.setNextPageKey(forward.getLink());
							}
						}
					}
					result.setResult(Result.SUCCESS);
					objController.setReadMode();
				}else {
					if (fixedAction == FixedAction.ACTION_CREATE)
						objController.setCreateMode();
					else if (fixedAction == FixedAction.ACTION_UPDATE)
						objController.setUpdateMode();
					else if (fixedAction == FixedAction.ACTION_DELETE)
						objController.setDeleteMode();
				}
			} else {
				result.setResult(Result.FAILURE);
				result.setErrors(errors);
				if (fixedAction == FixedAction.ACTION_CREATE)
					objController.setCreateMode();
				else if (fixedAction == FixedAction.ACTION_UPDATE)
					objController.setUpdateMode();
				else if (fixedAction == FixedAction.ACTION_DELETE)
					objController.setDeleteMode();
			}
		}else if (fixedAction == FixedAction.ACTION_GOEDITMODE  || fixedAction == FixedAction.ACTION_GOVIEWMODE) {
			object = ((CRUDController)getViewController()).populateFullObjectfromPK(((CRUDController)getViewController()).getObject());
			result.setObject(object);
			result.setResult(Result.SUCCESS);
		} else if (fixedAction == FixedAction.ACTION_READ ) {
			CRUDController objController = ((CRUDController)getViewController());
			objController.read();
			result.setObject(objController.getObject());
		}
		
		return result;
	}




	public UICRUDPage(){
	}
	 
			 
	

	public UIForm getForm() {
		return form;
	}


	public void setForm(UIForm form) {
		this.form = form;
	}


	public UICRUDPage(ModelObject object) {
		
		this.object = object;
	}

	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}

	public List<UIElement> getHeaderElements() {
		return headerElements;
	}

	public void setHeaderElements(List<UIElement> headerElements) {
		this.headerElements = headerElements;
	}
	
	public void addHeaderElement (UIElement element) {
		headerElements.add(element);
	}

	public List<UIElement> getFooterElements() {
		return footerElements;
	}

	public void setFooterElements(List<UIElement> footerElements) {
		this.footerElements = footerElements;
	}
	
	public void addFooterElement (UIElement element) {
		footerElements.add(element);
	}

	

	public List<UIFixedPanel> getPanels() {
		return panels;
	}


	public void setPanels(List<UIFixedPanel> panels) {
		this.panels = panels;
	}
	
	public void addPanel(UIFixedPanel panel ) {
		if (panels == null ) {
			panels = new ArrayList<UIFixedPanel>();
		}
		panels.add(panel);
	}




	@Override
	public List<UIElement> getInputElements() {
		List<UIElement> inputElements = new ArrayList<> ();
		if (form != null) {
			return super.getFormElements(form);
		}
		
		return inputElements;
	}

	
	



	


	
	
	
	
}

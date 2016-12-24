package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.GeneralController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;

public class UIGeneralPage extends UIPage{
	
	 UIForm form ;
	 ModelObject object;

	 
	 
	 
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}

	@Override
	public PageResult submit() throws Exception {
		return ((GeneralController)getViewController()).submit(object);
	}

	@Override
	public PageResult applyFixedAction() throws Exception {
		FixedAction fixedAction= getFixedAction() ;
		if (fixedAction != null && (fixedAction == FixedAction.ACTION_CREATE || fixedAction == FixedAction.ACTION_UPDATE)) {
			return((GeneralController)getViewController()).save(object);
		} else if (fixedAction != null && fixedAction == FixedAction.ACTION_READ) {
			return((GeneralController)getViewController()).read(object);
		}else if (fixedAction != null && fixedAction == FixedAction.ACTION_DELETE) {
			return((GeneralController)getViewController()).delete(object);
		}else 
			return submit();
	//	getViewController().
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
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

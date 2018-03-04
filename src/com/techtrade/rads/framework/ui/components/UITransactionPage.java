package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.GeneralController;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult.Result;
import com.techtrade.rads.framework.ui.abstracts.PageForward;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.utils.Utils;

public class UITransactionPage extends UIPage {
	
	UIForm form ;
	ModelObject object;

	@Override
	public PageResult submit() throws Exception {
		TransactionController objController = ((TransactionController)getViewController());
		object = objController.getObject();
		return ((TransactionController)getViewController()).submit(object);
	}
	
	@Override
	public PageResult submit(String submitAction) throws Exception {
		TransactionController objController = ((TransactionController)getViewController());
		object = objController.getObject();
		return ((TransactionController)getViewController()).submit(object,submitAction);
	}


	@Override
	public PageResult applyFixedAction() throws Exception {
		FixedAction fixedAction= getFixedAction() ;
		PageResult result = new PageResult();
		if (fixedAction != null && (fixedAction == FixedAction.ACTION_CREATE || fixedAction == FixedAction.ACTION_UPDATE )){
			TransactionController objController = ((TransactionController)getViewController());
			List<RadsError> adaptErrors =  objController.adaptfromUI(objController.getObject());
			if (!Utils.isNullList(adaptErrors)) {
				result.setResult(Result.FAILURE);
				result.setErrors(adaptErrors);
				if (fixedAction == FixedAction.ACTION_CREATE)
					objController.setCreateMode();
				else
					objController.setUpdateMode();
				return result;
			}
			List<RadsError> errors ;
			if (fixedAction == FixedAction.ACTION_CREATE)
				errors = objController.validateforCreate() ;
			else
				errors  = objController.validateforUpdate() ;
			
			if (Utils.isNullList(errors)) {
				if (fixedAction == FixedAction.ACTION_CREATE)
					objController.create();
				else if (fixedAction == FixedAction.ACTION_UPDATE)
					objController.update();
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
			} else {
				result.setResult(Result.FAILURE);
				result.setErrors(errors);
				objController.setCreateMode();
			}
		} else if (fixedAction == FixedAction.ACTION_PLAINSUBMIT  ) {  
			TransactionController objController = ((TransactionController)getViewController());
			object = objController.getObject();
			String fixedActionParam = this.getFixedActionParam() ;
			if (Utils.isNullString(fixedActionParam)) {
				result =((TransactionController)getViewController()).submit(object);
			}else {
				result =((TransactionController)getViewController()).submit(object,fixedActionParam);
			}
		} else if (fixedAction != null && fixedAction == FixedAction.ACTION_READ) {
			return((TransactionController)getViewController()).read();
		}else if (fixedAction == FixedAction.ACTION_PRINT  ) { 
			object = ((TransactionController)getViewController()).populateFullObjectfromPK(((TransactionController)getViewController()).getObject());
			TransactionController objController = ((TransactionController)getViewController());
			objController.setObject(object);
			objController.print() ;
		}else if (fixedAction == FixedAction.ACTION_GOEDITMODE  || fixedAction == FixedAction.ACTION_GOVIEWMODE) {
			object = ((TransactionController)getViewController()).populateFullObjectfromPK(((TransactionController)getViewController()).getObject());
			((TransactionController)getViewController()).adapttoUI(object);
			result.setObject(object);
			result.setResult(Result.SUCCESS);
		}
		
		return result;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
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

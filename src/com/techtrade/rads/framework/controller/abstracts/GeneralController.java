package com.techtrade.rads.framework.controller.abstracts;


import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public  abstract class GeneralController extends ViewController {
	
	protected ModelObject object ;
	
	public abstract PageResult submit(ModelObject object) ;
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	
	/**
	 * Can override if required
	 * @param object
	 */
	public  PageResult save (ModelObject object) {
		return new PageResult();
	}
	
	public  PageResult read (ModelObject object) {
		return new PageResult();
	}
	
	public  PageResult delete (ModelObject object) {
		return new PageResult();
	}
	
}

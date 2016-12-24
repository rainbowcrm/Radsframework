package com.techtrade.rads.framework.controller.abstracts;

import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.ViewController.Mode;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public abstract class CRUDController extends ViewController{
	
	protected ModelObject object;
	
	public CRUDController() {
		mode = Mode.CREATE ;
	}
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	
	
	public abstract List<RadsError> validateforCreate() ;
	public abstract List<RadsError> validateforUpdate() ;
	public abstract List<RadsError> validateforDelete() ;
	
	public abstract PageResult create (); 
	
	public abstract PageResult delete ();
	
	public abstract void read() ;
	
	public abstract PageResult update() ;

	public abstract ModelObject populateFullObjectfromPK(ModelObject objects) ;
	
}
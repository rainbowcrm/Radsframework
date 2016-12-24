package com.techtrade.rads.framework.controller.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.utils.Utils;

public abstract class DataSheetController extends ListController{
  
	protected List<ModelObject> objects;
	
	public DataSheetController() {
		setReadMode();
	}
	
	public List<ModelObject> getObjects() {
		return objects;
	}
	public void setObjects(List<ModelObject> objects) {
		this.objects = objects;
	}
	public void addObject(ModelObject object) {
		if (objects == null)
			objects = new ArrayList<ModelObject> ();
		this.objects.add(object);
	}

	public abstract List<RadsError> validateforCreate() ;
	
	public abstract List<RadsError> validateforUpdate() ;
	
	public  List<RadsError> validateforDelete() {
		return validateforDelete(objects);
	}
	
	
	public abstract PageResult create (); 
	
	public abstract PageResult delete ();
	
	public abstract void read() ;
	
	public abstract PageResult update() ;
	



	
	
}

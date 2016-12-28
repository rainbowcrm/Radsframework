package com.techtrade.rads.framework.controller.abstracts;



import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.ViewController.Mode;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public abstract class TransactionController  extends ViewController {
	protected ModelObject object ;
	

	public TransactionController() {
		mode = Mode.CREATE ;
	}
	
	public  PageResult submit(ModelObject object) {
		return new PageResult();
	}
	
	public  PageResult submit(ModelObject object,String actionParam) {
		return new PageResult();
	}
	
		
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}

	public abstract List<RadsError>  adaptfromUI(ModelObject modelObject) ;

	public abstract List<RadsError>  adapttoUI(ModelObject modelObject) ;
	
	public abstract List<RadsError> validateforCreate() ;
	
	public abstract List<RadsError> validateforCancel() ;
	
	public abstract List<RadsError> validateforUpdate() ;
	
	public abstract PageResult create (); 
	
	public abstract PageResult delete ();
	
	public abstract void read() ;
	
	public abstract PageResult update() ;
	
	public abstract PageResult print() ;
	
	public abstract ModelObject populateFullObjectfromPK(ModelObject objects) ;

	

}

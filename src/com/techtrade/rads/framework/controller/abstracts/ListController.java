package com.techtrade.rads.framework.controller.abstracts;

import java.util.List;
import java.util.Properties;

import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public abstract class ListController extends ViewController{
	
	int recordsPerPage ;
	
	
	
	public int getRecordsPerPage() {
		return recordsPerPage;
	}



	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public abstract PageResult print(List<ModelObject> objects) ;
	

	public abstract List<ModelObject> getData(int pageNumber,Filter filter);
	
	public abstract void saveFilter(Filter filter);
	
	public abstract int getTotalNumberofPages();
	
	
	public abstract PageResult delete(List<ModelObject> objects) ;
	
	
	public abstract PageResult submit(List<ModelObject> objects, String submitAction) ;
	
	public abstract List<RadsError> validateforDelete(List<ModelObject> objects) ;
	
	public abstract List<RadsError> validateforEdit(List<ModelObject> objects) ;
	
	public abstract PageResult goToEdit(List<ModelObject> objects) ;
	
	public abstract List<ModelObject> populateFullObjectfromPK(List<ModelObject> objects) ;
	
	
	

}

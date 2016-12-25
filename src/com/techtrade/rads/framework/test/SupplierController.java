package com.techtrade.rads.framework.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.context.RadsContext;
import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public class SupplierController extends CRUDController{

	
	
	
	@Override
	public ModelObject populateFullObjectfromPK(ModelObject objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRadsContext generateContext(String authToken) {
		return null;
	}

	@Override
	public IRadsContext generateContext(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new RadsContext();
	}

	@Override
	public List<RadsError> validateforCreate() {
	
		return null;
	}

	@Override
	public List<RadsError> validateforUpdate() {
		
		return null;
	}

	@Override
	public List<RadsError> validateforDelete() {
		
		return null;
	}

	@Override
	public PageResult create() {
		// TODO Auto-generated method stub
		Supplier supplier =(Supplier) object;
		System.out.println("supplier=" + supplier);
		return null;
		
	}

	@Override
	public PageResult delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Map <String, String > getCities() {
		Map<String, String> ans = new HashMap<String, String> ();
		ans.put("BNG", "Bangalore");
		ans.put("CHN", "Chennai");
		ans.put("HYD", "Hyderabad");
		ans.put("COK", "Cochin");
		ans.put("DLH", "New Delhi");
		ans.put("GRG", "Gurgaon");
		ans.put("NOI", "Noida");
		ans.put("MUM", "Mumbai");
		return ans; 
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResult update() {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	
	
	

}

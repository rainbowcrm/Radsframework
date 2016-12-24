package com.techtrade.rads.framework.test;

import java.util.ArrayList;
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

public class CustomerController extends CRUDController{

	
	
	@Override
	public IRadsContext generateContext(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRadsContext generateContext(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new RadsContext();
	}

	@Override
	public void init(HttpServletRequest request) {
		System.out.println("param names = " + request.getParameterNames());
	}

	public Map <String, String > getStates() {
		Map<String, String> ans = new HashMap<String, String> ();
		ans.put("KL", "Kerala");
		ans.put("KA", "Karnataka");
		ans.put("TL", "Telungana");
		ans.put("TN", "TamilNadu");
		ans.put("AP", "Andhra Pradesh");
		return ans; 
	}

	public Boolean shouldShow() {
		return true;
	}

	@Override
	public List<RadsError> validateforCreate() {
		return validateforUpdate(); 
	}

	@Override
	public List<RadsError> validateforUpdate() {
		List<RadsError> errors = new ArrayList<RadsError> ();
		Customer cust = (Customer)getObject();
		if(cust.getAge() < 18 ) {
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			errors.add(new RadsError("101","Age cannot be less than 18")) ;
			
		}
		return errors ;
	}

	@Override
	public List<RadsError> validateforDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult create() {
		System.out.println("Saving objectt=" + object);
		return null;
		
	}

	@Override
	public PageResult delete() {
		return null;
		
	}

	@Override
	public void read() {
		
		
	}

	@Override
	public PageResult update() {
		return null;
		
	}
	
	

	
}

package com.techtrade.rads.framework.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.context.RadsContext;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;

public class SalesOrderController extends TransactionController{

	@Override
	public PageResult read() {
		return null;
	}

	@Override
	public IRadsContext generateContext(HttpServletRequest request, HttpServletResponse response, UIPage page) {
		return null;
	}

	@Override
	public IRadsContext generateContext(String authToken, UIPage page) {
		return null;
	}

	@Override
	public PageResult print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelObject populateFullObjectfromPK(ModelObject objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RadsError> validateforUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult update() {
		// TODO Auto-generated method stub
		return null;
	}


	
	@Override
	public List<RadsError> adaptfromUI(ModelObject modelObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RadsError> adapttoUI(ModelObject modelObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RadsError> validateforCreate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RadsError> validateforCancel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult create() {
		System.out.println(object.toString());
		return null;
		
	}

	@Override
	public PageResult delete() {
		return null;
		
	}


	
	
	

}

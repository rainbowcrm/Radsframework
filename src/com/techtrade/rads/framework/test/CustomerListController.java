package com.techtrade.rads.framework.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.context.RadsContext;
import com.techtrade.rads.framework.controller.abstracts.ListController;
import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.components.SortCriteria;

public class CustomerListController extends ListController{

	
	
	@Override
	public PageResult print(List<ModelObject> objects) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IRadsContext generateContext(String authToken) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	@Override
	public void saveFilter(Filter filter) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public long getTotalNumberofRecords(Filter filter) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public IRadsContext generateContext(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new RadsContext();
	}

	@Override
	public void init(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.init(request);
	}


	@Override
	public List<ModelObject> getData(int pageNumber,Filter filter, SortCriteria sortCriteria) {
		List <ModelObject > customers =new ArrayList<ModelObject>();
		if (pageNumber == 1) {
			
			for (int i = 0 ; i < getRecordsPerPage() ; i ++ ) {
				String str = "FName" ;
				Customer cust = new Customer();
				cust.setId(100+ i);
				cust.setAge(i);
				cust.setFirstName(str  + i );
				cust.setLastName("LName" + i);
				cust.setBirthday(new java.util.Date());
				cust.setState("State" + i);
				cust.setSex("Male");
				customers.add(cust);
			}
		}else if (pageNumber == 2) {
			for (int i = 0 ; i < getRecordsPerPage() ; i ++ ) {
				String str = "CAP";
				Customer cust = new	 Customer();
				cust.setId(100+ i);
				cust.setAge(i);
				cust.setFirstName(str + i );
				cust.setLastName("SMALL" + i);
				cust.setBirthday(new java.util.Date());
				cust.setState("KL" + i);
				cust.setSex("Female");
				customers.add(cust);
			}
		} else if (pageNumber == 3) {
			for (int i = 0 ; i < getRecordsPerPage() ; i ++ ) {
				String str = "THREE";
				Customer cust = new Customer();
				cust.setId(200+ i);
				cust.setAge(i);
				cust.setFirstName(str + i );
				cust.setLastName("FOUR" + i);
				cust.setBirthday(new java.util.Date());
				cust.setState("IND" + i);
				cust.setSex("Female");
				customers.add(cust);
			}
		} else if (pageNumber == 4) {
			for (int i = 0 ; i < getRecordsPerPage() ; i ++ ) {
				Customer cust = new Customer();
				String str = "FIVE";
				cust.setId(200+ i);
				cust.setAge(i);
				cust.setFirstName(str + i );
				cust.setLastName("SIX" + i);
				cust.setBirthday(new java.util.Date());
				cust.setState("PAK" + i);
				cust.setSex("Male");
				customers.add(cust);
			}
		}
			
		
		return customers ;
	}

	@Override
	public int getTotalNumberofPages() {
		return 4;
	}

	
	
	
	@Override
	public PageResult delete(List<ModelObject> objects) {
		for (ModelObject id :objects ) {
			System.out.println("Deleting" +  id);
		}
		return null;
	}
	
	

	@Override
	public List<RadsError> validateforDelete(List<ModelObject> objects) {
			List<RadsError> errors = new ArrayList<RadsError> ();
			for (ModelObject obj : objects )  {
				Customer cust = (Customer) obj; 
				if (cust.getId() == 100 || cust.getId() == 115  )
					errors.add(new RadsError ("202", " 10 and 20 cannt be deleted"));
			}
			
			return errors;
	}

	@Override
	public PageResult submit(List<ModelObject> objects,String submitAction) {
		PageResult result = new PageResult();
		if ("promote".equalsIgnoreCase(submitAction) ) {
			for (ModelObject object : objects ) {
				Customer cust = (Customer) object ;
				if (cust.getId() == 111 ) {
					result.addError(new RadsError("333","unlucky nelson"));
				}else {
					System.out.println("promoting" + cust.getId());
					result.setNextPageKey("CSXML");
				}
			} 
		} else if ("moveToZone".equalsIgnoreCase(submitAction) ) {
			if ( objects.size() !=  1 ) {
				result.addError(new RadsError ("333","Only one row can be selected"));
			}
		}
		return result;
		
	}

	@Override
	public List<ModelObject> populateFullObjectfromPK(List<ModelObject> objects) {
		return objects;
	}

	
	
	@Override
	public List<RadsError> validateforEdit(List<ModelObject> objects) {
		return null;
	}


	@Override
	public PageResult goToEdit(List<ModelObject> objects) {
		
		PageResult result = new PageResult();
		if ( objects.size() !=  1 ) {
			result.addError(new RadsError ("333","Only one row can be selected for edit..."));
		} else {
			result.setNextPageKey("CSXML");
		}
		return result;
	}

	
	
	
	
	

}

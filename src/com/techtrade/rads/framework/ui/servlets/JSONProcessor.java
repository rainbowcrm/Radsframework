package com.techtrade.rads.framework.ui.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.GeneralController;
import com.techtrade.rads.framework.controller.abstracts.ListController;
import com.techtrade.rads.framework.controller.abstracts.TransactionController;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.filter.FilterNode;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.components.SortCriteria;
import com.techtrade.rads.framework.ui.components.UIGeneralPage;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.config.AppConfig;
import com.techtrade.rads.framework.ui.config.PageConfig;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsControlConstants;
import com.techtrade.rads.framework.utils.Utils;

public class JSONProcessor {

	 private static void populateContextParams(JSONObject contextParameters, IRadsContext pageContext)
	 {
		 if (contextParameters != null && pageContext != null ) {
			 Iterator itkeys = contextParameters.keys() ;
			 if (itkeys == null ) return ;
			 while(itkeys.hasNext()) {
				 String token = String.valueOf(itkeys.next()) ;
				 String value = contextParameters.optString(token);
				 pageContext.addProperty(token, value);
			 }
		 }
		 
	 }
	
	 public static void processRequest (HttpServletRequest request , HttpServletResponse response, ServletContext context ) 
	 {
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		    JSONTokener  tokener = new JSONTokener(jb.toString());
			JSONObject root = new JSONObject(tokener);
			String authToken =  root.optString("authToken");
			String pageID = root.optString("pageID");
			String modeKey  =root.optString("currentmode") ;
			String fixedActionStr = root.optString("fixedAction") ;
			String pageNumber = root.optString("hdnPage");
			String submitAction  = root.optString("submitAction");
			String sortField = root.optString(RadsControlConstants.SORT_FIELD);
			String sortDirection = root.optString(RadsControlConstants.SORT_DIRECTION);
			String selectedRecords = root.optString(RadsControlConstants.JSON_SELECTEDIDS);
			JSONObject contextParameters =root.optJSONObject("contextParameters") ;
			JSONObject json =root.optJSONObject("dataObject") ;
			JSONArray filterArray = root.optJSONArray("filter")	;
			ViewController.Mode initialMode = PageGenerator.getModeFromString(modeKey);
			PageConfig config = AppConfig.APPCONFIG.getPageConfig(context.getRealPath("/"), pageID);
			ModelObject object =PageGenerator.readObjectfromPageConfig(config);
			request.setAttribute("authToken", authToken);
			UIPage page = PageGenerator.getPagefromKey(config,object,request,initialMode,response,context);
			page.setPageKey(pageID);
			IRadsContext pageContext = page.getViewController().generateContext(authToken);
			populateContextParams(contextParameters,pageContext);
			if (page.getViewController() != null ) {
				page.getViewController().setContext(pageContext);
				if(json !=null && !Utils.isNullString(json.toString())) {
					object = object.instantiateObjectfromJSON(json.toString(), object.getClass().getName(), pageContext);
				}
				 if (page.getViewController() instanceof CRUDController || page.getViewController() instanceof TransactionController) {
					 if (page.getViewController() instanceof CRUDController) 
						 ((CRUDController)page.getViewController()).setObject(object);
					 else
						 ((TransactionController)page.getViewController()).setObject(object);
				 }else  if (page.getViewController() instanceof ListController && page instanceof UIListPage) {
					 if (Utils.isPositiveInt(pageNumber))
						 ((UIListPage) page).setPageNumber(Integer.parseInt(pageNumber));
					if(!Utils.isNullString(sortField)  && !Utils.isNullString(sortDirection)){ 
						SortCriteria sortCriteria = new SortCriteria(sortField,SortCriteria.DIRECTION.valueOf(sortDirection));
						((UIListPage) page).setSortCriteria(sortCriteria);
					}
					  if(!Utils.isNullString(selectedRecords)) 
					  {
						  List<String >selRows = new ArrayList<String> ();
						  StringTokenizer tokenizer = new StringTokenizer(selectedRecords,",");
						  while(tokenizer.hasMoreTokens()) {
							  selRows.add(tokenizer.nextToken()) ;
						  }
						  ((UIListPage) page).setSelectedRows(selRows);
					  }
					 writeListPage((UIListPage) page, fixedActionStr,response,authToken,filterArray);
					 return;
				 } else  if (page.getViewController() instanceof GeneralController){
					 ((GeneralController)page.getViewController()).setObject(object);
					 ((UIGeneralPage)page).setObject(object);
				 }
				 if (!Utils.isNull(fixedActionStr)) {
					 FixedAction fixedAction = FixedAction.getFixedAction(fixedActionStr);
					 page.setFixedAction(fixedAction);
					 PageResult result =  page.applyFixedAction() ;
					 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
						 object = result.getObject() ;
					 }
					 writeOutput(response, object, result.getErrors(), authToken, result);
				 }else {
					 PageResult result;
					 if( Utils.isNullString( submitAction)) {
						 result =  page.submit() ;
					 }else
						 result = page.submit(submitAction);
					 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
						 object = result.getObject() ;
					 }
					 writeOutput(response, object, result.getErrors(), authToken, result);
				 }
			}
			
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	 }
	 
	 private static void writeListPage (UIListPage page, String fixedActionStr,HttpServletResponse response , String authToken, JSONArray filterArray) throws Exception
	 {
		Filter filter = new Filter();
		 if (filterArray != null) {
			 for (int  i = 0 ; i  < filterArray.length() ; i++ ) {
				 JSONObject filterNode = (JSONObject)filterArray.get(i);
				 String field = filterNode.optString("field");
				 String operator = filterNode.optString("operator");
				 String value =  filterNode.optString("value");
				 FilterNode node = new FilterNode();
				 node.setField(field);
				 node.setValue(value);
				 if (">".equals(operator))
					node.setOperater(FilterNode.Operator.GREATER_THAN);
				else if (">=".equals(operator))
					node.setOperater(FilterNode.Operator.GREATER_THAN_EQUAL);
				else if ("<".equals(operator))
					node.setOperater(FilterNode.Operator.LESS_THAN);
				else if ("<=".equals(operator))
					node.setOperater(FilterNode.Operator.LESS_THAN_EQUAL);
				else if ("EQUALS".equals(operator))
					node.setOperater(FilterNode.Operator.EQUALS);
				else if ("IN".equals(operator))
					node.setOperater(FilterNode.Operator.IN);
				else if ("NOT IN".equals(operator))
					node.setOperater(FilterNode.Operator.NOT_IN);
				 filter.addNode(node);
			 }
		 }
		 page.setFilter(filter);
		 if (!Utils.isNull(fixedActionStr)) {
			 FixedAction fixedAction = FixedAction.getFixedAction(fixedActionStr);
			 page.setFixedAction(fixedAction);
			 PageResult result =  page.applyFixedAction() ;
			 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
				 writeOutput(response, page.getObjects(), result.getErrors(), authToken, result);
			 }
			 
		 }else {
			 PageResult result =  page.submit() ;
			 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
				 writeOutput(response, page.getObjects(), result.getErrors(), authToken, result);
			 }
		 }
		 
	 }
	 
	 private  static void writeOutput(HttpServletResponse response, List<ModelObject> objects , List<RadsError> errors, String authToken, PageResult result) {
		 response.setContentType("application/json");
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 try {
			 JSONObject json = new JSONObject();
			 json.put("authToken", authToken) ;
			 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
				 	json.put("result", "success") ;			
				 	json.put("availableRecords", result.getAvailableRecords());
				 	json.put("fetchedRecords", result.getFetchedRecords());
			 } else  {
				 	json.put("result", "failure") ;
				 	int index = 0 ;
				 	JSONArray array = new JSONArray();
				 	for (RadsError error : errors) {
				 		array.put(index ++ , error.getMessage()) ;
				 	}
			 }
			 JSONArray array = new JSONArray();
			 for (ModelObject object : objects) {
				 JSONObject objJSON = new JSONObject(object.toJSON());
				 array.put(objJSON);
			 }
			 json.put("dataObject", array ) ;
			 response.getWriter().write(json.toString());
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
		 
	 }
	 
	 private  static void writeOutput(HttpServletResponse response, ModelObject object , List<RadsError> errors, String authToken, PageResult result) {
		 response.setContentType("application/json");
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 try {
			 JSONObject json = new JSONObject();
			 json.put("authToken", authToken) ;
			 if (result.getResult().equals(TransactionResult.Result.SUCCESS)) {
				 	json.put("result", "success") ;			 
			 } else  {
				 	json.put("result", "failure") ;
				 	int index = 0 ;
				 	JSONArray array = new JSONArray();
				 	for (RadsError error : errors) {
				 		array.put(index ++ , error.getMessage()) ;
				 	}
			 }
			 JSONObject root = new JSONObject(object.toJSON());
			 json.put("dataObject", root ) ;
			 response.getWriter().write(json.toString());
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
		 
	 }
}

package com.techtrade.rads.framework.ui.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult;
import com.techtrade.rads.framework.utils.Utils;

public class PageResult extends TransactionResult{

	
	String nextPageKey; 
	// boolean redirectToNext ; // true - redirect ; false - forward response ;
	
	 public enum ResponseAction {
		 DEFAULT,FULLRELOAD, RELOADSTATICCONTS , NORELOAD , NEWPAGE, FILEDOWNLOAD, POPUPRESPONSE;
	 }  
	 
	 ResponseAction responseAction = ResponseAction.DEFAULT;
	
	
	public ResponseAction getResponseAction() {
		return responseAction;
	}

	public void setResponseAction(ResponseAction responseAction) {
		this.responseAction = responseAction;
	}

	public String getNextPageKey() {
		return nextPageKey;
	}

	public void setNextPageKey(String nextPageKey) {
		this.nextPageKey = nextPageKey;
	}

	/*public boolean isRedirectToNext() {
		return redirectToNext;
	}

	public void setRedirectToNext(boolean redirectToNext) {
		this.redirectToNext = redirectToNext;
	}*/

	public PageResult(Result result, List<RadsError> errors, ModelObject object) {
		super(result, errors, object);
	}
	public PageResult() {
		
	}
	public PageResult(TransactionResult transObject) {
		this(transObject.getResult(),transObject.getErrors(),transObject.getObject());
	}
	
		
	
}

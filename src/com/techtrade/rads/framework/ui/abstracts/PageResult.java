package com.techtrade.rads.framework.ui.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult;
import com.techtrade.rads.framework.utils.Utils;

public class PageResult extends TransactionResult{

	
	String nextPageKey; 
	// boolean redirectToNext ; // true - redirect ; false - forward response ;
	
	long availableRecords;
	long fetchedRecords;
	
	 public enum ResponseAction {
		 DEFAULT,FULLRELOAD, RELOADSTATICCONTS , NORELOAD , NEWPAGE, FILEDOWNLOAD, POPUPRESPONSE;
	 }  
	 
	 ResponseAction responseAction = ResponseAction.DEFAULT;

	 ViewController.Mode mode;

	
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

	public long getAvailableRecords() {
		return availableRecords;
	}

	public void setAvailableRecords(long availableRecords) {
		this.availableRecords = availableRecords;
	}

	public long getFetchedRecords() {
		return fetchedRecords;
	}

	public void setFetchedRecords(long fetchedRecords) {
		this.fetchedRecords = fetchedRecords;
	}


	public ViewController.Mode getMode() {
		return mode;
	}

	public void setMode(ViewController.Mode mode) {
		this.mode = mode;
	}
}

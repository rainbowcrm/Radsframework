package com.techtrade.rads.framework.model.simple;

import com.techtrade.rads.framework.model.abstracts.ModelObject;

public class LookupObject extends ModelObject{

	String additionalParam ;
	
	String additionalFields;
	String additionalControls;

	
	public String getAdditionalParam() {
		return additionalParam;
	}

	public void setAdditionalParam(String additionalParam) {
		this.additionalParam = additionalParam;
	}

	public String getAdditionalFields() {
		return additionalFields;
	}

	public void setAdditionalFields(String additionalFields) {
		this.additionalFields = additionalFields;
	}

	public String getAdditionalControls() {
		return additionalControls;
	}

	public void setAdditionalControls(String additionalControls) {
		this.additionalControls = additionalControls;
	}
	
	

}

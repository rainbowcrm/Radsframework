package com.techtrade.rads.framework.ui.components;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.controls.UILabel;

public class UIFilterElement extends UIElement {
	String operator ;

	public UIFilterElement() {
		super();
	}

	
	public UIFilterElement(UIElement element) {
		this.control = element.control;
		this.label = element.label;
		this.modelProperty= element.modelProperty;
		this.mandatory = element.mandatory ;
		this.constantValue = element.constantValue ;
		this.populator=element.populator;
		this.populatorParam =element.populatorParam ;
		this.rendered=element.rendered;
		this.generatedValue=element.generatedValue;
				
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
	

}

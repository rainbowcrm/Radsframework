package com.techtrade.rads.framework.ui.controls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.utils.Utils;

public class UICondition extends UIElement{
	
	List<UIElement> trueElements = null;
	
	public List<UIElement> getTrueElements() {
		return trueElements;
	}
	public void setTrueElements(List<UIElement> trueElements) {
		this.trueElements = trueElements;
	}
	public void addTrueElement (UIElement trueElement) {
		if (trueElements == null) 
			trueElements = new ArrayList<UIElement>();
		trueElements.add(trueElement);
	}
	
	

}

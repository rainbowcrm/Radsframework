package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.constants.FixedAction;

public class UIButton extends UIControl {
	String caption;
	String onClickJS;
	String iconStyle;
	
	List<UIElement> elements = null;
	
	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}
	
	
	public void addElement (UIElement element) {
		if (elements == null) 
			elements = new ArrayList<UIElement>();
		elements.add(element);
	}
			
	public String getIconStyle() {
		return iconStyle;
	}
	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getOnClickJS() {
		return onClickJS;
	}
	public void setOnClickJS(String onClickJS) {
		this.onClickJS = onClickJS;
	}
	
	public UIButton(String id, String caption,  String onClickJS) {
		setId(id);
		this.caption = caption;
		this.onClickJS = onClickJS;
		setExternalize(true);
	}
	
	public UIButton(String id, String caption,  FixedAction act) {
		setId(id);
		this.caption = caption;
		setFixedAction(act);
		setExternalize(true);
	}
	
	
	
	
	
	
	
	
	
	
}

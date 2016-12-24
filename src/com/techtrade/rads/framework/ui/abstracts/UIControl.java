package com.techtrade.rads.framework.ui.abstracts;

import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsStyles;
import com.techtrade.rads.framework.utils.Utils;


public abstract class UIControl  implements Cloneable{
	
	
	/*public enum WindowAction  {
		CLOSELOOKUPWINDOW, CANCELWINDOW, SUBMITPAGE ; 
	}
	WindowAction windowAction;*/ 
	
	private Object value;
	protected String id;
	String style ;
	FixedAction fixedAction ;
	String fixedActionField ;
	String fixedActionParam ;
	boolean applyStyleonChildren ;
	
	String dataProperty ;
	boolean externalize;


	/*public WindowAction getWindowAction() {
		return windowAction;
	}
	public void setWindowAction(WindowAction windowAction) {
		this.windowAction = windowAction;
	}*/
	
	
	
	public String getId() {
		return id;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public UIControl(String id) {
		setId(id);
	}

	public UIControl()
	{
		
	}
	public void setId(String id) {
		this.id = id;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public FixedAction getFixedAction() {
		return fixedAction;
	}
	public void setFixedAction(FixedAction fixedAction) {
		this.fixedAction = fixedAction;
	}
	public String getFixedActionField() {
		return fixedActionField;
	}
	public void setFixedActionField(String fixedActionField) {
		this.fixedActionField = fixedActionField;
	}
	public String getFixedActionParam() {
		return fixedActionParam;
	}
	public void setFixedActionParam(String fixedActionParam) {
		this.fixedActionParam = fixedActionParam;
	}
	public boolean isApplyStyleonChildren() {
		return applyStyleonChildren;
	}
	public void setApplyStyleonChildren(boolean applyStyleonChildren) {
		this.applyStyleonChildren = applyStyleonChildren;
	}
	public String getDataProperty() {
		return dataProperty;
	}
	public void setDataProperty(String dataProperty) {
		this.dataProperty = dataProperty;
	}
	public boolean isExternalize() {
		return externalize;
	}
	public void setExternalize(boolean externalize) {
		this.externalize = externalize;
	}
	
	
	
	
	
}

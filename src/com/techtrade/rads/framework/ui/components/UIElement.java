package com.techtrade.rads.framework.ui.components;



import java.lang.reflect.Method;
import java.util.Map;
import java.util.StringTokenizer;

import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.utils.Utils;



public class UIElement implements Cloneable{

	
	
	
	UILabel label;
	UIControl control;
	String modelProperty;
	Object constantValue ;
	String rendered ;
	boolean mandatory;
	boolean showInPrevCol;

	/**
	 * Intended to use for Transaction child objects displayed in table
	 */
	String extendedmodelProperty;
	
	String supplementaryProperty; 
	
	/** Used of options in List and other controls
	 * 
	 */
	String populator;
	String populatorParam;
	String generatedValue;
	
	/**
	 * used only for file controls..
	 */
	String fileNameProperty;
	
		
	public UIElement() {
		
	}
	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		UIElement retElement = (UIElement)super.clone();
		if(getLabel() != null )
			retElement.setLabel((UILabel)getLabel().clone());
		if(getControl() != null)
			retElement.setControl((UIControl)getControl().clone());
		
		return retElement; 
	}



	public UIElement (UIControl control){
		this.control = control;
	}
	
	public String getId() {
		return control.getId();
	}
	
	public void setId(String id) {
		control.setId(id);
	}

	
	public UIElement (UILabel label,UIControl control){
		this.label = label;
		this.control = control;
	}
	
	public UIElement (String label,UIControl control){
		this.label = new UILabel(label);
		this.control = control;
	}
	
	public UIElement (UILabel label,UIControl control,String modelProperty){
		this.label = label;
		this.control = control;
		this.modelProperty =  modelProperty;
	}
	
	public UIElement (String label,UIControl control,String modelProperty){
		this.label = new UILabel(label);
		this.label.setLabel(label);
		this.control = control;
		this.modelProperty =  modelProperty;
	}
	
	
	public UILabel getLabel() {
		return label;
	}
	
	public void setLabel(UILabel label) {
		this.label = label;
	}
	
	public UIControl getControl() {
		return control;
	}
	
	public void setControl(UIControl control) {
		this.control = control;
	}
	

	
	public String getModelProperty() {
		return modelProperty;
	}


	public void setModelProperty(String modelProperty) {
		this.modelProperty = modelProperty;
	}
	
	

	
	public boolean isMandatory() {
		return mandatory;
	}


	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}


	public String getExtendedmodelProperty() {
		return extendedmodelProperty;
	}


	public void setExtendedmodelProperty(String extendedmodelProperty) {
		this.extendedmodelProperty = extendedmodelProperty;
	}

	public String getPopulator() {
		return populator;
	}

	public void setPopulator(String populator) {
		this.populator = populator;
	}

	public String getPopulatorParam() {
		return populatorParam;
	}

	public void setPopulatorParam(String populatorParam) {
		this.populatorParam = populatorParam;
	}



	public UIElement getChildElementById(String Id) {
		if (this.getControl() != null  && Id.equals(this.getControl().getId()) ) {
			return this; 
		}
		if (this.getControl() != null  && Id.equals(this.getControl().getId()) ) {
			return this; 
		}

		return null;
	}


	public Object getConstantValue() {
		return constantValue;
	}


	public void setConstantValue(Object constantValue) {
		this.constantValue = constantValue;
	}


	public String getRendered() {
		return rendered;
	}


	public void setRendered(String rendered) {
		this.rendered = rendered;
	}
  
	
	
	public String getGeneratedValue() {
		return generatedValue;
	}


	public void setGeneratedValue(String generatedValue) {
		this.generatedValue = generatedValue;
	}
	
	
	public String getFileNameProperty() {
		return fileNameProperty;
	}

	public void setFileNameProperty(String fileNameProperty) {
		this.fileNameProperty = fileNameProperty;
	}

	
	
	public String getSupplementaryProperty() {
		return supplementaryProperty;
	}



	public void setSupplementaryProperty(String supplementaryProperty) {
		this.supplementaryProperty = supplementaryProperty;
	}



	public Object getValue() {
		return control.getValue();
	}

	public void setValue(Object value) {
		control.setValue(value);
	}
	

	public String getStyle() {
		return control.getStyle();
	}

	public void setStyle(String style) {
		control.setStyle(style);
	}
	
	public boolean  shouldDisplay (ModelObject object,ViewController controller) throws Exception{
		if (Utils.isNullString(rendered))  return true ;
		rendered = rendered.trim();
		if (!rendered.contains(".")) 
			return Boolean.parseBoolean(rendered);
		else {
			StringTokenizer tokenizer = new StringTokenizer(rendered,".");
			int count = 0;
			String elements [] = new String [10] ;
			while(tokenizer.hasMoreTokens()) {
				elements[count ++ ] = tokenizer.nextToken() ;
			}
			if ( ("Object".equalsIgnoreCase(elements[0]) || "!Object".equalsIgnoreCase(elements[0]))  ) {
				if(object != null) {
					Method methodRead = object.getClass().getMethod(elements[1]);
					Boolean result = (Boolean) methodRead.invoke(object, null);
					if (result == null) return false;
					if (elements[0].startsWith("!"))
						return !result.booleanValue();
					else
						return result.booleanValue();
				}
			} else 	if ("Controller".equalsIgnoreCase(elements[0]) || "!Controller".equalsIgnoreCase(elements[0])) {
				  Method methodRead =  controller.getClass().getMethod(elements[1]);	
				  Boolean result  = (Boolean) methodRead.invoke(controller, null);
				  if (elements[0].startsWith("!"))
			  	  return !result.booleanValue() ;
			  else
				  return result.booleanValue() ;
			} else
				 throw new RadsException("Unidentified rendered value" + rendered) ;
			
			
		}
		
		
		return true ;
	}

	
	public FixedAction getFixedAction() {
		return control.getFixedAction();
	}
	public void setFixedAction(FixedAction fixedAction) {
		control.setFixedAction(fixedAction);
	}
	public String getFixedActionField() {
		return control.getFixedActionField();
	}
	public void setFixedActionField(String fixedActionField) {
		control.setFixedActionField(fixedActionField);
	}
	public String getFixedActionParam() {
		return control.getFixedActionParam();
	}
	public void setFixedActionParam(String fixedActionParam) {
		control.setFixedActionParam(fixedActionParam);
	}


	public boolean isShowInPrevCol() {
		return showInPrevCol;
	}


	public void setShowInPrevCol(boolean showInPrevCol) {
		this.showInPrevCol = showInPrevCol;
	}
	
	public static Map populateOptions(ViewController controller,Object object,String populator) throws Exception{
		if (!populator.contains(".")) {
			return getPopulatorOptions(controller, populator);
		}
		StringTokenizer tokenizer = new StringTokenizer(populator,".");
		int count = 0;
		String elements [] = new String [10] ;
		
		while(tokenizer.hasMoreTokens()) {
			elements[count ++ ] = tokenizer.nextToken() ;
		}
		if ("Object".equalsIgnoreCase(elements[0]) ) {
			return getPopulatorOptions(object, elements[1]);
		} else if ("Controller".equalsIgnoreCase(elements[0]) ) {
			return getPopulatorOptions(controller, elements[1]);
		}else  
			throw new RadsException("Unidentified populator Options " + populator) ;
	}
	
	public static Map populateOptionsWithParam(ViewController controller,Object object,String populator, String populatorParam) throws Exception{
		if (!populator.contains(".")) {
			return getPopulatorOptionsWithParam(controller, populator,populatorParam);
		}
		StringTokenizer tokenizer = new StringTokenizer(populator,".");
		int count = 0;
		String elements [] = new String [10] ;
		
		while(tokenizer.hasMoreTokens()) {
			elements[count ++ ] = tokenizer.nextToken() ;
		}
		if ("Object".equalsIgnoreCase(elements[0]) ) {
			return getPopulatorOptionsWithParam(object, elements[1],populatorParam);
		} else if ("Controller".equalsIgnoreCase(elements[0]) ) {
			return getPopulatorOptionsWithParam(controller, elements[1],populatorParam);
		}else  
			throw new RadsException("Unidentified populator Options " + populator) ;
	}


	 private static Map getPopulatorOptions(Object obj , String populator) throws Exception {
		 Method methodRead =  obj.getClass().getMethod(populator);
		   if (methodRead.getReturnType().equals(Map.class)) {
			  Map ans = (Map) methodRead.invoke(obj, null);
			  return ans ;
		   } 
		   return null;
	 }
	 

	 private static Map getPopulatorOptionsWithParam(Object obj , String populator,String populatorParam) throws Exception {
		 Method methodRead =  obj.getClass().getMethod(populator, new Class[]{String.class});
		   if (methodRead.getReturnType().equals(Map.class)) {
			  Map ans = (Map) methodRead.invoke(obj, populatorParam);
			  return ans ;
		   } 
		   return null;
	 }
	
	 public static String GenerateValue(ViewController controller,Object object,String genValue) throws Exception{
		 if (!genValue.contains(".")) {
				return getStringValue(controller, genValue);
			}
			StringTokenizer tokenizer = new StringTokenizer(genValue,".");
			int count = 0;
			String elements [] = new String [10] ;
			
			while(tokenizer.hasMoreTokens()) {
				elements[count ++ ] = tokenizer.nextToken() ;
			}
			if ("Object".equalsIgnoreCase(elements[0]) ) {
				return getStringValue(object, elements[1]);
			} else if ("Controller".equalsIgnoreCase(elements[0]) ) {
				return getStringValue(controller, elements[1]);
			}else  
				throw new RadsException("Unidentified getGeneratedValue Options " + genValue) ;
	 }
	 
	 private static String getStringValue(Object obj , String populator) throws Exception {
		 Method methodRead =  obj.getClass().getMethod(populator);
		   if (methodRead.getReturnType().equals(String.class)) {
			  String ans = (String) methodRead.invoke(obj, null);
			  return ans ;
		   } 
		   return null;
	 }

	
}

package com.techtrade.rads.framework.ui.readers;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UICRUDPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIGeneralPage;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UILookupPage;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITransactionPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UIBooleanCheckBox;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIFileUpload;
import com.techtrade.rads.framework.ui.controls.UIMenu;
import com.techtrade.rads.framework.utils.Utils;

public class HTMLAlternativeReader  extends HTMLReader{

	public HTMLAlternativeReader(Object board) {
		super(board);
	}

	@Override
	public void read(UIControl control, ModelObject object, String modelProperty)
			throws RadsException {
		super.read(control, object, modelProperty);
		
	}

	protected void readUIGeneralPage(UIGeneralPage page, HttpServletRequest request, ModelObject object ) throws Exception{
		if ( page != null && page.getForm() != null ) {
			String fixedActField = page.getTemplate().getFixedActionfield();
			String fixedAction = request.getParameter(fixedActField);
			String fixedActionParamField = page.getTemplate().getFixedActionParamfield();
			if (!Utils.isNullString(fixedAction))
				page.setFixedAction(FixedAction.getFixedAction(fixedAction));
			String fixedActionParam = request.getParameter(fixedActionParamField);
			if (!Utils.isNullString(fixedActionParam))
				page.setFixedActionParam(fixedActionParam);
			List <UIElement> inputElements = page.getInputElements() ;
			for (UIElement element : inputElements) {
				readControl(element, request, object);
			}
			/*if (!Utils.isNullList(page.getForm().getElements() )) {
				for (UIElement element : page.getForm().getElements() ) {
					if (element.getControl() instanceof UIMenu )
						 continue ;
					if (element instanceof UICondition ) {
						readConditionalContent((UICondition) element, request, object);
					}
					readControl(element, request, object);
				}
			}*/
		}
	}
	
	protected void readUITransactionPage(UITransactionPage page, HttpServletRequest request, ModelObject object ) throws Exception{
		if ( page != null && page.getForm() != null ) {
			String fixedActField = page.getTemplate().getFixedActionfield();
			String fixedAction = request.getParameter(fixedActField);
			String fixedActionParamField = page.getTemplate().getFixedActionParamfield();
			if (!Utils.isNullString(fixedAction))
				page.setFixedAction(FixedAction.getFixedAction(fixedAction));
			String fixedActionParam = request.getParameter(fixedActionParamField);
			if (!Utils.isNullString(fixedActionParam))
				page.setFixedActionParam(fixedActionParam);
	

			List <UIElement> inputElements = page.getInputElements() ;
			for (UIElement element : inputElements) {
				readControl(element, request, object);
			}
			/*if (!Utils.isNullList(page.getForm().getElements() )) {
				for (UIElement element : page.getForm().getElements() ) {
					if (element instanceof UICondition ) {
						readConditionalContent((UICondition) element, request, object);
					}
					if (!Utils.isNullString(element.getModelProperty())  ){
						String value  =  request.getParameter(element.getControl().getId()) ;
						if (!Utils.isNullString(value)){
							callSetter(object,element, value);
						}
					}else {
						//read(element.getControl(), object);
						readControl(element, request, object);
					}
				}
			}*/
		}
	}
	
	protected Object indexElement(Collection collection, int index) {
		if (collection instanceof  List)  {
			List list = (List)collection;
			if (list.size() <= index ) return null;
			return  list.get(index) ;
		}
		if (collection instanceof Vector)  {
			Vector vector = (Vector)collection;
			if (vector.size() <= index ) return null;
			return  vector.get(index);
		}
		if (collection instanceof Set)  {
			Set set = (Set)collection;
			if (set.size() <= index ) return null;
			Iterator it = set.iterator();
			int count =0 ;
			while(it.hasNext()) {
				Object ret = it.next() ;
				if (index == count)
					return ret;
				count ++ ;
			}
		}
		return null;
	}
	
	protected void readCollections(UIElement element ,HttpServletRequest request, ModelObject object ) throws Exception{
		String[] values ;
		if (element.getControl() instanceof UIBooleanCheckBox) {
			values = request.getParameterValues(((UIBooleanCheckBox)element.getControl()).getHiddenControlId());
		}else {
			values = request.getParameterValues(element.getControl().getId());
		}
		if (values == null ) return ;
		String modelProperty = element.getExtendedmodelProperty();
		String collectionProperty = modelProperty.substring(0, modelProperty.indexOf("["));
		String subObjectDataType = modelProperty.substring(modelProperty.indexOf("[")+1, modelProperty.indexOf("]"));
		String finalProperty = modelProperty.substring(modelProperty.indexOf("].")+2, modelProperty.length());
		Method collectionObjectRead =  object.getClass().getMethod("get" + collectionProperty);
		Collection collObject = (Collection)collectionObjectRead.invoke(object);
		if (List.class.isAssignableFrom(collectionObjectRead.getReturnType())) {
			if (collObject  == null)
				collObject = new ArrayList ();
		}
		if (Vector.class.isAssignableFrom(collectionObjectRead.getReturnType())) {
			if (collObject  == null)
				collObject = new Vector();
		}
		if (Set.class.isAssignableFrom(collectionObjectRead.getReturnType())) {
			if (collObject  == null)
				collObject = new LinkedHashSet ();
		}
		for (int i = 0 ; i < values.length ; i ++ ) {
			Object subObject = indexElement(collObject, i);
			if (subObject == null ) {
				subObject =  Class.forName(subObjectDataType).newInstance() ;
				collObject.add(subObject);
			}
			//callSetter(subObject,finalProperty, values[i],element.getControl());
			setonSubObject(subObject, finalProperty, values[i], element);
			
		}

		Method methodSet = object.getClass().getMethod("set" + collectionProperty, new Class[] { collectionObjectRead.getReturnType() });
		methodSet.invoke(object, collObject);
	}
	


	protected void callSetter (Object object ,String property , String value, UIControl control) {
		try {             
			  
			   Method methodRead =  object.getClass().getMethod("get" + Utils.initupper(property));
			   Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   Object param; 
			   if ("java.util.Date".equals(methodRead.getReturnType()) && control instanceof UIDate)
				   param = Utils.stringToDate(value, ((UIDate)control).getFormat());
			   else
				   param = Utils.stringToType(value, methodRead.getReturnType());
			   method.invoke(object, param);
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
	}
	
	private String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	protected void readControl (UIElement element ,HttpServletRequest request, ModelObject object ) throws Exception{
		if (!Utils.isNullString(element.getModelProperty()) ){
			if(!Utils.isNullString(element.getExtendedmodelProperty()) &&
					element.getExtendedmodelProperty().contains("[") && element.getExtendedmodelProperty().contains("]") ){
				readCollections(element,request,object);
			}else {
				if (element.getControl() instanceof UIFileUpload) {
					try { 
						final Part filePart = request.getPart(element.getControl().getId()); 
						InputStream  filecontent = filePart.getInputStream();
						String fileName =getFileName(filePart);
						final byte[] bytes = new byte[((UIFileUpload)element.getControl()).getMaxSize()];
						filecontent.read(bytes);
						callSetter(object,element, bytes);
						String fileNameProp = element.getFileNameProperty() ;
						if(!Utils.isNull(fileNameProp)) {
							callSetter(object, fileNameProp, fileName);
						}
					}catch(Exception ex) {
						// File not present"
					}
				}else { String value  =  request.getParameter(element.getControl().getId()) ;
					if (!Utils.isNullString(value)){
						callSetter(object,element, value);
					}
				}
			}
		}else {
			read(element.getControl(), object,element.getModelProperty());
		}
	}
	
	protected void readUICRUDPage(UICRUDPage page, HttpServletRequest request, ModelObject object ) throws Exception{
		if ( page != null && page.getForm() != null ) {
			String fixedActField = page.getTemplate().getFixedActionfield();
			String fixedAction = request.getParameter(fixedActField);
			String fixedActionParamField = page.getTemplate().getFixedActionParamfield();
			if (!Utils.isNullString(fixedAction))
				page.setFixedAction(FixedAction.getFixedAction(fixedAction));
			String fixedActionParam = request.getParameter(fixedActionParamField);
			if (!Utils.isNullString(fixedActionParam))
				page.setFixedActionParam(fixedActionParam);
			
			List <UIElement> inputElements = page.getInputElements() ;
			for (UIElement element : inputElements) {
				readControl(element, request, object);
			}
			
			/*if (!Utils.isNullList(page.getForm().getElements() )) {
				for (UIElement element : page.getForm().getElements() ) {
					if (element.getControl() instanceof UIMenu )
						 continue ;
					if (element instanceof UICondition ) {
						readConditionalContent((UICondition) element, request, object);
					}
					readControl(element, request, object);
				}
			}*/
		}
	}
	
	
	

}

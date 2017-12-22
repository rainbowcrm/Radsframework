package com.techtrade.rads.framework.ui.readers;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

/*import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
*/

import javax.servlet.http.Part;

import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.filter.FilterNode;
import com.techtrade.rads.framework.model.abstracts.DataType;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.ILookupService;
import com.techtrade.rads.framework.ui.abstracts.Reader;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.SortCriteria;
import com.techtrade.rads.framework.ui.components.UICRUDPage;
import com.techtrade.rads.framework.ui.components.UIDataSheetPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIFilterElement;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UIGeneralPage;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UILookupPage;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.components.UITransactionPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsConstants;
import com.techtrade.rads.framework.ui.constants.RadsControlConstants;
import com.techtrade.rads.framework.ui.controls.UIBooleanCheckBox;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UIDialog;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIFileUpload;
import com.techtrade.rads.framework.ui.controls.UIFilterSet;
import com.techtrade.rads.framework.ui.controls.UIIFrame;
import com.techtrade.rads.framework.ui.controls.UIList;
import com.techtrade.rads.framework.ui.controls.UIMenu;
import com.techtrade.rads.framework.ui.controls.UITab;
import com.techtrade.rads.framework.ui.controls.UITabSet;
import com.techtrade.rads.framework.utils.Utils;

public class HTMLReader extends Reader{

	
	
	@Override
	public void read(UIControl control, ModelObject object, String modelProperty) throws RadsException {
		try {
			
			HttpServletRequest request = (HttpServletRequest) board;
			if ( control instanceof UITransactionPage) {
				readUITransactionPage((UITransactionPage)control, request, object);
			}else if ( control instanceof UICRUDPage) {
				readUICRUDPage((UICRUDPage)control, request, object);
			}else if ( control instanceof UIGeneralPage) {
				readUIGeneralPage((UIGeneralPage)control, request, object);
			}  else if ( control instanceof UILookupPage) {
				readUILookupPage((UILookupPage)control, request, object);
			}  else if ( control instanceof UITable) {
				readTable((UITable)control, request, object,modelProperty);
			}else if ( control instanceof UIDataSheetPage) {
				readUIDataSheetPage((UIDataSheetPage)control,request);
			}else if ( control instanceof UIListPage) {
				readUILstPage((UIListPage)control,request);
			} else if ( control instanceof UIDiv) {
				readDiv((UIDiv)control, request, object, modelProperty);
			}else if ( control instanceof UITabSet) {
				readTabSet((UITabSet)control, request, object, modelProperty);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new RadsException (ex);
		}
		return;
		
	}
	
	
	protected Object  setonSubObject(Object object, String property, String value,UIElement element ) throws Exception {
		if(property.contains(".")) {
			String subObjectProperty = property.substring(0, property.indexOf("."));
			String remainingProperty = property.substring(property.indexOf(".")+1,property.length());
			Method subObjectRead =  getterMethod(object.getClass(),Utils.initupper(subObjectProperty));  //object.getClass().getMethod("get" + Utils.initupper(subObjectProperty));
			Object curObject = subObjectRead.invoke(object);
			if (curObject == null ) {
				curObject =   Class.forName(subObjectRead.getReturnType().getName()).newInstance();  //curObject.getClass().newInstance() ; 
				Method methodSet = object.getClass().getMethod("set" +  Utils.initupper(subObjectProperty), new Class[] { subObjectRead.getReturnType() });
				methodSet.invoke(object, curObject);
			}
			return setonSubObject(curObject,remainingProperty, value,element );
		}else {
			 Method methodRead =   getterMethod(object.getClass(),Utils.initupper(property)); //object.getClass().getMethod("get" + Utils.initupper(property));
			 Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   Object param; 
			   if ("java.util.Date".equals(methodRead.getReturnType()) && element.getControl() instanceof UIDate)
				   param = Utils.stringToDate(value, ((UIDate)element.getControl()).getFormat());
			   else
				   param = Utils.stringToType(value, methodRead.getReturnType());
			   method.invoke(object, param);
			   return object ;
		}
		
		//return object;
	}
	
	protected Object  setByteArrayonSubObject(Object object, String property, byte[] value,UIElement element ) throws Exception {
		if(property.contains(".")) {
			String subObjectProperty = property.substring(0, property.indexOf("."));
			String remainingProperty = property.substring(property.indexOf(".")+1,property.length());
			Method subObjectRead =  getterMethod(object.getClass(),Utils.initupper(subObjectProperty));  //object.getClass().getMethod("get" + Utils.initupper(subObjectProperty));
			Object curObject = subObjectRead.invoke(object);
			if (curObject == null ) {
				curObject =   Class.forName(subObjectRead.getReturnType().getName()).newInstance();  //curObject.getClass().newInstance() ; 
				Method methodSet = object.getClass().getMethod("set" +  Utils.initupper(subObjectProperty), new Class[] { subObjectRead.getReturnType() });
				methodSet.invoke(object, curObject);
			}
			return setByteArrayonSubObject(curObject,remainingProperty, value,element );
		}else {
			 Method methodRead =   getterMethod(object.getClass(),Utils.initupper(property)); //object.getClass().getMethod("get" + Utils.initupper(property));
			 Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			  
			   method.invoke(object, value);
			   return object ;
		}
		
		//return object;
	}
	
	
	protected void callSetter (Object object ,UIElement element , byte[] bytes) {
		try {             
			   String property= element.getModelProperty();
			   if (property.contains("."))  {
				   setonSubObject(object,property,null,element) ;
				   return;
			   }
			   Method methodRead =  getterMethod(object.getClass(),Utils.initupper(property));
			   Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   method.invoke(object, bytes);
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
	}
	
	protected void callSetter (Object object , String property , String value) {
		try {             
			   Method methodRead =  object.getClass().getMethod("get" + Utils.initupper(property));
			   Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   method.invoke(object, value);
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
	}
	
	protected void callSetter (Object object , String property , String values[]) {
		try {             
			   Method methodRead =  object.getClass().getMethod("get" + Utils.initupper(property));
			   Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   method.invoke(object,new Object[]{values} ); 
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
	}
	
	protected Method getterMethod(Class className , String property) throws java.lang.NoSuchMethodException  {
		return Utils.getterMethod(className, property);
	}

	
	
	protected void callSetter (Object object ,UIElement element , String value) {
		try {             
			   String property= element.getModelProperty();
			   if (property.contains("."))  {
				   setonSubObject(object,property,value,element) ;
				   return;
			   }
			   Method methodRead =   getterMethod(object.getClass(),property);
			   Method method = object.getClass().getMethod("set" + Utils.initupper(property), new Class[] { methodRead.getReturnType() });
			   Object param; 
			   if ("java.util.Date".equals(methodRead.getReturnType().getName()) && element.getControl() instanceof UIDate)
				   param = Utils.stringToDate(value, "yyyy-MM-dd");
			   else
				   param = Utils.stringToType(value, methodRead.getReturnType());
			   method.invoke(object, param);
			 } catch (Exception ex) {
			         ex.printStackTrace();
			 }
	}
	
	
	private Object[] readChildObject(HttpServletRequest request,UITableRow row , String className, Object retObject[])  throws Exception{
		//Object retObject = Class.forName(className).newInstance() ;
		for (UITableCol col: row.getCols()) {
			for  (UIElement element :  col.getElements() ){
				if  (request.getParameterValues(element.getControl().getId()) != null )  {
					String value[] = request.getParameterValues(element.getControl().getId()); 
					for (int i = 0 ; i < value.length ; i ++ )  {
						if (!Utils.isNullString(value[i])){
							if (retObject[i] == null )  {
								retObject[i] = Class.forName(className).newInstance() ;
							}
								callSetter(retObject[i],element, value[i]);
						}
					}
				}
			}
		}
		return retObject ;
	}
	
	private String getFileName (Part filePart) {
		Collection st = filePart.getHeaders("content-disposition");
		Iterator it = st.iterator();
		while( it.hasNext() ) {
			Object obj  = it.next();
			System.out.println(obj.getClass()) ;
		}
		String fileName = "abc.jpg";
		return fileName;
		
	}
	private void readTableRow(UITableRow row, HttpServletRequest request, ModelObject object, String modelProperty) throws Exception{
		if (row != null ) {
			
			if (!Utils.isNullString(modelProperty) && modelProperty.contains("[]") && !Utils.isNullString(row.getDataType())) { // property is a collection
				Object childObject[] = new Object[100];
			    childObject = readChildObject(request,row ,row.getDataType(),childObject);
				String collectionProperty = modelProperty.substring(0, modelProperty.indexOf("["));
				Method collectionObjectRead =  object.getClass().getMethod("get" + collectionProperty);
				Collection collObject = (Collection)collectionObjectRead.invoke(object);
			
				if (collectionObjectRead.getReturnType().isAssignableFrom(List.class)) {
					if (collObject  == null)
						collObject = new ArrayList ();
					for (int i = 0; i < childObject.length ; i ++ ) {
						if(childObject[i] == null )
							break ;
						collObject.add(childObject[i]);
					}
				} else if (collectionObjectRead.getReturnType().isAssignableFrom(Vector.class)) {
					if (collObject  == null)
						collObject = new Vector();
					for (int i = 0; i < childObject.length ; i ++ ) {
						if(childObject[i] == null )
							break ;
						collObject.add(childObject[i]);
					}
				}else if (collectionObjectRead.getReturnType().isAssignableFrom(Set.class)) {
					if (collObject  == null)
						collObject = new LinkedHashSet ();
					for (int i = 0; i < childObject.length ; i ++ ) {
						if(childObject[i] == null )
							break ;
						collObject.add(childObject[i]);
					}
				} 
				/*else if (collectionObjectRead.getReturnType().isAssignableFrom(Map.class)) {
					collObject = new HashMap();
				}else {
					collObject =   Class.forName(collectionObjectRead.getReturnType().getName()).newInstance();  //curObject.getClass().newInstance() ;
				}*/
				
				Method methodSet = object.getClass().getMethod("set" + Utils.initupper(collectionProperty), new Class[] { collectionObjectRead.getReturnType() });
				methodSet.invoke(object, collObject);
				
				return ;
			}
			
			
			for (UITableCol col: row.getCols()) {
				if (Utils.isNullList(col.getElements())) continue ;
				for  (UIElement element :  col.getElements() ){
					if (element instanceof UICondition) {
						readConditionalContent(((UICondition)element), request, object);
					}
					if (!Utils.isNullString(element.getModelProperty())  ){
						if (element.getControl() instanceof UIFileUpload) {
							try { 
								final Part filePart = request.getPart(element.getControl().getId()); 
								InputStream  filecontent = filePart.getInputStream();  //((UIFileUpload)element.getControl()).getMaxSize()]
								String fileName =getFileName(filePart);
								final byte[] bytes = new byte[filecontent.available()];
								filecontent.read(bytes);
								callSetter(object,element, bytes);
								String fileNameProp = element.getFileNameProperty() ;
								if(!Utils.isNull(fileNameProp)) {
									callSetter(object, fileNameProp, fileName);
								}
							}catch(Exception ex) {
								// File not present"
							}
						}else  {
							String value  =  request.getParameter(element.getControl().getId()) ;
							if (!Utils.isNullString(value)){
								callSetter(object,element, value);
							}
						}
					}
				}
			}
		}
		
	}
	protected void readTab(UITab tab, HttpServletRequest request, ModelObject object ) throws Exception{
		if(tab  == null || Utils.isNullList(tab.getElements())) return;
		for (UIElement element :  tab.getElements()) {
			readControl(element, request, object);
		}
	}
	
	protected void readTabSet(UITabSet tabSet, HttpServletRequest request, ModelObject object, String modelProperty ) throws Exception{
		if (tabSet == null || Utils.isNull(tabSet.getTabs())) return ;
		for (UITab element : tabSet.getTabs()) {
			readTab((UITab)element, request, object);
		}
	}
	
	protected void readDiv(UIDiv div, HttpServletRequest request, ModelObject object, String modelProperty ) throws Exception{
		if (div == null || Utils.isNull(div.getElements())) return ;
		for (UIElement element : div.getElements()) {
			if (element.getControl() instanceof UIMenu)
				 continue ;
			readControl(element, request, object);
		}
	}
	
	protected void readTable (UITable table, HttpServletRequest request, ModelObject object, String modelProperty ) throws Exception{
		if (table != null)  {
			if (!Utils.isNullList(table.getHeaders())){
				for ( UITableHead head :   table.getHeaders()) {
					readTableRow(head.getRow(), request, object,modelProperty);
				}
			}
			if (!Utils.isNullList(table.getInnerRows())){
				for (UITableRow row: table.getInnerRows()) {
					readTableRow(row, request, object,modelProperty);
				}
			}
			if (!Utils.isNullList(table.getFooters())){
				for (UITableFooter foot : table.getFooters()){
					readTableRow(foot.getRow(), request, object,modelProperty);
				}
			}
		}

	}
	
	protected void readConditionalContent  (UICondition condition, HttpServletRequest request, ModelObject object ) throws RadsException{
		for (UIElement element : condition.getTrueElements()) {
			if (!Utils.isNullString(element.getModelProperty())  ){
				String value  =  request.getParameter(element.getControl().getId()) ;
				if (!Utils.isNullString(value)){
					callSetter(object,element, value);
				}
			}else {
				read(element.getControl(), object,element.getModelProperty());
			}
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
			if (!Utils.isNullList(page.getForm().getElements() )) {
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
			}
		}
	}
	
	protected void readControl (UIElement element ,HttpServletRequest request, ModelObject object ) throws Exception{
		if (!Utils.isNullString(element.getModelProperty()) &&  !(element.getControl() instanceof UITable) ){
			if (element.getControl() instanceof UIFileUpload) {
				try { 
					final Part filePart = request.getPart(element.getControl().getId()); 
					InputStream  filecontent = filePart.getInputStream();
					final byte[] bytes = new byte[((UIFileUpload)element.getControl()).getMaxSize()];
					filecontent.read(bytes);
					callSetter(object,element, bytes);
				}catch(Exception ex) {
					// File not present"
				}
			}else if (element.getControl() instanceof UIList){
				UIList lst= (UIList)element.getControl();
				if (lst.isMultiSelect())  {
					String [] values = request.getParameterValues(element.getControl().getId()) ;
					if(values != null )
						callSetter(object,element.getModelProperty(),values);
				}else {
					String value  =  request.getParameter(element.getControl().getId()) ;
					if (!Utils.isNullString(value)){
						callSetter(object,element, value);
					}
				}
				
			}else {
				String value  =  request.getParameter(element.getControl().getId()) ;
				if (!Utils.isNullString(value)){
					callSetter(object,element, value);
				}
			}
		}else if (element.getControl() instanceof UIDiv) {
			readDiv((UIDiv) element.getControl(), request, object, element.getModelProperty());
		}else if (element.getControl() instanceof UIDialog) {
			UIDialog dialog  = (UIDialog)element.getControl() ;
			if (!Utils.isNullList(dialog.getElements()) ) {
				for (UIElement innerElement : dialog.getElements())  {
					if (innerElement.getControl() instanceof UIMenu)
						 continue ;
					readControl(innerElement,request, object);
				}
			}
		}else if (element.getControl() instanceof UIIFrame) {
			UIIFrame iframe  = (UIIFrame)element.getControl() ;
			if (!Utils.isNullList(iframe.getElements()) ) {
				for (UIElement innerElement : iframe.getElements())  {
					if (innerElement.getControl() instanceof UIMenu)
						 continue ;
					readControl(innerElement,request, object);
				}
			}
		}else {
			read(element.getControl(), object,element.getModelProperty());
		}
	}
	
	
	protected void readChildControls(UIDiv div,HttpServletRequest request, ModelObject object ) throws RadsException{
		
		
	}
	
	
	protected void readMultiPartform (UIForm form,  HttpServletRequest request, ModelObject object ) {
	 //	List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	/*    for (FileItem item : multiparts) {
	        if (!item.isFormField()) {
	            //your operations on file
	        } else {
	            String name = item.getFieldName();
	            String value = item.getString();
	            //you operations on paramters
	        }
	}*/
		
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
			String submitAction = request.getParameter(page.getTemplate().getSubmitActionfield());
			if(!Utils.isNullString(submitAction))
				page.setSubmitAction(submitAction);
			if (!Utils.isNullList(page.getForm().getElements() )) {
				for (UIElement element : page.getForm().getElements() ) {
					if (element.getControl() instanceof UIMenu )
						 continue ;
					if (element instanceof UICondition ) {
						readConditionalContent((UICondition) element, request, object);
					}
					readControl(element, request, object);
				}
			}
		}
	}
	
	protected void readUIDataSheetPage(UIDataSheetPage page,HttpServletRequest request ) throws Exception  {
		readUILstPage(page,request) ;
		if (!Utils.isNullList(page.getDataEntryColumns())) {
			Object childObject[] = new Object[100];
			for (UIElement element : page.getDataEntryColumns() ) {
				String value[] = null ;
				if (element == null ) continue;
				if(element.getControl() instanceof UIBooleanCheckBox)  {
					UIBooleanCheckBox booleanBox = (UIBooleanCheckBox)element.getControl();
					if  (request.getParameterValues(booleanBox.getHiddenControlId()) != null )  {
						value = request.getParameterValues(booleanBox.getHiddenControlId()); 
					}
				}else {
					if  (request.getParameterValues(element.getControl().getId()) != null )  {
						value = request.getParameterValues(element.getControl().getId()); 
					}
				}
				if( value != null)  {
					for (int i = 0 ; i < value.length ; i ++ )  {
						if (!Utils.isNullString(value[i])){
							if (childObject[i] == null )  {
								childObject[i] = Class.forName(page.getModelClass()).newInstance() ;
							}
								callSetter(childObject[i],element, value[i]);
						}
					}
				}
			}
			
			String sortField = request.getParameter(RadsControlConstants.SORT_FIELD);
			String sortDirection = request.getParameter(RadsControlConstants.SORT_DIRECTION);
			if(!Utils.isNullString(sortField)){
				SortCriteria sortCriteria =new SortCriteria();
				sortCriteria.setFieldName(sortField);
				if("DESC".equalsIgnoreCase(sortDirection))
					sortCriteria.setDirection(SortCriteria.DIRECTION.DESCENDING);
				else
					sortCriteria.setDirection(SortCriteria.DIRECTION.ASCENDING);
				page.setSortCriteria(sortCriteria);
			}
			List<ModelObject> objects = page.getModelObjects() ;
			if (objects == null )
				objects = new ArrayList<ModelObject>() ;
			for (int k = 0 ; k < 100 ; k ++ ) {
				if (childObject[k] != null)
					objects.add((ModelObject)childObject[k]);
			}
			page.setModelObjects(objects);
		}
	}
	
	protected void readUICRUDPage(UICRUDPage page, HttpServletRequest request, ModelObject object ) throws Exception{
		if ( page != null && page.getForm() != null ) {
			String fixedActField = page.getTemplate().getFixedActionfield();
			String fixedAction = request.getParameter(fixedActField);
			String fixedActionParamField = page.getTemplate().getFixedActionParamfield();
			String submitAction = request.getParameter(page.getTemplate().getSubmitActionfield());
			if(!Utils.isNullString(submitAction))
				page.setSubmitAction(submitAction);
			
			if (!Utils.isNullString(fixedAction))
				page.setFixedAction(FixedAction.getFixedAction(fixedAction));
			String fixedActionParam = request.getParameter(fixedActionParamField);
			if (!Utils.isNullString(fixedActionParam))
				page.setFixedActionParam(fixedActionParam);
			if (!Utils.isNullList(page.getForm().getElements() )) {
				for (UIElement element : page.getForm().getElements() ) {
					if (element.getControl() instanceof UIMenu )
						 continue ;
					if (element instanceof UICondition ) {
						readConditionalContent((UICondition) element, request, object);
					}
					readControl(element, request, object);
				}
			}
		}
	}
	
	protected List<FilterNode> readFilterNode(UIElement element, HttpServletRequest request) {
		List<FilterNode> nodeList = new ArrayList<FilterNode>();
		if (!Utils.isNullString(element.getModelProperty()) &&  !(element.getControl() instanceof UITable) ){
			String value  =  request.getParameter(element.getControl().getId()) ;
			if (!Utils.isNull(value) ) {
				FilterNode node = new FilterNode();
				node.setField(element.getModelProperty());
				if(element instanceof UIFilterElement && !Utils.isNull(((UIFilterElement) element).getOperator())) {
					String op = ((UIFilterElement) element).getOperator() ;
					if (">".equals(op))
						node.setOperater(FilterNode.Operator.GREATER_THAN);
					else if (">=".equals(op))
						node.setOperater(FilterNode.Operator.GREATER_THAN_EQUAL);
					else if ("<".equals(op))
						node.setOperater(FilterNode.Operator.LESS_THAN);
					else if ("<=".equals(op))
						node.setOperater(FilterNode.Operator.LESS_THAN_EQUAL);
					else if ("EQUALS".equals(op))
						node.setOperater(FilterNode.Operator.EQUALS);
					else if ("IN".equals(op))
						node.setOperater(FilterNode.Operator.IN);
					else if ("NOT IN".equals(op))
						node.setOperater(FilterNode.Operator.IN);
				}else {
					node.setOperater(FilterNode.Operator.EQUALS);
				}
				node.setValue(value);
				nodeList.add(node);
				return nodeList ;
			}
		} else if (element.getControl() instanceof UITable) {
			UITable filterTable = (UITable)element.getControl() ;
			for (UITableRow row : filterTable.getInnerRows() ) {
				for (UITableCol col : row.getCols())  {
					if (!Utils.isNullList( col.getElements())) {
						for (UIElement innerElem  : col.getElements()) {
							List<FilterNode> innerList = readFilterNode(innerElem, request) ;
							nodeList.addAll(innerList);
						}
					}
				}
			}
		}
		return nodeList  ;
	}
	
	protected Filter readListFilter (UIFilterSet filterSet, HttpServletRequest request) throws RadsException{
		Filter filter = new Filter();
		if (filterSet != null && !Utils.isNullList(filterSet.getElements()) ) { 
			for (UIElement element : filterSet.getElements())  {
				List<FilterNode> nodeList =readFilterNode(element,request); 
				for (FilterNode node : nodeList)  {
					filter.addNode(node);
				}
			}
		}
		return filter;
	}
	
	
	protected void readUILstPage(UIListPage page, HttpServletRequest request) throws RadsException{
		if ( page != null ) {
			String fixedActField = page.getTemplate().getFixedActionfield();
			String submitActionField = page.getTemplate().getSubmitActionfield() ;
			String fixedActionParamField = page.getTemplate().getFixedActionParamfield(); 
			Filter filter  = readListFilter(page.getFilterSet(),request);
			page.setFilter(filter);
			String pageNumField = 	page.getPageNumElement().getId();
			String fixedAction = request.getParameter(fixedActField);
			String pageNum = request.getParameter(pageNumField);  
			String submitAction = request.getParameter(submitActionField) ;
			String fixedActionParam = request.getParameter(fixedActionParamField);
			
			String sortField = request.getParameter(RadsControlConstants.SORT_FIELD);
			String sortDirection = request.getParameter(RadsControlConstants.SORT_DIRECTION);
			
			if(!Utils.isNullString(sortField)){
				SortCriteria sortCriteria = new  SortCriteria();
				sortCriteria.setFieldName(sortField);
				if("DESC".equalsIgnoreCase(sortDirection))
					sortCriteria.setDirection(SortCriteria.DIRECTION.DESCENDING);
				else
					sortCriteria.setDirection(SortCriteria.DIRECTION.ASCENDING);
				page.setSortCriteria(sortCriteria);
			}
			
			String []selectedRows = request.getParameterValues(page.getUniquePropertyUIId()); 
			if ( selectedRows != null ) {
				List<String> selRecords = Arrays.asList(selectedRows);
				page.setSelectedRows(selRecords);
			}
			if(Utils.isPositiveInt(pageNum))
				page.setPageNumber(Integer.parseInt(pageNum));
			if (!Utils.isNullString(fixedAction))
				page.setFixedAction(FixedAction.getFixedAction(fixedAction));
			if (!Utils.isNullString(fixedActionParam))
				page.setFixedActionParam(fixedActionParam);
			if(!Utils.isNullString(submitAction))
				page.setSubmitAction(submitAction);
			
			
		}
	}
	
	protected void readUILookupPage(UILookupPage page, HttpServletRequest request , ModelObject object) throws RadsException,Exception {
		ILookupService lookup = page.getLookupSevice() ;
		String searchObject  =page.getSearchControl();
		String searchString = request.getParameter(searchObject);
		String lookupType = request.getParameter("lookupType");
		String parentControl = request.getParameter("parentControl");
		String dialogId =request.getParameter("dialogId");
		page.setLookupType(lookupType);
		page.setParentControl(parentControl);
		page.setSearchValue(searchString);
		page.setDialogId(dialogId);
		if (!Utils.isNullList(page.getForm().getElements() )) {
			for (UIElement element : page.getForm().getElements() ) {
				if (element.getControl() instanceof UIMenu )
					 continue ;
				if (element instanceof UICondition ) {
					readConditionalContent((UICondition) element, request, object);
				}
				readControl(element, request, object);
			}
		}
	}
	
	
	public HTMLReader(Object board) {
		super(board);
		
	}
	
	
	
	

}

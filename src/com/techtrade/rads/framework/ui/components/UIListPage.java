package com.techtrade.rads.framework.ui.components;

import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.ListController;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.filter.Filter;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.PageResult.ResponseAction;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.UIPage;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UIFilterSet;
import com.techtrade.rads.framework.ui.controls.UIHyperLink;
import com.techtrade.rads.framework.ui.controls.UIImage;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UINote;
import com.techtrade.rads.framework.ui.controls.UIRadioBox;
import com.techtrade.rads.framework.ui.templates.ListTemplateType;
import com.techtrade.rads.framework.utils.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class UIListPage extends UIPage
{
	
	
  List<UIButton> buttons;
  Filter filter;
  
  UITable table = null;
  List <UITableCol> columnDefs ;
  List<UIDataColumn> uiDataColumns;
  UIFilterSet filterSet ;
  List<ModelObject> objects;
  
  UIControl pageNumElement ; 
  
  int pageNumber = 1;
  int totalRecords ;
  String formName ; 

  String oddRowStyle ;
  String evenRowStyle ;
  String titleRowStyle;
  String uniqueProperty ;
  String  uniquePropertyUIId ;
  String allowRowSelection ;
  
  List <String> selectedRows ;
  String modelClass ;
  
  SortCriteria sortCriteria;
  
  private static final String MULTIPLE = "Multiple" ;
  private static final String SINGLE =  "Single" ;
  private static final String NONE =  "None" ;

  
  List<UIElement> innerElements = new ArrayList<UIElement>();

  
  public UIListPage() {
	  
	  
	 // setStyleSheet(RadsStyles.LISTSTYLESHEET);
  }
  
  
  
	public UIFilterSet getFilterSet() {
		return filterSet;
	}


	public void setFilterSet(UIFilterSet filterSet) {
		this.filterSet = filterSet;
	}


	public Filter getFilter() {
		return filter;
	  }
  
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public List<UIElement> getInnerElements() {
		return innerElements;
	  }
	
	 public void setInnerElements(List<UIElement> innerElements) {
		this.innerElements = innerElements;
	 }
 
	 public void addInnerElement(UIElement innerElement) {
	  if (innerElements == null ) {
		  innerElements = new ArrayList<UIElement> () ;
	  }
	  innerElements.add(innerElement);
	 }


	 public void addHeader(UITableHead head)
	 {
    if (this.table == null)
      this.table = new UITable();
    head.setStyle(titleRowStyle);
    this.table.addHeader(head);
    
  }

  public void addFooter(UITableFooter footer) {
    if (this.table == null)
      this.table = new UITable();
    this.table.addFooter(footer);
  }

  public void addRow(UITableRow row) {
    if (this.table == null)
      this.table = new UITable();
    this.table.addRow(row);
  }

  public List<UIButton> getButtons() {
    return this.buttons;
  }

  public void setButtons(List<UIButton> buttons) {
    this.buttons = buttons;
  }

  public void addButton(UIButton button) {
    if (this.buttons == null) {
      this.buttons = new ArrayList();
    }
    this.buttons.add(button);
  }

  public UITable getTable() {
    return this.table;
  }
  public void setTable(UITable table) {
    this.table = table;
  }

	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public UIControl getPageNumElement() {
		return pageNumElement;
	}

	public void setPageNumElement(UIControl pageNumElement) {
		this.pageNumElement = pageNumElement;
	}

	

	
	public List<String> getSelectedRows() {
		return selectedRows;
	}

	public void setSelectedRows(List<String> selectedRows) {
		this.selectedRows = selectedRows;
	}

	public String getUniqueProperty() {
		return uniqueProperty;
	}

	public void setUniqueProperty(String uniqueProperty) {
		this.uniqueProperty = uniqueProperty;
	}
	
	

	public String getAllowRowSelection() {
		return allowRowSelection;
	}

	public void setAllowRowSelection(String allowRowSelection) {
		this.allowRowSelection = allowRowSelection;
	}
	
	private void getPropertyonObject(Object object, String property,String value, UIElement element,StringBuffer propertyValue ) throws Exception{
		 if(property.contains(".")) {
			String subObjectProperty = property.substring(0, property.indexOf("."));
			String remainingProperty = property.substring(property.indexOf(".")+1,property.length());
			Method subObjectRead =  Utils.getterMethod(object.getClass(), subObjectProperty);//object.getClass().getMethod("get" + subObjectProperty);
			Object curObject = subObjectRead.invoke(object);
			if (curObject == null ) {
				curObject =   Class.forName(subObjectRead.getReturnType().getName()).newInstance();  //curObject.getClass().newInstance() ; 
				Method methodGet =  Utils.getterMethod(object.getClass(), subObjectProperty); //object.getClass().getMethod("get" + subObjectProperty);
				curObject = methodGet.invoke(object);
			}
			if(curObject != null )
				getPropertyonObject(curObject,remainingProperty, value,element,propertyValue );
		 }else { 	
	   	    Method methodRead =  Utils.getterMethod(object.getClass(), property); //object.getClass().getMethod("get" + Utils.initupper(property));
		    Object ret = methodRead.invoke(object, new Object [] {});
		    propertyValue.append(ret!=null?ret.toString():"") ;
		 }
	}
	
	protected UITableCol getDataAppliedCol(UIDataColumn headerCol, ModelObject object , int count) throws Exception {

		 UITableCol col = new UITableCol();
		ViewController controller = getViewController();
		for (UIElement element : headerCol.getElementImages()) {
			 String property =  element.getModelProperty();
			 String supplProperty =  element.getSupplementaryProperty();
			 StringBuffer propertyValue= new StringBuffer();
			 StringBuffer supplPropValue = new StringBuffer();
			 String value= String.valueOf(element.getConstantValue());
			 String id = element.getId();
			 if (!Utils.isNullString(property)) {
				 col.setDataProperty(property);
				 if (property.contains(((ListTemplateType)this.getTemplate()).getUniqueKeySeperator())) {
						StringTokenizer token  = new StringTokenizer(property, ((ListTemplateType)this.getTemplate()).getUniqueKeySeperator());
						while (token.hasMoreTokens()) {
							String prop = token.nextToken() ;
							Method methodRead =  object.getClass().getMethod("get" + prop);
							 Object ret = methodRead.invoke(object, new Object [] {});
							 propertyValue.append(ret.toString() + (token.hasMoreTokens()?((ListTemplateType)this.getTemplate()).getUniqueKeySeperator():"")) ;
						}
					}else {
							getPropertyonObject(object,property,value,element,propertyValue) ;
					}
				  
			} else if (!Utils.isNullString(value)) {
				propertyValue.append(value) ; 
			}
			 
			 if (!Utils.isNullString(supplProperty)) {
				 if (property.contains(((ListTemplateType)this.getTemplate()).getUniqueKeySeperator())) {
						StringTokenizer token  = new StringTokenizer(supplProperty, ((ListTemplateType)this.getTemplate()).getUniqueKeySeperator());
						while (token.hasMoreTokens()) {
							String prop = token.nextToken() ;
							Method methodRead =  object.getClass().getMethod("get" + prop);
							 Object ret = methodRead.invoke(object, new Object [] {});
							 supplPropValue.append(ret.toString() + (token.hasMoreTokens()?((ListTemplateType)this.getTemplate()).getUniqueKeySeperator():"")) ;
						}
					}else {
							getPropertyonObject(object,supplProperty,value,element,supplPropValue) ;
					}
				  
			} 
			 String rendered = element.getRendered();
			 String controlId = id ;
			if (!Utils.isNullString(rendered) ){
				element.setRendered(rendered);
			}
			if( element.shouldDisplay(object, controller)){
				element.setId(controlId);
				if (element.getControl() instanceof UICheckBox  ) {
					UICheckBox box = new UICheckBox(controlId);
					box.addOption(propertyValue.toString(),"");
					col.addElement(new UIElement(box));
				}else if (element.getControl() instanceof UIRadioBox  ) {
					UIRadioBox box = new UIRadioBox(controlId);
					box.addOption(propertyValue.toString(),"");
					col.addElement(new UIElement(box));
				}else if (element.getControl() instanceof UINote  ) {
					UINote note = new UINote(controlId);
					note.setNote(propertyValue.toString());
					col.addElement(new UIElement(note));
				}else if (element.getControl() instanceof UIHyperLink  ) {
					UIHyperLink link = new UIHyperLink(controlId);
					link.setHyperLink(propertyValue.toString());
					link.setInnerText(supplPropValue.toString());
					col.addElement(new UIElement(link));
				}else  	if (element.getControl() instanceof UIImage  ) {
					UIImage img = new UIImage(controlId);
					img.setSrc(((UIImage)element.getControl()).getSrc());
					img.setHeight(((UIImage)element.getControl()).getHeight());
					img.setWidth(((UIImage)element.getControl()).getWidth());
					col.addElement(new UIElement(img));
				} else 	if (element.getControl() instanceof UILabel  ){
					col.addElement(element);
					((UILabel)element.getControl()).setLabel(propertyValue.toString());
				} else 	{
					col.addElement(element);
					element.getControl().setValue(propertyValue.toString());
				}
				element.setValue(propertyValue);
				
			}
		}
		 return col;
	 
	}

	protected void applyListValues( List <ModelObject > objects,FixedAction fixedAction) throws Exception{
		int count = 0 ;
		for (ModelObject object : objects ) {
			UITableRow row = new UITableRow();
			 if ( count ++ % 2 ==0 ) {
				 row.setStyle(oddRowStyle);
			 }else {
				 row.setStyle(evenRowStyle);
			 }
			 for (UIDataColumn headerCol : uiDataColumns) {
				 UITableCol col = getDataAppliedCol(headerCol, object, count);
				 row.addCol(col);
			 }
			addRow(row);
		}
	}
	
	@Override
	public PageResult applyFixedAction( )throws Exception {
		FixedAction action = getFixedAction();
		PageResult res = new PageResult();
		if (!Utils.isNullList(getSelectedRows())) {
			for (String st :getSelectedRows() ) {
				ModelObject object =  instantiateModelObjectwithUK(st);
				if (objects == null) {
					objects = new ArrayList<ModelObject>();
				}
 				objects.add(object);
			}
		}
		if (action ==  FixedAction.NAV_FIRSTPAGE  || action == FixedAction.ACTION_APPLYFILTER) {
			pageNumber = 1;
			
		}else if (action ==  FixedAction.NAV_PREVPAGE) {
			if (pageNumber >=  2)
				pageNumber -- ;
		} else if (action ==  FixedAction.NAV_NEXTPAGE) {
			if (pageNumber <  ((ListController)getViewController()).getTotalNumberofPages())
			pageNumber ++ ;
		} else if  (action ==  FixedAction.NAV_LASTPAGE) {
			pageNumber = ((ListController)getViewController()).getTotalNumberofPages();
		}else if  (action ==  FixedAction.ACTION_GOEDITMODE  || action ==  FixedAction.ACTION_PRINT) { 
			objects =((ListController)getViewController()).populateFullObjectfromPK(objects);
			List<RadsError> errors = null ;
			if (action ==  FixedAction.ACTION_GOEDITMODE)
				errors = ((ListController)getViewController()).validateforEdit(objects);
			if (!Utils.isNullList(errors)){
				res.setErrors(errors);
				objects = ((ListController)getViewController()).getData(pageNumber,filter,sortCriteria);
				applyListValues(objects,action);
			}else {
				if (action ==  FixedAction.ACTION_GOEDITMODE)
					res = ((ListController)getViewController()).goToEdit(objects);
				else
					res = ((ListController)getViewController()).print(objects);
				if (!Utils.isNullList(objects))
					res.setObject(objects.get(0));
			}
			return res;
		}else if (action ==  FixedAction.ACTION_CLEARFILTER ){
			filter= null ;
			pageNumber = 1;
			res.setResponseAction(ResponseAction.FULLRELOAD);
		}else if (action ==  FixedAction.ACTION_SAVEFILTER ){
			((ListController)getViewController()).saveFilter(filter);
		}else if (action ==  FixedAction.ACTION_DELETE ){
			objects = ((ListController)getViewController()).populateFullObjectfromPK(objects);
			List<RadsError> errors = ((ListController)getViewController()).validateforDelete(objects);
			if (!Utils.isNullList(errors))
				res.setErrors(errors);
			else {
				 PageResult res1= ((ListController)getViewController()).delete(objects);
			}
		}
		
	    objects = ((ListController)getViewController()).getData(pageNumber,filter,sortCriteria);
	    long totalRecords = ((ListController)getViewController()).getTotalNumberofRecords() ;
		applyListValues(objects,action);
		res.setAvailableRecords(totalRecords);
		if(objects != null)
			res.setFetchedRecords(objects.size());
		return res;
		
	}
	
	
	protected ModelObject instantiateModelObjectwithUK(String key ) throws Exception {
		ModelObject object= (ModelObject)Class.forName(modelClass).newInstance() ;
		String property= getUniqueProperty();
		if (property.contains(((ListTemplateType)this.getTemplate()).getUniqueKeySeperator())) {
			StringTokenizer token  = new StringTokenizer(property, ((ListTemplateType)this.getTemplate()).getUniqueKeySeperator());
			String[] params = key.split(((ListTemplateType)this.getTemplate()).getUniqueKeySeperator()) ;
			int count = 0;
			while (token.hasMoreTokens()) {
				String prop = token.nextToken() ;
				Method methodRead =  object.getClass().getMethod("get" + prop);
				Method method = object.getClass().getMethod("set" + prop, new Class[] { methodRead.getReturnType() });
				if (params.length > count ) {
					Object param = Utils.stringToType(params[count ++ ], methodRead.getReturnType());
					method.invoke(object, param);
				}
			}
		}else {
			Method methodRead =  object.getClass().getMethod("get" + property);
			Method method = object.getClass().getMethod("set" + property, new Class[] { methodRead.getReturnType() });
			Object param = Utils.stringToType(key, methodRead.getReturnType());
			method.invoke(object, param);
		}
		return object;
	}

	public String getOddRowStyle() {
		return oddRowStyle;
	}

	public void setOddRowStyle(String oddRowStyle) {
		this.oddRowStyle = oddRowStyle;
	}

	public String getEvenRowStyle() {
		return evenRowStyle;
	}

	public void setEvenRowStyle(String evenRowStyle) {
		this.evenRowStyle = evenRowStyle;
	}
	
	public String getTitleRowStyle() {
		return titleRowStyle;
	}

	public void setTitleRowStyle(String titleRowStyle) {
		this.titleRowStyle = titleRowStyle;
	}

	public List<UITableCol> getColumnDefs() {
		return columnDefs;
	}

	public void setColumnDefs(List<UITableCol> columnDefs) {
		this.columnDefs = columnDefs;
	}

	public void addColumnDefs(UITableCol columnDef) {
		if (columnDefs == null ) {
			columnDefs = new ArrayList<UITableCol>();
		}
		
		columnDefs.add(columnDef);
	}
	public List<UIDataColumn> getUIDataColumns() {
		return uiDataColumns;
	}
	public void setUIDataColumns(List<UIDataColumn> uiDataColumns) {
		this.uiDataColumns = uiDataColumns;
	}
	public void addUIDataColumns(UIDataColumn uiDataColumn) {
		if (uiDataColumns == null)
			uiDataColumns = new ArrayList<UIDataColumn> ();
		uiDataColumns.add(uiDataColumn);
	}



	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	
	
	public String getUniquePropertyUIId() {
		return uniquePropertyUIId;
	}

	public void setUniquePropertyUIId(String uniquePropertyUIId) {
		this.uniquePropertyUIId = uniquePropertyUIId;
	}

	@Override
	public PageResult submit() throws Exception{
		List<ModelObject > objects = new ArrayList<ModelObject>();
		if (Utils.isNullList(getSelectedRows())) {
			objects = ((ListController)getViewController()).getData(pageNumber,filter,sortCriteria);
			applyListValues(objects,null);
			return new PageResult();
		}
		for (String st :getSelectedRows() ) {
			ModelObject object =  instantiateModelObjectwithUK(st);
			objects.add(object);
		}
		objects = ((ListController)getViewController()).populateFullObjectfromPK(objects);
		PageResult res=  ((ListController)getViewController()).submit(objects,getSubmitAction());
		if (Utils.isNullString(res.getNextPageKey())) {
			objects = ((ListController)getViewController()).getData(pageNumber,filter,sortCriteria);
			applyListValues(objects,null);
		}
		return res;
	}

	
	@Override
	public PageResult submit(String submitAction) throws Exception {
		return submit();
	}
	
	public List<ModelObject> getObjects() {
		return objects;
	}

	public void setObjects(List<ModelObject> objects) {
		this.objects = objects;
	}



	@Override
	public List<UIElement> getInputElements() {
		// TODO Auto-generated method stub
		return null;
	}



	public SortCriteria getSortCriteria() {
		return sortCriteria;
	}



	public void setSortCriteria(SortCriteria sortCriteria) {
		this.sortCriteria = sortCriteria;
	}

	   
	
	
  
  
}
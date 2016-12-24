package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.controller.abstracts.DataSheetController;
import com.techtrade.rads.framework.controller.abstracts.ListController;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.model.transaction.TransactionResult.Result;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.PageResult.ResponseAction;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.utils.Utils;

public class UIDataSheetPage extends UIListPage{
	List<UIElement> dataEntryColumns ;
	List<ModelObject> modelObjects;
	List<ModelObject> editingObjects;
	List<ModelObject> addingObjects;
	UITableRow rowForEntry ;

	
	public List<UIElement> getDataEntryColumns() {
		return dataEntryColumns;
	}
	public void setDataEntryColumns(List<UIElement> dataColumns) {
		this.dataEntryColumns = dataColumns;
	}
	public void addDataEntryColumn(UIElement dataColumn) {
		if (dataEntryColumns == null )
			dataEntryColumns = new ArrayList<UIElement> ();
		
		dataEntryColumns.add(dataColumn);
	}
	public List<ModelObject> getModelObjects() {
		return modelObjects;
	}
	public void setModelObjects(List<ModelObject> modelObjects) {
		this.modelObjects = modelObjects;
	}
	public void addModelObject(ModelObject modelObject) {
		if (modelObjects == null)
			modelObjects = new ArrayList<ModelObject>();
		this.modelObjects.add(modelObject);
	}
	@Override
	public PageResult applyFixedAction() throws Exception {
		FixedAction action = getFixedAction();
		DataSheetController objController = ((DataSheetController)getViewController());
		List<RadsError> errors = null;
		List<ModelObject > objects = new ArrayList<ModelObject>();
		if (!Utils.isNullList(getSelectedRows())) {
			for (String st :getSelectedRows() ) {
				ModelObject object =  instantiateModelObjectwithUK(st);
				objects.add(object);
			}
		}
		PageResult result = new PageResult();
		if (action == FixedAction.ACTION_CREATE)			
			errors = objController.validateforCreate() ;
		else if (action == FixedAction.ACTION_UPDATE) 
			errors = objController.validateforUpdate() ;
		else  if (action == FixedAction.ACTION_DELETE)  
			errors = objController.validateforDelete();
		if (Utils.isNullList(errors)) {
			if (action == FixedAction.ACTION_CREATE  )  {
				result=   objController.create();
				result.setResponseAction(ResponseAction.FULLRELOAD);
			}else if(action ==  FixedAction.ACTION_UPDATE)  {
				result = objController.update();
				result.setResponseAction(ResponseAction.FULLRELOAD);
			
			}else if  (action ==  FixedAction.ACTION_GOEDITMODE) { 
				editingObjects =((ListController)getViewController()).populateFullObjectfromPK(objects);
				errors = ((ListController)getViewController()).validateforEdit(editingObjects); 
				objects = ((ListController)getViewController()).getData(pageNumber,filter);
				if (!Utils.isNullList(errors)){
					result.setErrors(errors);
					applyListValues(objects,action);
				}else {
					applyListValues(objects,action);
				}
			}else
				result = super.applyFixedAction();
		}else {
			result.setResult(Result.FAILURE);
			result.setErrors(errors);
			addingObjects = new ArrayList(((DataSheetController)getViewController()).getObjects()) ;
			objects = new ArrayList( ((ListController)getViewController()).getData(pageNumber,filter));
			//applyListValues(objects, action);
			if (action == FixedAction.ACTION_CREATE)
				objController.setCreateMode();
			else if (action == FixedAction.ACTION_UPDATE) {
				objController.setUpdateMode();
				 editingObjects =((DataSheetController)getViewController()).getObjects() ;
			}else if (action == FixedAction.ACTION_DELETE)
				objController.setDeleteMode();
			
			applyListValues(objects, action);
		
				 
		}
		if (action == FixedAction.ACTION_GOADDMODE || action == FixedAction.ACTION_GOEDITMODE ) {
			result.setResponseAction(ResponseAction.RELOADSTATICCONTS);
		} else if (action == FixedAction.ACTION_PAGEFORWARD ) {
			result.setResponseAction(ResponseAction.NEWPAGE);
			result.setNextPageKey(getPageKey());
		}
		return result; 
	}
	
	private boolean containsValueBasedonPK(List<ModelObject> sourceObjects , ModelObject currentObject ) {
		if (Utils.isNullList(sourceObjects)) return false;
		for (ModelObject object : sourceObjects) {
			if (object.getPK().equals(currentObject.getPK())) 
				return true ;
		}
		return false;
	}
	@Override
	protected void applyListValues(List<ModelObject> objects,FixedAction action) throws Exception {
		int count = 0 ;
		for (ModelObject object : objects ) {
			UITableRow row = new UITableRow();
			 if ( count ++ % 2 ==0 ) {
				 row.setStyle(oddRowStyle);
			 }else {
				 row.setStyle(evenRowStyle);
			 }
			 if (!Utils.isNullList(editingObjects) &&  containsValueBasedonPK(editingObjects,object) &&
					 (FixedAction.ACTION_GOEDITMODE.equals(action) || FixedAction.ACTION_UPDATE.equals(action) ) ) {
				 getViewController().setUpdateMode();
				 for (UITableCol col : rowForEntry.getCols() ) {
					 UIDataColumn editingDataCol = new UIDataColumn();
					 if (Utils.isNullList(col.getElements()))  continue;
					 for (UIElement innerElement : col.getElements()  ) {
						 UIElement newImage  =(UIElement) innerElement.clone() ;
						 editingDataCol.addElementImage(newImage);
					 }
					 editingDataCol.setWidth(col.getWidth());
					 for (ModelObject editingObject : editingObjects) {
						 if(editingObject.getPK().equals(object.getPK())) {
							 UITableCol datCol = getDataAppliedCol(editingDataCol, editingObject, count);
							 row.addCol(datCol);
							 break;
						 }
					 }
				 }
			 }else {
				 for (UIDataColumn headerCol : uiDataColumns) {
					 UITableCol col = getDataAppliedCol(headerCol, object, count);
					 row.addCol(col);
				 }
			 }
			addRow(row);
		}
		
		if (action != null && FixedAction.ACTION_GOADDMODE.equals(action) || action != null && FixedAction.ACTION_CREATE.equals(action)) {
			getViewController().setCreateMode();
			if (FixedAction.ACTION_GOADDMODE.equals(action) )
				getTable().addRow(rowForEntry);
			else if (!Utils.isNullList(addingObjects)) {
				for (ModelObject object : addingObjects) {
					UITableRow row = new UITableRow();
					for (UITableCol col : rowForEntry.getCols() ) {
						 UIDataColumn addingDataCol = new UIDataColumn();
						 for (UIElement innerElement : col.getElements()  ) {
							 UIElement newImage  =(UIElement) innerElement.clone() ;
							 addingDataCol.addElementImage(newImage);
						 }
					 UITableCol colNew = getDataAppliedCol(addingDataCol, object, count);
					 row.addCol(colNew);
					}
					if ( count ++ % 2 ==0 ) {
						 row.setStyle(oddRowStyle);
					 }else {
						 row.setStyle(evenRowStyle);
					 }
					getTable().addRow(row);
				}
			}
		}
	}
	public UITableRow getRowForEntry() {
		return rowForEntry;
	}
	public void setRowForEntry(UITableRow rowForEntry) {
		this.rowForEntry = rowForEntry;
	}
	

}

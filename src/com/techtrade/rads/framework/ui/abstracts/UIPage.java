package com.techtrade.rads.framework.ui.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.controller.abstracts.IExternalizeFacade;
import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.controller.abstracts.CRUDController;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.components.AjaxGroup;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.constants.FixedAction;
import com.techtrade.rads.framework.ui.constants.RadsStyles;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UIErrorList;
import com.techtrade.rads.framework.ui.controls.UIErrorObject;
import com.techtrade.rads.framework.ui.controls.UIMenu;
import com.techtrade.rads.framework.ui.controls.UITab;
import com.techtrade.rads.framework.ui.controls.UITabSet;
import com.techtrade.rads.framework.ui.templates.TemplateType;
import com.techtrade.rads.framework.utils.Utils;


public abstract class UIPage  extends UIControl {

	String title ;
	List<String> styleSheets ;
	FixedAction fixedAction;
	String submitAction;
	String pageKey ;
	String pageMode; 
	
	List<PageForward> pageForwards ;
	List<RadsError> errors;
	List<String> includedJSs ;
	
	ViewController controller ;
	List<AjaxGroup> ajaxGroups ;
	
	IExternalizeFacade  externalizeFacade;
	
	
	TemplateType template ;
	
	public abstract PageResult submit() throws Exception ;
	
	public abstract PageResult applyFixedAction() throws Exception ;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public List<String> getStyleSheets() {
		if (Utils.isNullList(styleSheets))
			return getTemplate().getStyleSheets() ;
		else
			return styleSheets;
	}

	public void setStyleSheets(List<String> styleSheets) {
		this.styleSheets = styleSheets;
	}

	public ViewController getViewController() {
		return controller;
	}
	public void setViewController(ViewController controller) {
		this.controller = controller;
	}
	
	
	public FixedAction getFixedAction() {
		return fixedAction;
	}
	public void setFixedAction(FixedAction fixedAction) {
		this.fixedAction = fixedAction;
	}
	public List<PageForward> getPageForwards() {
		return pageForwards;
	}
	public void setPageForwards(List<PageForward> pageForwards) {
		this.pageForwards = pageForwards;
	}
		
	public void addPageForward(PageForward pageForward) {
		if (pageForwards ==null ) {
			pageForwards = new ArrayList <PageForward>();
		}
		pageForwards.add(pageForward);
	}
	
		
	public List<AjaxGroup> getAjaxGroups() {
		return ajaxGroups;
	}

	public void setAjaxGroups(List<AjaxGroup> ajaxGroups) {
		this.ajaxGroups = ajaxGroups;
	}
	
	public void addAjaxGroup(AjaxGroup ajaxGroup){
		if (ajaxGroups == null) {
			ajaxGroups = new ArrayList<AjaxGroup>();
		}
		ajaxGroups.add(ajaxGroup);
	}

	public PageForward getPageForwardforKey(String key) {
		for ( PageForward pageForward: pageForwards) {
			if (key.equalsIgnoreCase(pageForward.getKey())) {
				return pageForward;
			}
		}
		return null;
	}
	
	public List<RadsError> getErrors() {
		return errors;
	}

	public void setErrors(List<RadsError> errors) {
		this.errors = errors;
	}
	
	
	public void addError(RadsError err) {
		if(errors == null) 
			errors = new ArrayList<RadsError>();
		errors.add(err);
	}
	
	public UIErrorList getErrorList(){
		UIErrorList errorList = new UIErrorList();
		if (!Utils.isNullList(getErrors())) {
			for (RadsError error : getErrors()) {
				UIErrorObject object = new UIErrorObject();
				object.setMessage(error.getMessage());
				errorList.addErrorObjects(object);
			}
		}
		return errorList;
	}

	


	public String getSubmitAction() {
		return submitAction;
	}

	public void setSubmitAction(String submitAction) {
		this.submitAction = submitAction;
	}

	public TemplateType getTemplate() {
		return template;
	}

	public void setTemplate(TemplateType template) {
		this.template = template;
	}

	public List<String> getIncludedJSs() {
		return includedJSs;
	}

	public void setIncludedJSs(List<String> includedJSs) {
		this.includedJSs = includedJSs;
	}
	
	public void addIncludedJSs(String jsfile) {
		if (Utils.isNullList(includedJSs)) {
			includedJSs = new ArrayList<String> ();
		}
		includedJSs.add(jsfile);
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}
	
	

	
	public abstract List<UIElement> getInputElements();
	
	private  List<UIElement>  recurseForInputElements(UIElement element) {
		List<UIElement> innerElements=  new ArrayList<UIElement>();
		UIControl control = element.getControl();
		if (element instanceof UICondition) {
			List<UIElement> innerCondElements = getConditionInputElements((UICondition)element) ;
			innerElements.addAll(innerCondElements) ;
		} else if (control instanceof UITable) {
			List<UIElement> innertabElements = getTableInputElements((UITable)control) ;
			innerElements.addAll(innertabElements) ;
		}else if ((control instanceof UIDiv)) {
			List<UIElement> innerDivElements  = getDivInputElements((UIDiv) control);
			innerElements.addAll(innerDivElements) ;
		}else if ((control instanceof UITabSet)) {
			List<UIElement> innerTabSetElements  = getTabSetInputElements((UITabSet) control);
			innerElements.addAll(innerTabSetElements) ;
		} else if (control instanceof UIMenu  || control instanceof UIButton  ) {
			return innerElements;
		}else if (!Utils.isNullString(element.getModelProperty())){
			innerElements.add(element);
		}
		
		return innerElements;
	}
	
	private List<UIElement> getConditionInputElements(UICondition condition) {
		List<UIElement> conditionElements = new ArrayList<UIElement>();
		if (condition == null ||  Utils.isNullList(condition.getTrueElements()   )){
			return conditionElements;
		}
		for (UIElement elem : condition.getTrueElements())  {
			List<UIElement> innerElements = recurseForInputElements(elem) ;
			if (!Utils.isNullList(innerElements)) {
				conditionElements.addAll(innerElements);
			}
		}
		return conditionElements;

	}
	
	private List<UIElement> getTabSetInputElements(UITabSet tabset) {
		List<UIElement> tabSetElements = new ArrayList<UIElement>();
		if (tabset == null || Utils.isNullList(tabset.getTabs())){
			return tabSetElements;
		}
		for (UITab tab : tabset.getTabs()) {
			if (!Utils.isNullList(tab.getElements())) {
				for (UIElement element : tab.getElements()) {
					List<UIElement> innerElements = recurseForInputElements(element);
					if (!Utils.isNullList(innerElements)) {
						tabSetElements.addAll(innerElements);
					}
					if (Utils.isNullString(element.getModelProperty())) {
						tabSetElements.add(element);
					}
				}
			}
		}
		return tabSetElements;
	}
	
	private List<UIElement> getDivInputElements(UIDiv div) {
		List<UIElement> divInputElements = new ArrayList<UIElement>();
		if(div == null || Utils.isNullList(div.getElements()))
			return divInputElements ;
		for (UIElement elem : div.getElements())  {
			if (elem != null) {
				List<UIElement> innerElements = recurseForInputElements(elem) ;
				if (!Utils.isNullList(innerElements)) {
					divInputElements.addAll(innerElements);
				}
			}
		}
		return divInputElements;
	}
	
	private  List<UIElement> getTableInputElements(UITable table) {
		List<UIElement> tableInputElements = new ArrayList<UIElement>();
		String prefix = table.getDataProperty();
		if (Utils.isNullString(prefix))
				prefix = "";
		else 
			  prefix = prefix + ".";
		if(Utils.isNullList(table.getInnerRows()))
			return tableInputElements;
		for (UITableRow row  : table.getInnerRows()) {
			String dataType = row.getDataType() ;
			if(!Utils.isNullString(dataType) &&  prefix.contains("[]")) {
				String firstPartPrefix = prefix.substring(0, prefix.indexOf("[")+1);
				prefix = firstPartPrefix + dataType + "].";
			}
			for (UITableCol col :  row.getCols())  {
				if (!Utils.isNullList(col.getElements())) {
					for (UIElement elem : col.getElements())  {
						List<UIElement> innerElements = recurseForInputElements(elem) ;
						for (UIElement innerElement : innerElements ) {
							innerElement.setExtendedmodelProperty(prefix  + innerElement.getModelProperty());
						}
						if (!Utils.isNullList(innerElements)) {
							tableInputElements.addAll(innerElements);
						}
					/*	if (Utils.isNullString(elem.getModelProperty())){
							elem.setModelProperty(prefix + elem.getModelProperty());
							tableInputElements.add(elem);
						}*/
					}
				}
			}
		}
		return tableInputElements;
	}
	
	
	public  List<UIElement> getFormElements(UIForm form) {
		List<UIElement> formElements = new ArrayList<UIElement>();
		if (form != null) {
			for (UIElement element : form.getElements()) {
				if (element.getControl() instanceof UIMenu) continue;
				List <UIElement> innerElements = recurseForInputElements(element) ;
				if (!Utils.isNullList(innerElements))
					formElements.addAll(innerElements);
			}
		}
		return formElements;
	}


	public IExternalizeFacade getExternalizeFacade() {
		return externalizeFacade;
	}

	public void setExternalizeFacade(IExternalizeFacade externalizeFacade) {
		this.externalizeFacade = externalizeFacade;
	}
	
	
	
	

	
	
}

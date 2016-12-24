package com.techtrade.rads.framework.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techtrade.rads.framework.model.abstracts.DataType;
import com.techtrade.rads.framework.model.abstracts.RadsError;
import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.components.UICRUDPage;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIForm;
import com.techtrade.rads.framework.ui.components.UILeftNav;
import com.techtrade.rads.framework.ui.components.UIMainDataPanel;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.components.UIForm.METHOD;
import com.techtrade.rads.framework.ui.controls.UIBreak;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UIDate;
import com.techtrade.rads.framework.ui.controls.UIDiv;
import com.techtrade.rads.framework.ui.controls.UILabel;
import com.techtrade.rads.framework.ui.controls.UIList;
import com.techtrade.rads.framework.ui.controls.UILookupText;
import com.techtrade.rads.framework.ui.controls.UINote;
import com.techtrade.rads.framework.ui.controls.UIRadioBox;
import com.techtrade.rads.framework.ui.controls.UITab;
import com.techtrade.rads.framework.ui.controls.UITabSet;
import com.techtrade.rads.framework.ui.controls.UIText;

public class CustomerCRUDPage  extends UICRUDPage{
	Customer cust = new Customer();
	
	public CustomerCRUDPage( Customer cust) {
		super(cust);
		UILeftNav leftNav = new UILeftNav();
		addPanel(leftNav);
		UIMainDataPanel panel = new UIMainDataPanel();
		addPanel(panel);
		setTitle("Add Edit Customer");
		UIForm formNew =new UIForm("frmCust",METHOD.POST);
		panel.addElement(new UIElement(formNew));
		List <UIElement> uiElements  = new ArrayList<UIElement>();
		uiElements.add(new UIElement("Id",new UIText("txtId"),"Id"));
		uiElements.add(new UIElement("First Name",new UIText("txtfirstName"),"FirstName"));
		uiElements.add(new UIElement("Last Name",new UIText("txtlastName"),"LastName"));
		uiElements.add(new UIElement("Age",new UIText("txtAge"),"Age"));
		uiElements.add(new UIElement("Address",new UIText("txtAddress"),"Address"));
		Map<String , String>  states = new HashMap<String , String> (); 
		states.put("KR", "kerala");
		states.put("KA", "karnataka");
		states.put("TN", "TamilNadu");
		states.put("AN", "Andhra Pradesh");
		states.put("TL", "Telungana");
		UIList lst = new UIList("lstState",states);
		uiElements.add(new UIElement("State",lst,"State"));
		uiElements.add(new UIElement("Birthday",new UIDate("txtBirthday"),"Birthday"));
		Map <String,String> sx=  new HashMap<String,String>();
		sx.put("Male","Male");
		sx.put("Female","Female");
		UIRadioBox sex = new UIRadioBox("sx",sx);
		uiElements.add(new UIElement("Sex",sex,"Sex"));
		uiElements.add(new UIElement("SalesMan",new UILookupText("txtSalesMan","salesMan")));
		
		UINote note1 = new UINote("ABC") ;
		UINote note2 = new UINote("DEF") ;
		UINote note3 = new UINote("GHI") ;
		
		UITab tab1 = new UITab("t1","George Washington");
		UITab tab2 = new UITab("t2","Abraham Lincoln");
		UITab tab3 = new UITab("t3","Mahatma Gandhi");
		
		UITabSet tSet= new UITabSet("tS1");
		tab1.addElement(new UIElement(note1));
		tab1.addElement(new UIElement(new UIBreak()));
		tab1.addElement(new UIElement(new UINote("Was a President of United States")));
		
		tab2.addElement(new UIElement(note2));
		tab2.addElement(new UIElement(new UIBreak()));
		tab2.addElement(new UIElement(new UINote("Was Also a President of United States")));
		tab3.addElement(new UIElement(note3));
		tab3.addElement(new UIElement(new UIBreak()));
		tab3.addElement(new UIElement(new UINote("Was Father of the Nation- India")));
		tSet.addTab(tab1);
		tSet.addTab(tab2);
		tSet.addTab(tab3);
		UIElement elemtab = new UIElement( tSet);
		formNew.addElement(elemtab);
		
		UITable table  = UITable.tabularizeElements("TBL", uiElements, 2) ;
		formNew.addElement(new UIElement(table));
		formNew.addElement(new UIElement(new UIBreak()));
		
		
		List <UIElement> uiButtons  = new ArrayList<UIElement>();
		uiButtons.add(new UIElement(new UIButton("btnSumbit","Submit" ,"frmCust.submit();")));
		uiButtons.add(new UIElement(new UIButton("btnCancel","Cancel" ,"frmCust.submit();" )));
		
		UITableFooter footer = UITable.makeFooter(table, uiButtons, 1);
		table.addFooter(footer);
		
		//setForm(formNew);
	}

	/*
	@Override
	public PageResult submit() {
		PageResult result = new PageResult();
		Customer cust = (Customer)getObject();
		if(cust.getAge() < 18 ) {
			result.setResult(PageResult.Result.FAILURE);
			result.setNextPageKey("rdscontroller?page=CSXML");
			result.addError(new RadsError ("101", " Age cannot be under 18 "));
			return result ;
		}else {
			result.setNextPageKey("rdscontroller?page=CLXML");
			result.setResult(PageResult.Result.SUCCESS);
		}
		
		return result ;
	}*/
	
	
	
	

}

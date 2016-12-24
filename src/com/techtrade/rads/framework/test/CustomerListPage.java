package com.techtrade.rads.framework.test;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UIListPage;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableFooter;
import com.techtrade.rads.framework.ui.components.UITableHead;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.ui.controls.UIButton;
import com.techtrade.rads.framework.ui.controls.UICheckBox;
import com.techtrade.rads.framework.ui.controls.UINote;


public class CustomerListPage extends UIListPage{

	 List <Customer> custList = new ArrayList<Customer> ();
	 
	 public CustomerListPage() {
		 super() ;
		 setTitle("Customer List");
//		 setStyleSheet("./css/display.css");
		 UITableHead head = new UITableHead();
		 UITableRow headRow = new UITableRow();
		 headRow.addCol(new UITableCol(new UIElement(new UINote(" Customer List")),null,"6"));
		 head.setRow(headRow);
		 addHeader(head);
		 
		 UITableHead titleHead = new UITableHead();
		 UITableRow titleRow = new UITableRow();
		 titleRow.addCol( new UITableCol("Select"));
		 titleRow.addCol( new UITableCol("Id"));
		 titleRow.addCol( new UITableCol("Name"));
		 titleRow.addCol( new UITableCol("Age"));
		 titleRow.addCol( new UITableCol("Sex"));
		 titleRow.addCol( new UITableCol("State"));
		 titleHead.setRow(titleRow);
		 //titleHead.setStyle("subTitle");
		 
		 addHeader(titleHead);
		 for ( int  i = 0 ; i < 10 ; i ++ ) {
		 UITableRow row = new UITableRow();
		 if ( i % 2 ==0 ) {
			 row.setStyle("oddRow");
		 }else {
			 row.setStyle("evenRow");
		 }
		 row.addCol( new UITableCol(new UIElement(new UICheckBox("chk" + i,"")), "5%") );
		 row.addCol( new UITableCol(new UIElement(new UINote("id" + i)),"18%"));
		 row.addCol( new UITableCol(new UIElement(new UINote("Name" + i)),"18%"));
		 row.addCol( new UITableCol(new UIElement(new UINote("Age" + i)),"18%"));
		 row.addCol( new UITableCol(new UIElement(new UINote("State" + i)),"18%"));
		 row.addCol( new UITableCol(new UIElement(new UINote("Sex" + i)),"18%"));
		 addRow(row);
		 
		 }
		 
		UITableFooter footer = new  UITableFooter();
		UITableRow footerRow = new UITableRow();
		UITableCol colEnd  = new UITableCol();
		colEnd.addElement(new UIElement(new UIButton("btn1","Delete","")));
		colEnd.addElement(new UIElement(new UIButton("btn2","Details","")));
		colEnd.addElement(new UIElement(new UIButton("btn3","Add","")));
		colEnd.setColSpan("6");
		footerRow.addCol(colEnd);
		footer.setRow(footerRow);
		//footer.setStyle("summary");
		addFooter(footer);
		
	 }

}

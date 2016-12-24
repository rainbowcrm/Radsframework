package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.filter.FilterNode;
import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.components.UITable;
import com.techtrade.rads.framework.ui.components.UITableCol;
import com.techtrade.rads.framework.ui.components.UITableRow;
import com.techtrade.rads.framework.utils.Utils;

public class UIFilterSet extends UIControl{
	
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
	
	public List<UIElement> getAllFilterElements() {
		List<UIElement> ans = new ArrayList<UIElement> ();
		if (Utils.isNullList(elements)) return ans ;
		for (UIElement element  : elements  ) {
			if (element.getControl() instanceof UITable) {
				UITable filterTable = (UITable)element.getControl() ;
				for (UITableRow row : filterTable.getInnerRows() ) {
					for (UITableCol col : row.getCols())  {
						if (!Utils.isNullList( col.getElements())) {
							for (UIElement innerElem  : col.getElements()) {
								ans.add(innerElem);
							}
						}
					}
				}
			} else {
				ans.add(element);
			}
		}
		return ans ;
	}
	
	
}
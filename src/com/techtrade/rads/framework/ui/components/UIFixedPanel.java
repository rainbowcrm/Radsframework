package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.constants.RadsStyles;
import com.techtrade.rads.framework.utils.Utils;


public class UIFixedPanel extends UIControl{
   
	 List<UIElement> elements;
	 String style;

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

		public String getStyle() {
				return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}
		
		

}

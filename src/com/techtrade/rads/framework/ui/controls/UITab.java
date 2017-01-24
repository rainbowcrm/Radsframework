package com.techtrade.rads.framework.ui.controls;

import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;

public class UITab extends UIControl {
	UIDiv div;
	UILabel label;
	boolean visible;
	String width;
	int index;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public UITab(String id, String title) {
		setId(id);
		label = new UILabel("lbl_" + id);
		label.setLabel(title);
	}

	public List<UIElement> getElements() {
		if (div == null)
			return null;
		else
			return div.getElements();
	}

	public void setElements(List<UIElement> elements) {
		if (div == null)
			div = new UIDiv();
		div.setElements(elements);
	}

	public void addElement(UIElement element) {
		if (div == null)
			div = new UIDiv();
		div.addElement(element);
	}

	public UILabel getLabel() {
		return label;
	}

	public void setLabel(UILabel label) {
		this.label = label;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
}

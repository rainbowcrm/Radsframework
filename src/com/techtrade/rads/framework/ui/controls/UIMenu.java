package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIMenu extends UIControl {
	String menuText;
	String menuLink ;
	String iconStyle;
	
	
	List <UIMenu> childMenus ;
	String groupId; 
	
	public String getIconStyle() {
		return iconStyle;
	}
	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	
	public UIMenu(String id) {
		super(id);
		setExternalize(true);
	}
	public List<UIMenu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<UIMenu> childMenus) {
		this.childMenus = childMenus;
	}
	public void addChileMenu(UIMenu childMenu) {
		if (childMenus == null )
			childMenus = new ArrayList<UIMenu> ();
		childMenus.add(childMenu);
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	

}

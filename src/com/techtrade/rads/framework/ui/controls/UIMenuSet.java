package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

import java.util.ArrayList;
import java.util.List;

public class UIMenuSet extends UIControl {
    List<UIMenu> menus ;

    public List<UIMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<UIMenu> menus) {
        this.menus = menus;
    }

    public void addMenu(UIMenu menu) {
        if( menus == null)
             menus = new ArrayList<>() ;
        this.menus.add(menu);
    }
}

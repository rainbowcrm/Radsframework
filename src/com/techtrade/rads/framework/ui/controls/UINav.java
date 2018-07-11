package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.components.UIElement;

import java.util.ArrayList;
import java.util.List;

public class UINav extends UIDiv {

    List<UIElement> elements = null;
    public UINav(String id) 	{
        setId(id);
    }

    public UINav() 	{

    }
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

}

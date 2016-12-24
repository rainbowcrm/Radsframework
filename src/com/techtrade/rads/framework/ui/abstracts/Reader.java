package com.techtrade.rads.framework.ui.abstracts;

import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.components.UIElement;

public  abstract class Reader {
	
	protected Object board ; 
	public abstract void read(UIControl control, ModelObject object, String modelProperty) throws RadsException ;
	
	public Reader(Object board) {
		super();
		this.board = board;
	}

}

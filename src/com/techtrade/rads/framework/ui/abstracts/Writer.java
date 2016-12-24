package com.techtrade.rads.framework.ui.abstracts;

import com.techtrade.rads.framework.controller.abstracts.ViewController;
import com.techtrade.rads.framework.exceptions.RadsException;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.components.UIElement;


public abstract class Writer {

	protected Object board ; 
	public abstract void write (UIPage page, ModelObject object) throws RadsException;
	public Writer(Object board) {
		super();
		this.board = board;
	}
	
	
}

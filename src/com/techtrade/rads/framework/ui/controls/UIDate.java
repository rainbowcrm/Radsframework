package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.constants.RadsConstants;
import com.techtrade.rads.framework.utils.Utils;

public class UIDate  extends UIControl{
   
	String format;
	
	public UIDate (String id) {
		setId(id);
	}

	public String getFormat() {
		if (Utils.isNullString(format))
			return RadsConstants.DEFAULT.getDateFormat();
		else
			return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	
	
	
}

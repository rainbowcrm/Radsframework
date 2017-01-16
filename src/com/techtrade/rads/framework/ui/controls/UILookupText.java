package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.constants.RadsConstants;
import com.techtrade.rads.framework.utils.Utils;

public class UILookupText extends UIControl {
	
	int size ;
	String imgSrc ;
	String url ;
	String windowHeight;
	String windowWidth ;
	String lookupWindowTitle;
	String lookupType ;
	/** 
	 * These props are required if the lookup is rendered as a dialog
	 */
	boolean showLookupAsDialog  ;
	String dialogId ;
	String dialogStyle;
	String frameStyle;
	
	
	String additionalInputControl;
	
	
	public UILookupText (String id) {
		setId(id);
	}
	public UILookupText(String id, String lookupType) {
		super(id);
		this.lookupType = lookupType;
	}
	public String getImgSrc() {
		if(!Utils.isNullString(imgSrc))
			return imgSrc;
		else
			return RadsConstants.DEFAULT.getLookupIcon() ;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getUrl() {
		if (Utils.isNullString(url))
			return RadsConstants.DEFAULT.getLookupURL() ;
		else
			return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWindowHeight() {
		if (Utils.isNullString(windowHeight))
			return RadsConstants.DEFAULT.getLookupWindowHeight();
		else
			return windowHeight;
	}
	public void setWindowHeight(String windowHeight) {
		this.windowHeight = windowHeight;
	}
	public String getWindowWidth() {
		if (Utils.isNullString(windowWidth))
			return RadsConstants.DEFAULT.getLookupWindowWidth();
		else
			return windowWidth;
	}
	public void setWindowWidth(String windowWidth) {
		this.windowWidth = windowWidth;
	}
	public String getLookupWindowTitle() {
		if (Utils.isNullString(lookupWindowTitle))
			return RadsConstants.DEFAULT.getLookupWindowTitle();
		else
			return lookupWindowTitle;
	}
	public void setLookupWindowTitle(String lookupWindowTitle) {
		this.lookupWindowTitle = lookupWindowTitle;
	}
	public String getLookupType() {
		return lookupType;
	}
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	public boolean isShowLookupAsDialog() {
		return showLookupAsDialog;
	}
	public void setShowLookupAsDialog(boolean showLookupAsDialog) {
		this.showLookupAsDialog = showLookupAsDialog;
	}
	public String getDialogId() {
		return dialogId;
	}
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
	public String getDialogStyle() {
		return dialogStyle;
	}
	public void setDialogStyle(String dialogStyle) {
		this.dialogStyle = dialogStyle;
	}
	public String getFrameStyle() {
		return frameStyle;
	}
	public void setFrameStyle(String frameStyle) {
		this.frameStyle = frameStyle;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getAdditionalInputControl() {
		return additionalInputControl;
	}
	public void setAdditionalInputControl(String additionalInputControl) {
		this.additionalInputControl = additionalInputControl;
	}
	
	
	

}

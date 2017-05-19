package com.techtrade.rads.framework.ui.controls;

import com.techtrade.rads.framework.ui.abstracts.UIControl;

public class UIHyperLink extends UIControl {
	String hyperLink ;
	String innerText;
	public String getHyperLink() {
		return hyperLink;
	}
	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}
	public String getInnerText() {
		return innerText;
	}
	public void setInnerText(String innerText) {
		this.innerText = innerText;
	}
	public UIHyperLink(String id,String hyperLink, String innerText) {
		setId(id);
		this.hyperLink = hyperLink;
		this.innerText = innerText;
		setExternalize(true);
	}
	public UIHyperLink(String id) {
		setId(id);
		setExternalize(true);
	}
	
	
	

}

package com.techtrade.rads.framework.ui.abstracts;

public class PageForward {

	String key ;
	String link ;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public PageForward(String key, String link) {
		super();
		this.key = key;
		this.link = link;
	}
	
	
}

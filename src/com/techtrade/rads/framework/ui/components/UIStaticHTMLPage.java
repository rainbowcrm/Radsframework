package com.techtrade.rads.framework.ui.components;

import com.techtrade.rads.framework.ui.abstracts.PageResult;
import com.techtrade.rads.framework.ui.abstracts.UIPage;

import java.util.ArrayList;
import java.util.List;

public class UIStaticHTMLPage extends UIPage {

    String htmlPage ;

    public String getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    @Override
    public PageResult submit() throws Exception {
        return null;
    }

    @Override
    public PageResult submit(String submitAction) throws Exception {
        return null;
    }

    @Override
    public PageResult applyFixedAction() throws Exception {
        return null;
    }

    @Override
    public List<UIElement> getInputElements() {
        return super.getFormElements(null);

    }

}

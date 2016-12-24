package com.techtrade.rads.framework.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	List<FilterNode> nodeList;

	public List<FilterNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<FilterNode> nodeList) {
		this.nodeList = nodeList;
	}
	
	
	public void addNode(FilterNode node ){
		//node.setOperater(FilterNode.Operator.EQUALS);
		if (nodeList == null ) {
			nodeList = new ArrayList<>();
		}
		nodeList.add(node);
	}
	
	

}

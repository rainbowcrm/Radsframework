package com.techtrade.rads.framework.model.graphdata;

import java.util.ArrayList;
import java.util.List;

public class PieChartData {

	List<PieSliceData>  pieSlices;
	String footerNote;
	
	public List<PieSliceData> getPieSlices() {
		return pieSlices;
	}

	public void setPieSlices(List<PieSliceData> pieSlices) {
		this.pieSlices = pieSlices;
	} 
	
	public void addPieSlice(PieSliceData pieSlice) {
		if (pieSlices == null)
			   pieSlices = new ArrayList<PieSliceData>();
		pieSlices.add(pieSlice);
	}

	public String getFooterNote() {
		return footerNote;
	}

	public void setFooterNote(String footerNote) {
		this.footerNote = footerNote;
	}
	
	
	
	
	
}

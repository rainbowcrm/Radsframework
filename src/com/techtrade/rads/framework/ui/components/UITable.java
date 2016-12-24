package com.techtrade.rads.framework.ui.components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.ui.controls.UICondition;
import com.techtrade.rads.framework.ui.controls.UIHidden;
import com.techtrade.rads.framework.utils.Utils;

public class UITable extends UIControl {

	private static class Queue<E> {
	
		private LinkedList<E> list = new LinkedList<E>();
		   public void enqueue(E item) {
		      list.addLast(item);
		   }
		   public E dequeue() {
		      return list.poll();
		   }
		   public boolean hasItems() {
		      return !list.isEmpty();
		   }
		   public int size() {
		      return list.size();
		   }
		  
	}

	
	int noRows, noColumns ;
	String border = null ;
	String width ;
	List<UITableRow> innerRows = null;
	List<UITableHead> headers = null ;
	List<UITableFooter> footers = null ;
	
	
	public UITable(int noRows,int noColumns) 	{
		this.noRows = noRows;
		this.noColumns = noColumns;
		
	}
	
	public UITable(){
		
	}
	public UITable(String id, int noRows,int noColumns) 	{
		setId(id);
		this.noRows = noRows;
		this.noColumns = noColumns;
		
	}
	

	public UITable(String id) 	{
		setId(id);
	}
 
	
	public static UITableFooter makeFooter(UITable  currentTable, List<UIElement> elements,int noCols  ) {
		int rowCount = currentTable.getInnerRows().size() ;
		UITableRow lastRow = currentTable.getInnerRows().get(rowCount-1);
		int prevRowtotalCols  = lastRow.getCols().size() ;
		int colSpan = prevRowtotalCols / noCols ;
		int maxElementsPerCol = elements.size() / noCols ;
		UITableFooter footer = new UITableFooter();
		UITableRow footerRow = new UITableRow();
		int curCol = 0 ;
		UITableCol colEnd = new UITableCol();
		for (UIElement element : elements) {
			if (curCol  == 0 ) {
				footerRow.addCol(colEnd);
				colEnd.setColSpan(String.valueOf(colSpan));
			}
			colEnd.addElement(element);
			curCol ++ ;
			if (curCol >= maxElementsPerCol) {
				curCol = 0;
				colEnd = new  UITableCol();
			}
		}
		footer.setRow(footerRow);
		return footer;
	}
	
	
	public static UITable tabularizeElements(String id ,List<UIElement> elements,int norows, int noCols) {
		UITable table  = new UITable(id);
		UITableRow row = new UITableRow();
		int curCol = 0 ;
		if (norows == -1 ) {
			return tabularizeElements(id, elements, noCols);
		}
		if (!Utils.isNullList(elements)) {
			int totalNoCells = norows * noCols ;
			int cellsPerCol =  elements.size() / totalNoCells ;
			int count = 0 ;
			UITableCol col  = new UITableCol();
			for (int i = 0 ; i < norows ; i ++ )  {
				for (int k = 0 ;  k < noCols ; k ++ ) { 
					for ( int l = 0 ; l < cellsPerCol ; l ++ ) {
						if (elements.size() > count )  {
							col.addElement(elements.get(count ++));
						}
					}
					row.addCol(col);
					col = new UITableCol();
				}
				table.addRow(row);
				row = new UITableRow();
			}
		}
		return table;
	}
	
	
	
	public static UITable tabularizeElements(String id ,List<UIElement> elements, int noCols) {
		
		UITable table  = new UITable(id);
		UITableRow row = new UITableRow();
		int curCol = 0 ;
		Queue<UIElement> queue = new Queue<UIElement>();
		if (!Utils.isNullList(elements)) {
			for (UIElement element : elements) {
				/*
				 * To be visited Later.. 
				 * if (element.getControl() instanceof UICondition)  {
					table = tabularizeElements(id, ((UICondition)element.getControl()).getTrueElements(), noCols);
					continue;
				}*/
				
				if (element.getControl() !=null && element.getControl() instanceof UIHidden) {
					queue.enqueue(element);
					continue;
				}
				if (element.isShowInPrevCol()) {
					if (!Utils.isNullList(table.getInnerRows()) && table.getInnerRows().size() >= 1) {
						UITableRow lastTableRow= table.getInnerRows().get(table.getInnerRows().size()-1);
						if (!Utils.isNullList(lastTableRow.getCols()) && lastTableRow.getCols().size() >= 1) {
						UITableCol lastCol = lastTableRow.getCols().get(lastTableRow.getCols().size()-1);
							if (lastCol != null){
								lastCol.addElement(element);
								continue;
							}
						}
					}
						
				}
				if (curCol == 0 ) {
					table.addRow(row);
				}
				curCol ++ ;
				if (queue.hasItems()) {
					UITableCol col =  new UITableCol();
					while (queue.hasItems()) {
						UIElement  hidn = queue.dequeue();
						col.addElement(hidn);
					}
					col.addElement(element);
					row.addCol(col);
				} else {
					
					UITableCol col =  new UITableCol(element);
					row.addCol(col);
				}
				
				if (curCol == noCols) {
					curCol = 0;
					row = new UITableRow();
				}
			}
			for (int j = noCols ; j > curCol ; j -- ) {
				UITableCol col =  new UITableCol();
				row.addCol(col);
			}
		}
		if (queue.hasItems()) {
			UITableRow lastRow = table.getInnerRows().get(table.getInnerRows().size()-1);
			UITableCol col =  lastRow.getCols().get(lastRow.getCols().size() -1) ;
			while (queue.hasItems()) {
				UIElement  hidn = queue.dequeue();
				col.addElement(hidn);
			}
		}
		return table;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public int getNoRows() {
		return noRows;
	}

	public void setNoRows(int noRows) {
		this.noRows = noRows;
	}

	public int getNoColumns() {
		return noColumns;
	}

	public void setNoColumns(int noColumns) {
		this.noColumns = noColumns;
	}

	public List<UITableRow> getInnerRows() {
		return innerRows;
	}

	public void setInnerRows(List<UITableRow> innerRows) {
		this.innerRows = innerRows;
	}

	public void addRow (UITableRow row ) {
		if (innerRows == null) {
			innerRows = new ArrayList<UITableRow>();
		}
		innerRows.add(row);
	}
	
	

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public List<UITableHead> getHeaders() {
		return headers;
	}

	public void setHeaders(List<UITableHead> headers) {
		this.headers = headers;
	}

	public void addHeader(UITableHead header) {
		if (headers == null) {
			headers = new ArrayList<UITableHead>();
		}
		headers.add(header);
		
	}

	public List<UITableFooter> getFooters() {
		return footers;
	}

	public void setFooters(List<UITableFooter> footers) {
		this.footers = footers;
	}
	
	public void addFooter(UITableFooter footer){
		if(footers ==null ) {
			footers = new ArrayList<UITableFooter> ();
		}
		footers.add(footer);
	}
	
	
	

}

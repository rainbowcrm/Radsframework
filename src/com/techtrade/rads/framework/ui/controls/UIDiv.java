package com.techtrade.rads.framework.ui.controls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.techtrade.rads.framework.ui.abstracts.UIControl;
import com.techtrade.rads.framework.ui.abstracts.Writer;
import com.techtrade.rads.framework.ui.components.UIElement;
import com.techtrade.rads.framework.utils.Utils;

public class UIDiv extends UIControl{

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
	
	List<UIElement> elements = null;
	
	String width;
	String align;

	public UIDiv(String id) 	{
		setId(id);
	}

	public UIDiv() 	{

	}
	public List<UIElement> getElements() {
		return elements;
	}

	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}
	
	
	public void addElement (UIElement element) {
		if (elements == null) 
			elements = new ArrayList<UIElement>();
		elements.add(element);
	}
	
	public void insertAt(UIElement presentElement, List<UIElement> newElements ) {
		List<UIElement> newList = new ArrayList<UIElement>();
		for(UIElement element : elements) {
			if(element!= null && element.equals(presentElement)) {
				for (UIElement newElement : newElements) {
					newList.add(newElement);
				}
			}else {
				newList.add(element);
			}
		}
		elements = newList;
			
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}


	public static UIDiv tabularizeElements(String id ,List<UIElement> elements,int norows, int noCols, String rowStyle, String colStyle) {
		UIDiv table  = new UIDiv(id);
		UIDiv row = new UIDiv();
		int curCol = 0 ;
		if (norows == -1 ) {
			return tabularizeElements(id, elements, noCols,rowStyle,colStyle);
		}
		if (!Utils.isNullList(elements)) {
			int totalNoCells = norows * noCols ;
			int cellsPerCol =  elements.size() / totalNoCells ;
			int count = 0 ;
			UIDiv col  = new UIDiv();
			col.setStyle(colStyle);
			for (int i = 0 ; i < norows ; i ++ )  {
				for (int k = 0 ;  k < noCols ; k ++ ) {
					for ( int l = 0 ; l < cellsPerCol ; l ++ ) {
						if (elements.size() > count )  {
							col.addElement(elements.get(count ++));
						}
					}
					row.addElement(new UIElement(col));
					row.setStyle(rowStyle);
					col = new UIDiv();
					col.setStyle(colStyle);
				}
				table.addElement(new UIElement(row));
				row = new UIDiv();
				row.setStyle(rowStyle);
			}
		}
		return table;
	}



	public static UIDiv tabularizeElements(String id ,List<UIElement> elements, int noCols, String rowStyle, String colStyle) {

		UIDiv table  = new UIDiv(id);
		UIDiv row = new UIDiv();
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
				if(element instanceof UICondition && element.getControl() == null && Utils.isNullList(((UICondition)element).getTrueElements())) {
					queue.enqueue(element);
					continue;
				}

				if (element.getControl() !=null && element.getControl() instanceof UIHidden) {
					queue.enqueue(element);
					continue;
				}
				if (element.isShowInPrevCol()) {
					if (!Utils.isNullList(table.getElements()) && table.getElements().size() >= 1) {
						UIDiv lastTableRow= (UIDiv)table.getElements().get(table.getElements().size()-1).getControl();
						if (!Utils.isNullList(lastTableRow.getElements()) && lastTableRow.getElements().size() >= 1) {
							UIDiv lastCol = (UIDiv) lastTableRow.getElements().get(lastTableRow.getElements().size()-1).getControl();
							if (lastCol != null){
								lastCol.addElement(element);
								continue;
							}
						}
					}

				}
				if (curCol == 0 ) {
					table.addElement(new UIElement(row));
				}
				curCol ++ ;
				if (queue.hasItems()) {
					UIDiv col =  new UIDiv();
					while (queue.hasItems()) {
						UIElement  hidn = queue.dequeue();
						col.addElement(hidn);
					}
					col.addElement(element);
					row.addElement(new UIElement(col));
				} else {

					UIDiv col =  new UIDiv();
					col.addElement(element);
					row.addElement(new UIElement(col));
				}

				if (curCol == noCols) {
					curCol = 0;
					row = new UIDiv();
				}
			}
			for (int j = noCols ; j > curCol ; j -- ) {
				UIDiv col =  new UIDiv();
				row.addElement(new UIElement(col));
			}
		}
		if (queue.hasItems()) {
			UIDiv lastRow = (UIDiv) table.getElements().get(table.getElements().size()-1).getControl();
			UIDiv col = (UIDiv) lastRow.getElements().get(lastRow.getElements().size() -1).getControl() ;
			while (queue.hasItems()) {
				UIElement  hidn = queue.dequeue();
				col.addElement(hidn);
			}
		}
		return table;
	}


}

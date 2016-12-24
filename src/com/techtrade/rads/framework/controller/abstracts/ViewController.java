package com.techtrade.rads.framework.controller.abstracts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.model.abstracts.ModelObject;
import com.techtrade.rads.framework.ui.abstracts.PageResult;

public abstract class ViewController {
   	
   IRadsContext context;	
  
   public enum Mode {
		CREATE , READ, UPDATE, DELETE ;
	};
	 
	protected Mode mode;
	
	public void setReadMode () {
		mode = Mode.READ ;
	}
	
	public void setCreateMode () {
		mode = Mode.CREATE ;
	}
	
	public void setUpdateMode() {
		mode = Mode.UPDATE ;
	}
	
	public void setDeleteMode () {
		mode = Mode.DELETE ;
	}
	
	
	public boolean isReadMode() {
		return (mode == Mode.READ) ;
	}
	
	public boolean isCreateMode() {
		return (mode == Mode.CREATE) ;
	}
	
	public boolean isCreateorEditMode() {
		return ((mode == Mode.CREATE) ||  (mode == Mode.UPDATE)) ;
	}
	
	public boolean isUpdateMode() {
		return (mode == Mode.UPDATE) ; 
	}
	
	public boolean isDeleteMode() {
		return (mode == Mode.UPDATE) ; 
	}

	   
   public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void init(HttpServletRequest request ) {
	   
   }

	public IRadsContext getContext() {
		return context;
	}
	
	public void setContext(IRadsContext context) {
		this.context = context;
	}
   
   
   
    public abstract IRadsContext generateContext(HttpServletRequest request,HttpServletResponse response);
    
    public abstract IRadsContext generateContext(String authToken);
	   
  
}

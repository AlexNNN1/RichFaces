package org.mypack.controllers;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseActionButton implements Serializable {
	private Boolean disabled = true;

	public void action(){
		
	}
	
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	

}

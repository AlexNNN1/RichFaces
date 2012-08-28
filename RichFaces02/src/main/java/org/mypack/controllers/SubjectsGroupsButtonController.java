package org.mypack.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsGroupsButtonController implements Serializable {
	
	public SubjectsGroupsButtonController() {
		newButton = new BaseActionButton();
		editButton = new BaseActionButton();
		cutButton = new BaseActionButton();
		pasteButton = new BaseActionButton();
	}

	public BaseActionButton getNewButton() {
		return newButton;
	}

	public void setNewButton(BaseActionButton newButton) {
		this.newButton = newButton;
	}

	public BaseActionButton getEditButton() {
		return editButton;
	}

	public void setEditButton(BaseActionButton editButton) {
		this.editButton = editButton;
	}

	public BaseActionButton getCutButton() {
		return cutButton;
	}

	public void setCutButton(BaseActionButton cutButton) {
		this.cutButton = cutButton;
	}

	public BaseActionButton getPasteButton() {
		return pasteButton;
	}

	public void setPasteButton(BaseActionButton pasteButton) {
		this.pasteButton = pasteButton;
	}

	
	public Boolean getToolBarVisible() {
		return toolBarVisible;
	}

	public void setToolBarVisible(Boolean toolBarVisible) {
		this.toolBarVisible = toolBarVisible;
	}
	
	public void newButtonExecute() {
		System.out.println("newButtonExecure");
	}
	
	public String getNewButtonExecute() {
		System.out.println("newButtonExecure");
		return newButtonExecute;
	}

	public void setNewButtonExecute(String newButtonExecute) {
		this.newButtonExecute = newButtonExecute;
		System.out.println("newButtonExecure");
	}



	public Boolean toolBarVisible;
	private BaseActionButton newButton;
	private BaseActionButton editButton;
	private BaseActionButton cutButton;
	private BaseActionButton pasteButton;
	private  String newButtonExecute;
}

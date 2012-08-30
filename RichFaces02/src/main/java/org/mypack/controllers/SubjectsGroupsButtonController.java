package org.mypack.controllers;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.mypack.model.SubjectEntity;
import org.mypack.services.SubjectsFactory;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsGroupsButtonController implements Serializable {

	@ManagedProperty(value = "#{subjectsFactory}")
	private SubjectsFactory subjects;

	public SubjectsGroupsButtonController() {
		newButton = new BaseActionButton();
		editButton = new BaseActionButton();
		cutButton = new BaseActionButton();
		pasteButton = new BaseActionButton();
		showNewGroupPanel = false;
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

	public Boolean getShowNewGroupPanel() {
		return showNewGroupPanel;
	}

	public void setShowNewGroupPanel(Boolean showNewGroupPanel) {
		this.showNewGroupPanel = showNewGroupPanel;
	}

	public void newButtonExecute() {
		System.out.println("new Button Execure");
		this.showNewGroupPanel = true;
	}

	public void SaveNewButtonExecute() {
		this.setShowNewGroupPanel(false);
		System.out.println(String.format("Сохранено... %s в %d", groupName,
				selectedGroupId));
		SubjectEntity subject = subjects.createSubject(groupName,
				selectedGroupId);

		/*ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SubjectsTreeController tree = (SubjectsTreeController) FacesContext
				.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "#{subjectsTreeController}");*/
		FacesContext fc = FacesContext.getCurrentInstance(); 
		SubjectsTreeController tree = (SubjectsTreeController)
				fc.getApplication().getVariableResolver().resolveVariable(fc, "subjectsTreeController");  

		if (tree != null) {
			System.out.println("tree not null");
			tree.addTreeNode(subject, selectedGroupId);
		}
	}
	
	public void hideNewButtons(){
		this.setShowNewGroupPanel(false);
	}	

	public void setSelectedGroupId(Integer selectedGroupId) {
		this.selectedGroupId = selectedGroupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public SubjectsFactory getSubjects() {
		return subjects;
	}

	public void setSubjects(SubjectsFactory subjects) {
		this.subjects = subjects;
	}

	private Boolean showNewGroupPanel;
	private Integer selectedGroupId;
	private String groupName;
	private BaseActionButton newButton;
	private BaseActionButton editButton;
	private BaseActionButton cutButton;
	private BaseActionButton pasteButton;
}

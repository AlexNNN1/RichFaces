package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.mypack.model.SubjectEntity;
import org.mypack.model.dto.SubjectTreeNode;
import org.mypack.services.SubjectEntityService;
import org.richfaces.component.UIExtendedDataTable;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsListController implements Serializable {
	private String selectionMode = "single";  
	private Collection<Object> selection;
	private List<SubjectEntity> items = new ArrayList<SubjectEntity>();
	private List<SubjectEntity> selectedItems = new ArrayList<SubjectEntity>();
	private SubjectEntity groupFilter = null;
	private SubjectEntity groupSubject;
	
	private SubjectEntity currentSelection;
	
	@ManagedProperty(value = "#{subjectEntityService}")
	private SubjectEntityService subjects;
	
	@PostConstruct
	public void init() {
		showEditPanel = false;
		showNewPanel = false;
		showViewPanel = true;
		
		disableNewButton = false;
		disableEditButton = true;
		disableCutButton = true;
		disablePasteButton = true;
	}

	public List<SubjectEntity> getItems() {
		return items;
	}

	public void setItems(List<SubjectEntity> items) {
		this.items = items;
	}

	public SubjectEntity getGroupFilter() {
		return groupFilter;
	}
	public void setGroupFilter(SubjectEntity groupFilter) {
		this.groupFilter = groupFilter;
	}	
	
	public SubjectEntityService getSubjects() {
		return subjects;
	}

	public void setSubjects(SubjectEntityService subjects) {
		this.subjects = subjects;
	}	

	public SubjectEntity getGroupSubject() {
		return groupSubject;
	}

	public void setGroupSubject(SubjectEntity groupSubject) {
		this.groupSubject = groupSubject;
	}

	public void loadItems(SubjectEntity group){
		items.clear();
		groupSubject = group;
		items = subjects.getLeavesInGroup(groupSubject.getId());
	}
	
	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public List<SubjectEntity> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SubjectEntity> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public void selectionListener(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable)event.getComponent();
		Object originalKey = dataTable.getRowKey();
		selectedItems.clear();
		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				selectedItems.add((SubjectEntity) dataTable.getRowData());
				currentSelection = (SubjectEntity) dataTable.getRowData();
			}
		}
		dataTable.setRowKey(originalKey);
	}
	
	public void newButtonExecute(){
		setShowEditPanel(false);
		setShowNewPanel(true);
		setShowViewPanel(false);
	}
	
	public void cancelButtonExecute(){
		setShowEditPanel(false);
		setShowNewPanel(false);
		setShowViewPanel(true);
	}
	
	public void addButtonExecute(){
		
	}

	public SubjectEntity getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(SubjectEntity currentSelection) {
		this.currentSelection = currentSelection;
	}
	
	public Boolean getShowEditPanel() {
		return showEditPanel;
	}

	public void setShowEditPanel(Boolean showEditPanel) {
		this.showEditPanel = showEditPanel;
	}

	public Boolean getShowNewPanel() {
		return showNewPanel;
	}

	public void setShowNewPanel(Boolean showNewPanel) {
		this.showNewPanel = showNewPanel;
	}

	public Boolean getShowViewPanel() {
		return showViewPanel;
	}

	public void setShowViewPanel(Boolean showViewPanel) {
		this.showViewPanel = showViewPanel;
	}


	public Boolean getDisableEditButton() {
		return disableEditButton;
	}

	public void setDisableEditButton(Boolean disableEditButton) {
		this.disableEditButton = disableEditButton;
	}

	public Boolean getDisableNewButton() {
		return disableNewButton;
	}

	public void setDisableNewButton(Boolean disableNewButton) {
		this.disableNewButton = disableNewButton;
	}

	public Boolean getDisableCutButton() {
		return disableCutButton;
	}

	public void setDisableCutButton(Boolean disableCutButton) {
		this.disableCutButton = disableCutButton;
	}

	public Boolean getDisablePasteButton() {
		return disablePasteButton;
	}

	public void setDisablePasteButton(Boolean disablePasteButton) {
		this.disablePasteButton = disablePasteButton;
	}
	
	public String getNewItemName() {
		return newItemName;
	}

	public void setNewItemName(String newItemName) {
		this.newItemName = newItemName;
	}

	private Boolean showEditPanel;
	private Boolean showNewPanel;
	private Boolean showViewPanel;
	private Boolean disableEditButton;
	private Boolean disableNewButton;
	private Boolean disableCutButton;
	private Boolean disablePasteButton;
	private String newItemName;
}

















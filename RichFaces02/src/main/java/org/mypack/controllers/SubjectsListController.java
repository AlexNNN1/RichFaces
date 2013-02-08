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

import org.mypack.helpers.Constants;
import org.mypack.model.SubjectEntity;
import org.mypack.services.SubjectEntityService;
import org.mypack.services.SubjectsFactory;
import org.richfaces.component.UIExtendedDataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private SubjectEntity currentSelection;
	private UIExtendedDataTable table_;

	@ManagedProperty(value = "#{subjectEntityService}")
	private SubjectEntityService subjects;

	@ManagedProperty(value = "#{subjectsFactory}")
	private SubjectsFactory subjectsFactory;

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

	public void loadItems(SubjectEntity group) {
		disableEditButton = true;
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
		disableEditButton = true;
		switchToViewPanel();
		selectedItems.clear();
		setSelevtedItem(event);
	}

	private void setSelevtedItem(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		table_ = dataTable;
		Object originalKey = dataTable.getRowKey();
		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				selectedItems.add((SubjectEntity) dataTable.getRowData());
				currentSelection = (SubjectEntity) dataTable.getRowData();
				disableEditButton = false;
			}
		}
		dataTable.setRowKey(originalKey);
	}

	public void switchToViewPanel() {
		setShowEditPanel(false);
		setShowNewPanel(false);
		setShowViewPanel(true);
		//currentSelection = null;
		//selectedItems.clear();
	}

	public void newButtonExecute() {
		newItemName = "";
		setShowEditPanel(false);
		setShowNewPanel(true);
		setShowViewPanel(false);
	}

	public void cancelButtonExecute() {
		switchToViewPanel();
	}

	public void addButtonExecute() {
		int selectedGroupId = groupSubject.getId();
		SubjectEntity subject = subjectsFactory.createSubject(newItemName,
				selectedGroupId, Constants.subjectLeave);
		items.add(subject);
		selection.clear();
		selection.add(1);
		table_.setRowKey(1);
		selectedItems.add(subject);
		currentSelection = subject;
		switchToViewPanel();
	}

	public void saveOrUpdateExecute() {
		if (currentSelection != null) {
			currentSelection.setName(newItemName);
			subjectsFactory.updateSubject(currentSelection);
		}
		switchToViewPanel();
	}

	public void editButtomExecute() {
		if (currentSelection != null)
			newItemName = currentSelection.getName();
		setShowEditPanel(true);
		setShowNewPanel(false);
		setShowViewPanel(false);
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

	public SubjectsFactory getSubjectsFactory() {
		return subjectsFactory;
	}

	public void setSubjectsFactory(SubjectsFactory subjectsFactory) {
		this.subjectsFactory = subjectsFactory;
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
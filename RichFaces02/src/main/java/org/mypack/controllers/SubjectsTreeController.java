package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.mypack.model.dto.SubjectTreeNode;
import org.mypack.services.SubjectEntityService;
import org.mypack.services.SubjectInGroupEntityService;
import org.mypack.services.SubjectsFactory;
import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsTreeController implements Serializable {
	private List<SubjectTreeNode> nodes = new ArrayList<SubjectTreeNode>();
	private SubjectTreeNode currentSelection;

	@ManagedProperty(value = "#{subjectInGroupEntityService}")
	private SubjectInGroupEntityService subjectInGroups;

	@ManagedProperty(value = "#{subjectsGroupsButtonController}")
	private SubjectsGroupsButtonController buttons;

	@ManagedProperty(value = "#{subjectsListController}")
	private SubjectsListController details;

	@ManagedProperty(value = "#{subjectsFactory}")
	private SubjectsFactory subjects;
	
	@ManagedProperty(value = "#{subjectEntityService}")
	private SubjectEntityService subjectService;
	
	@PostConstruct
	public void init() {
		this.loadTreeNodes();
		this.setShowViewPanel(true);
	}

	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
		List<Object> selection = new ArrayList<Object>(
				selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0);
		UITree tree = (UITree) selectionChangeEvent.getSource();
		Object storedKey = tree.getRowKey();
		tree.setRowKey(currentSelectionKey);
		currentSelection = (SubjectTreeNode) tree.getRowData();
		tree.setRowKey(storedKey);
		changeButtonsDisabledOnSelect();
		loadChildSubjects();
		this.setShowViewPanel(true);
	}

	public void loadChildSubjects() {
		if (currentSelection != null && currentSelection.getSubject() != null) {
			details.loadItems(currentSelection.getSubject().getId());
		}
	}

	public List<SubjectTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<SubjectTreeNode> nodes) {
		this.nodes = nodes;
	}

	public SubjectTreeNode getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(SubjectTreeNode currentSelection) {
		this.currentSelection = currentSelection;
		changeButtonsDisabledOnSelect();
	}

	public SubjectInGroupEntityService getSubjectInGroups() {
		return subjectInGroups;
	}

	public void setSubjectInGroups(SubjectInGroupEntityService subjectInGroups) {
		this.subjectInGroups = subjectInGroups;
	}

	public SubjectsGroupsButtonController getButtons() {
		return buttons;
	}

	public void setButtons(SubjectsGroupsButtonController buttons) {
		this.buttons = buttons;
	}

	public SubjectsListController getDetails() {
		return details;
	}

	public void setDetails(SubjectsListController details) {
		this.details = details;
	}
	

	public SubjectEntityService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectEntityService subjectService) {
		this.subjectService = subjectService;
	}

	private void changeButtonsDisabledOnSelect() {
		if (this.currentSelection != null) {
			buttons.getNewButton().setDisabled(false);
			buttons.getEditButton().setDisabled(false);
			buttons.getCutButton().setDisabled(false);
			buttons.setShowNewGroupPanel(false);
			buttons.setSelectedGroupId(currentSelection.getSubject().getId());
		}
	}

	private void loadTreeNodes() {

		nodes.clear();
		List<SubjectInGroupEntity> list = subjectInGroups.getForTreeFiller(5);
		for (SubjectInGroupEntity node : list) {
			if (node.getGroup() == null || node.getGroup().getId() == 0) {
				SubjectTreeNode item = new SubjectTreeNode();
				item.setSubject(node.getItem());
				nodes.add(item);
				fillSubNodes(list, node, item);
			}
		}

	}

	private void fillSubNodes(List<SubjectInGroupEntity> list,
			SubjectInGroupEntity parentNode, SubjectTreeNode parentItem) {
		for (SubjectInGroupEntity node : list) {
			if (node.getGroup() != null) {
				if (node.getGroup().getId() == parentNode.getItem().getId()) {
					SubjectTreeNode item = new SubjectTreeNode();
					item.setSubject(node.getItem());
					parentItem.getChilds().add(item);
					fillSubNodes(list, node, item);
				}
			}
		}
	}

	private SubjectTreeNode getForId(List<SubjectTreeNode> treeNodes,
			Integer subjectId) {
		SubjectTreeNode result = null;
		System.out.println("finding node");

		for (SubjectTreeNode node : treeNodes) {

			Integer sID = node.getSubject().getId();
			System.out.println("subjectId " + subjectId);
			System.out.println("node subject id " + sID);
			System.out.println("node " + node);
			System.out.println("node subject " + node.getSubject());

			if (sID.equals(subjectId)) {
				System.out.println("finded node!!! "
						+ node.getSubject().getId());
				result = node;
				System.out.println("breaking ");
				break;
			} else {
				SubjectTreeNode value = getForId(node.getChilds(), subjectId);
				if (value != null) {
					result = value;
					break;
				}
			}
		}
		System.out.println("result " + result);
		return result;
	}

	public void addTreeNode(SubjectEntity subject, Integer rootID) {
		SubjectTreeNode item = new SubjectTreeNode();
		item.setSubject(subject);
		System.out.println("before find !!!!");
		SubjectTreeNode root = this.getForId(nodes, rootID);
		System.out.println("rootId = " + rootID);
		System.out.println("root = " + root);
		 root.getChilds().add(item);
	}
	
	public Boolean getShowEditPanel() {
		return showEditPanel;
	}

	public void setShowEditPanel(Boolean showEditPanel) {
		this.showEditPanel = showEditPanel;
		this.showViewPanel = false;
		this.showNewPanel = false;
	}

	public Boolean getShowNewPanel() {
		return showNewPanel;
	}

	public void setShowNewPanel(Boolean showNewPanel) {
		this.showNewPanel = showNewPanel;
		this.showViewPanel = false;
		this.showEditPanel = false;
	}

	public Boolean getShowViewPanel() {
		return showViewPanel;
	}

	public void setShowViewPanel(Boolean showViewPanel) {
		this.showViewPanel = showViewPanel;
		this.showNewPanel = false;
		this.showEditPanel = false;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void newButtonExecute() {
		setGroupName("");
		this.setShowNewPanel(true);
	}
	
	public void editButtonExecute() {
		if (currentSelection != null) {
			setGroupName(currentSelection.getName());
			this.setShowEditPanel(true);
		}	
	}


	public SubjectsFactory getSubjects() {
		return subjects;
	}

	public void setSubjects(SubjectsFactory subjects) {
		this.subjects = subjects;
	}

	public void SaveNewButtonExecute() {
		System.out.println("new buton click");
		System.out.println(subjects);
		System.out.println(currentSelection);
		if (currentSelection != null) {
			int selectedGroupId = currentSelection.getSubject().getId();
			System.out.println(String.format("Сохранено... %s в %d", groupName,
					selectedGroupId));
			SubjectEntity subject = subjects.createSubject(groupName,
					selectedGroupId);
			System.out.println("Субджект " + subject);
			addTreeNode(subject, selectedGroupId);
		}
		this.setShowViewPanel(true);
	}
	
	public void saveUpdatedButtonExecute() {
		if (currentSelection != null) {
			SubjectEntity subject = currentSelection.getSubject();
			
			subject.setName(getGroupName());
			subjectService.saveOrUpdate(subject);
			//updateTreeNode(subject);
			
			/*SubjectEntity subj = subjectService.getEntity(subject.getId());
			subj.setName(getGroupName());
			subjectService.saveOrUpdate(subj);
			updateTreeNode(subj);*/
		}
		this.setShowViewPanel(true);
	}
	
	public void CancelButtonExecute() {
		this.setShowViewPanel(true);
	}

	private String groupName;
	private Boolean showEditPanel;
	private Boolean showNewPanel;
	private Boolean showViewPanel;

}

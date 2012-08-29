package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.mypack.model.dto.SubjectTreeNode;
import org.mypack.services.SubjectInGroupEntityService;
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
	

	@PostConstruct
	public void init() {
		loadTreeNodes();
	}
	

	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
		List<Object> selection = new ArrayList<Object>(
				selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0);
		UITree tree = (UITree) selectionChangeEvent.getSource();
		Object storedKey = tree.getRowKey();
		tree.setRowKey(currentSelectionKey);
		currentSelection = (SubjectTreeNode) tree.getRowData();
		changeButtonsDisabledOnSelect();
		tree.setRowKey(storedKey);
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
	
	private SubjectTreeNode getForId(List<SubjectTreeNode> treeNodes, Integer subjectId){
		SubjectTreeNode result = null;
		System.out.println("finding node");
		
		for (SubjectTreeNode node : treeNodes) {
			
			System.out.println("node " + node);
			System.out.println("node subject " + node.getSubject());
			System.out.println("node subject id " + node.getSubject().getId());
			
			if (node.getSubject().getId() == subjectId) {
				System.out.println("finded node " + node);
				result = node;
				break;
			}
			else
			{
				SubjectTreeNode value =getForId(node.getChilds(), subjectId); 
				if (value != null){
					result = value;
					break;
				}
				
			}	
		
		}
		
		System.out.println("result " + result);
		return result;
	}
	
	public void addTreeNode(SubjectEntity subject, Integer rootID) {
		System.out.println("in add tree " + rootID);
		SubjectTreeNode item = new SubjectTreeNode();
		item.setSubject(subject);
		this.getForId(nodes, rootID).getChilds().add(item);
	}

}

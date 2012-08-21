package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.mypack.dao.SubjectInGroupEntityDao;
import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsTreeController implements Serializable {
	private List<SubjectEntity> nodes = new ArrayList<SubjectEntity>();
	private SubjectEntity currentSelection;

	@Autowired
	private SubjectInGroupEntityDao subjectInGroups;

	@PostConstruct
	public void init() {
		System.out.println("new tree constucted");
		loadTreeNodes();
	}

	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
		// considering only single selection
		List<Object> selection = new ArrayList<Object>(
				selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0);
		UITree tree = (UITree) selectionChangeEvent.getSource();
		Object storedKey = tree.getRowKey();
		tree.setRowKey(currentSelectionKey);
		currentSelection = (SubjectEntity) tree.getRowData();
		tree.setRowKey(storedKey);
	}

	public List<SubjectEntity> getNodes() {
		return nodes;
	}

	public void setNodes(List<SubjectEntity> nodes) {
		this.nodes = nodes;
	}

	public SubjectEntity getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(SubjectEntity currentSelection) {
		this.currentSelection = currentSelection;
	}

	private void loadTreeNodes() {
		List<SubjectInGroupEntity> list = subjectInGroups.getForTreeFiller(5);
		for (SubjectInGroupEntity node : list) {
			if (node.getGroup().getId() == 0) {
				nodes.add(node.getItem());
				fillSubNodes(list, node);
			}
		}
	}
	
	private void fillSubNodes(List<SubjectInGroupEntity> list, SubjectInGroupEntity parentNode) {
		for (SubjectInGroupEntity node : list) {
			if (node.getGroup().getId() == parentNode.getId()) {
				nodes.add(node.getItem());
				fillSubNodes(list, node);
			}
		}
	}

	
		
	
}

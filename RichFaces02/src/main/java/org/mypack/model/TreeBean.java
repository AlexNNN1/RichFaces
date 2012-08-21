package org.mypack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.tree.TreeNode;
import javax.annotation.PostConstruct; 

import org.ajax4jsf.model.DataComponentState;
import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.richfaces.event.TreeToggleEvent;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {
	private List<FolderNode> nodes;
	private TreeNode currentSelection;
	private DataComponentState dataState;
	public TreeBean(){
		nodes = new ArrayList<FolderNode>();
	}
	
	@PostConstruct
	public void init() {
		System.out.println("tree constucted");
		FolderNode root = new FolderNode();
		root.setName("Предприятие");
		nodes.add(root);
		FolderNode node1 = new FolderNode();
		node1.setName("Подразделение 1");
		root.getChilds().add(node1);
		FolderNode node2 = new FolderNode();
		node2.setName("Подразделение 2");
		root.getChilds().add(node2);	
	}

	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) { 
		// considering only single selection
		List<Object> selection = new ArrayList<Object>(selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0); 
		UITree tree = (UITree) selectionChangeEvent.getSource();
		Object storedKey = tree.getRowKey();
		tree.setRowKey(currentSelectionKey);
		currentSelection = (TreeNode) tree.getRowData();
		tree.setRowKey(storedKey);
	}
	
	  public void toggleNodeListener(TreeToggleEvent ev){
		/*  UITree tree = (UITree)ev.getSource();
		  FolderNode nodeExp = ((FolderNode)tree.getRowData());
	      nodeExp.setExpanded(!nodeExp.isExpanded());*/
	      System.out.println("toggled");
	    }
	
	public List<FolderNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<FolderNode> nodes) {
		this.nodes = nodes;
	}

	public TreeNode getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(TreeNode currentSelection) {
		this.currentSelection = currentSelection;
	}

	public DataComponentState getDataState() {
	//	System.out.println("state getting");
		return dataState;
	}

	public void setDataState(DataComponentState dataState) {
		//System.out.println("state setting");
		this.dataState = dataState;
	}
	
}

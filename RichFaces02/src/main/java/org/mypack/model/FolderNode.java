package org.mypack.model;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

@SuppressWarnings("serial")
public class FolderNode extends BaseNode implements TreeNode {
	private List<FolderNode> childs;
	private FolderNode parend;
	private Boolean expanded;
	
	public FolderNode(){
		childs = new LinkedList<FolderNode>();
		parend = null;
		setType("folder");
	}
	
	public TreeNode getChildAt(int childIndex) {
		return childs.get(childIndex);
	}

	public int getChildCount() {
		return childs.size();
	}

	public TreeNode getParent() {
		return parend;
	}

	public int getIndex(TreeNode node) {
		return childs.indexOf(node);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return childs.isEmpty();
	}

	public Enumeration<FolderNode> children() {
		return Iterators.asEnumeration(childs.iterator());
	}

	public List<FolderNode> getChilds() {
		return childs;
	}

	public void setChilds(List<FolderNode> childs) {
		this.childs = childs;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	
	
}

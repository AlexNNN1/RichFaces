package org.mypack.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import org.mypack.model.SubjectEntity;
import com.google.common.collect.Iterators;

@SuppressWarnings("serial")
public class SubjectTreeNode implements Serializable, TreeNode {

	private SubjectEntity subject;
	private String type = "subject";
	private List<SubjectTreeNode> childs = new ArrayList<SubjectTreeNode>();
	private SubjectTreeNode parend;
	private Boolean expanded;
	
	public String getType() {
		return this.type;
	}
	
	public String getName() {
		return subject.getName();
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
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

	public Enumeration<SubjectTreeNode> children() {
		return Iterators.asEnumeration(childs.iterator());
	}

	public List<SubjectTreeNode> getChilds() {
		return childs;
	}

	public void setChilds(List<SubjectTreeNode> childs) {
		this.childs = childs;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public SubjectTreeNode getParend() {
		return parend;
	}

	public void setParend(SubjectTreeNode parend) {
		this.parend = parend;
	}
}

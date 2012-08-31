package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.mypack.model.SubjectEntity;
import org.mypack.services.SubjectEntityService;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SubjectsListController implements Serializable {
	private List<SubjectEntity> items = new ArrayList<SubjectEntity>();
	private SubjectEntity groupFilter = null;
	
	@ManagedProperty(value = "#{subjectEntityService}")
	private SubjectEntityService subjects;
	
	@PostConstruct
	public void init() {
		SubjectEntity subj = new SubjectEntity();
		subj.setName("value 1");
		subj.setId(1);
		items.add(subj);
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

	public void loadItems(Integer groupId){
		System.out.println(String.format("groupId = %d", groupId));
		items.clear();
		System.out.println(String.format("items.count = %d", items.size()));
		items = subjects.getSubjectsInGroup(groupId);
		System.out.println(String.format("items.count = %d", items.size()));
	}
}
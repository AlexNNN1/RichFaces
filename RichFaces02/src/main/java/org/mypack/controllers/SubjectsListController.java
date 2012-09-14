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
	private SubjectEntity groupSubject;
	
	@ManagedProperty(value = "#{subjectEntityService}")
	private SubjectEntityService subjects;
	
	@PostConstruct
	public void init() {}

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
}
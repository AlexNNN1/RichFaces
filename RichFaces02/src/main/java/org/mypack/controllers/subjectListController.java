package org.mypack.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.mypack.model.FolderNode;
import org.mypack.model.SubjectEntity;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class subjectListController  implements Serializable {
	
	private List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
	private SubjectEntity groupFilter = null;
	
	@PostConstruct
	public void init() {
		SubjectEntity subj = new SubjectEntity();
		subj.setName("value 1");
		subj.setId(1);
		subjects.add(subj);
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}
	public SubjectEntity getGroupFilter() {
		return groupFilter;
	}
	public void setGroupFilter(SubjectEntity groupFilter) {
		this.groupFilter = groupFilter;
	}

}
